package saml20.implementation.wrapper;

import com.mendix.core.Core;
import com.mendix.logging.ILogNode;
import org.opensaml.common.SAMLException;
import org.opensaml.common.SAMLObject;
import org.opensaml.common.SignableSAMLObject;
import org.opensaml.common.binding.BasicEndpointSelector;
import org.opensaml.common.binding.BasicSAMLMessageContext;
import org.opensaml.common.binding.SAMLMessageContext;
import org.opensaml.saml2.binding.encoding.HTTPRedirectDeflateEncoder;
import org.opensaml.saml2.metadata.Endpoint;
import org.opensaml.saml2.metadata.RoleDescriptor;
import org.opensaml.security.SAMLSignatureProfileValidator;
import org.opensaml.ws.message.encoder.MessageEncodingException;
import org.opensaml.ws.transport.http.HttpServletResponseAdapter;
import org.opensaml.xml.ElementExtensibleXMLObject;
import org.opensaml.xml.Namespace;
import org.opensaml.xml.io.Marshaller;
import org.opensaml.xml.io.MarshallingException;
import org.opensaml.xml.security.SecurityException;
import org.opensaml.xml.security.SecurityHelper;
import org.opensaml.xml.security.credential.Credential;
import org.opensaml.xml.security.x509.BasicX509Credential;
import org.opensaml.xml.signature.*;
import org.opensaml.xml.util.Base64;
import org.opensaml.xml.util.XMLHelper;
import org.opensaml.xml.validation.ValidationException;
import org.w3c.dom.Element;
import saml20.implementation.SAMLRequestContext;
import saml20.implementation.common.Constants;
import saml20.implementation.common.SAMLUtil;
import saml20.implementation.metadata.IdpMetadata.Metadata;
import saml20.proxies.EncryptionMethod;

import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.namespace.QName;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.PublicKey;
import java.security.cert.X509Certificate;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;

import static org.opensaml.common.xml.SAMLConstants.SAML20P_NS;
import static org.opensaml.common.xml.SAMLConstants.SAML2_REDIRECT_BINDING_URI;

public class MxSAMLObject {
    protected static final ILogNode _logNode = Core.getLogger(Constants.LOGNODE);

    private final SAMLObject obj;

    public MxSAMLObject(SAMLObject obj) {
        if (obj == null)
            throw new IllegalArgumentException("Object cannot be null");

        this.obj = obj;
    }

    /**
     * Get an XML representation of the object.
     *
     * @throws SAMLException
     */
    public String toXML() throws SAMLException {
        Element e = SAMLUtil.marshallObject(this.obj);
        return XMLHelper.nodeToString(e);
    }

    /**
     * Encode the SAML object to a base64 encoded string.
     *
     * @return The XML representation encoded with base64.
     * @throws SAMLException
     */
    public String toBase64() throws SAMLException {
        Element element = SAMLUtil.marshallObject(this.obj);
        String xml = XMLHelper.nodeToString(element);
        return Base64.encodeBytes(xml.getBytes(), Base64.DONT_BREAK_LINES);
    }

    public boolean hasSignature() {
        if (!(this.obj instanceof SignableSAMLObject))
            return false;
        return ((SignableSAMLObject) this.obj).getSignature() != null;
    }

    /**
     * Check that a given object has been signed correctly with a specific {@link PublicKey}.
     *
     * @return true, if the signableObject has been signed correctly with the given key. Returns <code>false</code> if
     * the object is not signed at all.
     */
    public boolean verifySignature(X509Certificate certificate) {
        if (certificate == null) {
            throw new IllegalArgumentException("Certificate cannot be null");
        }
        Signature signature = null;
        if (this.obj instanceof SignableSAMLObject) {
            SignableSAMLObject signableObject = (SignableSAMLObject) this.obj;

            signature = signableObject.getSignature();
        } else if (this.obj instanceof ElementExtensibleXMLObject) {
            signature = SAMLUtil.getFirstElement((ElementExtensibleXMLObject) this.obj, Signature.class);
        }

        if (signature == null) {
            _logNode.warn("No signature present in object " + this.obj);
            return false;
        }

        // verify signature element according to SAML profile
        SAMLSignatureProfileValidator profileValidator = new SAMLSignatureProfileValidator();
        try {
            profileValidator.validate(signature);
        } catch (Exception e) {
            _logNode.warn("The signature does not meet the requirements indicated by the SAML profile of the XML signature", e);
            return false;
        }

        // verify signature
        BasicX509Credential credential = new BasicX509Credential();
        credential.setEntityCertificate(certificate);
        SignatureValidator validator = new SignatureValidator(credential);
        try {
            validator.validate(signature);
            return true;
        } catch (ValidationException e) {
            // BJHL 2016-02-18 Moved from warning to trace. Another certificate might match, verification failure should be handled by caller of this method.
            _logNode.trace("The signature does not match the signature of the login site", e);
            return false;
        }
    }

