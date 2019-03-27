package saml20.implementation.binding;

import javax.servlet.http.HttpServletRequest;

import org.apache.velocity.app.VelocityEngine;
import org.opensaml.common.SAMLException;
import org.opensaml.common.SAMLObject;
import org.opensaml.common.binding.SAMLMessageContext;
import org.opensaml.common.xml.SAMLConstants;
import org.opensaml.saml2.binding.encoding.HTTPPostEncoder;
import org.opensaml.saml2.metadata.Endpoint;
import org.opensaml.ws.message.encoder.MessageEncodingException;

import com.mendix.m2ee.api.IMxRuntimeRequest;
import com.mendix.m2ee.api.IMxRuntimeResponse;

import saml20.implementation.SAMLRequestContext;
import saml20.implementation.common.Constants;
import saml20.implementation.common.HTTPUtils;
import saml20.implementation.metadata.IdpMetadata.Metadata;
import saml20.implementation.wrapper.MxSAMLObject;

public class PostBindingHandler implements BindingHandler {

	private VelocityEngine engine;

	public PostBindingHandler() {
		this.engine = HTTPUtils.getEngine();
	}

	@Override
	public String getBindingURI() {
		return SAMLConstants.SAML2_POST_BINDING_URI;
	}

	@Override
	public void handle( IMxRuntimeRequest request, IMxRuntimeResponse response, SAMLRequestContext context, Metadata metadata, Endpoint destination, MxSAMLObject mxSamlObj, String relayState ) throws SAMLException {
		HttpServletRequest req = request.getHttpServletRequest();

		// BJHL 2015-05-28 If use encryption is false, we cannot sign since there is no credential 
		// available. Fall back to old behaviour of the module, which was to not sign the request.
		// This looks weird to me, I don't know for sure whether this will be accepted everywhere. 
		if ((boolean) context.getSpMetadata().getSpMetadataObject().getValue(
		        context.getIContext(), saml20.proxies.SPMetadata.MemberNames.UseEncryption.toString())) {
		    mxSamlObj.sign(context.getCredential(), context.getSpMetadata().getEncryptionAlgorithm(context.getIContext()));
		}
		String encodedMessage = mxSamlObj.toBase64();
		
		req.setAttribute("action", destination);
		if ( relayState != null ) {
			req.setAttribute(Constants.SAML_RELAYSTATE, relayState);
		}
		req.setAttribute(Constants.SAML_SAMLREQUEST, encodedMessage);

		try {
			SAMLMessageContext<?, SAMLObject, ?> samlContext = mxSamlObj.buildMessageContext(mxSamlObj, context, metadata, relayState);
			samlContext.setPeerEntityEndpoint(destination);

			HTTPPostEncoder encoder = new HTTPPostEncoder(this.engine, "templates/saml2-post-binding.vm");
			encoder.encode(samlContext);
		}
		catch( MessageEncodingException e) {
			throw new SAMLException(e);
		}
	}
}