    /**
     * Sign the saml object.
     * <p>
     * The effect of calling this method is that a new Signature element is created, and the object is marshalled. If
     * {@link #toXML()} is called, the XML will contain a valid signature.
     *
     * @param signingCredential The credential used for signing the object.
     * @throws SAMLException
     */
    public void sign(Credential signingCredential, String encryptionMethod) throws SAMLException {
        Signature signature = SAMLUtil.buildXMLObject(Signature.class);
        if (!(this.obj instanceof SignableSAMLObject)) {
            throw new IllegalStateException("Object of type " + this.obj.getClass() + " is not signable");
        }
        // manually add the ds namespace, as it will be added to the inclusiveNamespaces element
        this.obj.getNamespaceManager().registerNamespace(new Namespace(XMLSignature.XMLNS, "ds"));

        signature.setSigningCredential(signingCredential);
        if (encryptionMethod.equals(EncryptionMethod.SHA256WithRSA.toString())) {
            signature.setSignatureAlgorithm(SignatureConstants.ALGO_ID_SIGNATURE_RSA_SHA256);
        } else {
            signature.setSignatureAlgorithm(SignatureConstants.ALGO_ID_SIGNATURE_RSA_SHA1);
        }

        try {
            SecurityHelper.prepareSignatureParams(signature, signingCredential, null, null);
        } catch (SecurityException e) {
            throw new SAMLException(e);
        }

        ((SignableSAMLObject) this.obj).setSignature(signature);

        try {
            Marshaller marshaller = org.opensaml.xml.Configuration.getMarshallerFactory().getMarshaller(this.obj);
            if (marshaller == null) {
                throw new RuntimeException("No marshaller registered for " + this.obj.getElementQName() + ", unable to marshall in preperation for signing");
            }
            marshaller.marshall(this.obj);

            Signer.signObject(signature);
        } catch (MarshallingException e) {
            _logNode.error("Unable to marshall protocol message in preparation for signing", e);
            throw new SAMLException(e);
        } catch (SignatureException e) {
            _logNode.error("Unable to sign protocol message", e);
            throw new SAMLException(e);
        }
    }

    public String getRedirectURL(SAMLRequestContext requestContext, Metadata metadata, Endpoint destination, String relayState) throws SAMLException {
        Encoder enc = new Encoder();
        try {
            return enc.buildRedirectURL(requestContext, metadata, relayState, destination);
        } catch (MessageEncodingException e) {
            throw new SAMLException(e);
        }
    }

    protected class Encoder extends HTTPRedirectDeflateEncoder {
        public String buildRedirectURL(SAMLRequestContext requestContext, Metadata metadata, String relayState, Endpoint destination) throws MessageEncodingException {
            SAMLMessageContext<?, SAMLObject, ?> messageContext = buildMessageContext(MxSAMLObject.this, requestContext, metadata, relayState);

            String messageStr = XMLHelper.nodeToString(marshallMessage(MxSAMLObject.this.obj));

            Deflater deflater = new Deflater(Deflater.DEFLATED, true);
            String encoded;
            try (ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();
                 DeflaterOutputStream deflaterStream = new DeflaterOutputStream(bytesOut, deflater)) {
                deflaterStream.write(messageStr.getBytes("UTF-8"));
                deflaterStream.finish();

                encoded = Base64.encodeBytes(bytesOut.toByteArray(), Base64.DONT_BREAK_LINES);
            } catch (IOException e) {
                throw new RuntimeException("Unable to deflate message", e);
            }
            return super.buildRedirectURL(messageContext, destination.getLocation(), encoded);
        }
    }

    public SAMLMessageContext<?, SAMLObject, ?> buildMessageContext(MxSAMLObject mxSAMLObject, SAMLRequestContext requestContext, Metadata metadata, String relayState) {

        SAMLMessageContext<?, SAMLObject, ?> messageContext = new BasicSAMLMessageContext<SAMLObject, SAMLObject, SAMLObject>();
        // Build the parameters for the request
        messageContext.setOutboundSAMLMessage(mxSAMLObject.obj);
        messageContext.setRelayState(relayState);

        // Sign the parameters
        messageContext.setOutboundSAMLMessageSigningCredential(requestContext.getCredential());
        messageContext.setOutboundMessageTransport(new HttpServletResponseAdapter(requestContext.getResponse().getHttpServletResponse(), true));

        initializeContextEntityInformation(messageContext, metadata.getEntityDescriptor(), org.opensaml.saml2.metadata.Endpoint.DEFAULT_ELEMENT_NAME);

        return messageContext;
    }

    private static void initializeContextEntityInformation(SAMLMessageContext<? extends SAMLObject, ? extends SAMLObject, ? extends SAMLObject> context, org.opensaml.saml2.metadata.EntityDescriptor entity, QName endpointType) {
        RoleDescriptor role = entity.getIDPSSODescriptor(SAML20P_NS);

        context.setLocalEntityId(entity.getEntityID());
        context.setLocalEntityMetadata(entity);
        context.setLocalEntityRole(endpointType);
        context.setLocalEntityRoleMetadata(role);
        context.setOutboundMessageIssuer(entity.getEntityID());

        // Initialize the Peer information
        context.setPeerEntityId(entity.getEntityID());
        context.setPeerEntityMetadata(entity);
        context.setPeerEntityRole(endpointType);

        context.setPeerEntityRoleMetadata(role);
        {
            BasicEndpointSelector selector = new BasicEndpointSelector();
            selector.setEntityMetadata(entity);
            selector.setEndpointType(endpointType);
            selector.setEntityRoleMetadata(role);
            selector.getSupportedIssuerBindings().add(SAML2_REDIRECT_BINDING_URI);
            context.setPeerEntityEndpoint(selector.selectEndpoint());
        }
    }

}
