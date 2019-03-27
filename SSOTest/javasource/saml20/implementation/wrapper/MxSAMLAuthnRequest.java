package saml20.implementation.wrapper;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.opensaml.common.SAMLException;
import org.opensaml.common.SAMLVersion;
import org.opensaml.common.xml.SAMLConstants;
import org.opensaml.saml2.core.AuthnContextClassRef;
import org.opensaml.saml2.core.AuthnRequest;
import org.opensaml.saml2.core.Issuer;
import org.opensaml.saml2.core.NameIDPolicy;
import org.opensaml.saml2.core.RequestAbstractType;
import org.opensaml.saml2.core.RequestedAuthnContext;
import org.opensaml.saml2.core.impl.AuthnContextClassRefBuilder;
import org.opensaml.saml2.core.impl.AuthnRequestBuilder;
import org.opensaml.saml2.core.impl.IssuerBuilder;
import org.opensaml.saml2.core.impl.NameIDPolicyBuilder;
import org.opensaml.saml2.core.impl.RequestedAuthnContextBuilder;

import saml20.implementation.SAMLRequestContext;
import saml20.implementation.common.Constants;
import saml20.implementation.common.SAMLUtil;
import saml20.implementation.metadata.IdpMetadata.Metadata;
import saml20.proxies.SAMLAuthnContext;
import saml20.proxies.SSOConfiguration;

import com.mendix.core.Core;
import com.mendix.core.CoreException;
import com.mendix.logging.ILogNode;

public class MxSAMLAuthnRequest extends MxSAMLRequest {

	private static ILogNode _logNode = Core.getLogger(Constants.LOGNODE);

	private MxSAMLAuthnRequest( RequestAbstractType obj, String relayState ) {
		super(obj, relayState);
	}

	public static MxSAMLAuthnRequest buildAuthnRequestObject( SAMLRequestContext context, Metadata metadata, org.opensaml.saml2.metadata.Endpoint signonLocation, String relayState ) throws SAMLException {
		// Issuer object
		IssuerBuilder issuerBuilder = new IssuerBuilder();
		Issuer issuer = issuerBuilder.buildObject(SAMLConstants.SAML20_NS, "Issuer", "samlp");

		issuer.setValue(context.getSpMetadata().getEntityID());

		// Creation of AuthRequestObject
		DateTime issueInstant = new DateTime();
		AuthnRequestBuilder authRequestBuilder = new AuthnRequestBuilder();
		AuthnRequest authRequest = authRequestBuilder.buildObject(Constants.PROTOCOL, "AuthnRequest", "samlp");
		authRequest.setAttributeConsumingServiceIndex(1);
		
		// AuthnContext, only add if authn context classes are actually configured (ticket #40347)
		AuthnContextClassRefBuilder authnContextClassRefBuilder = new AuthnContextClassRefBuilder();
		List<AuthnContextClassRef> authContextClassList = getAuthContextClassList(SSOConfiguration.initialize(context.getIContext(), metadata.getSsoConfiguration()), authnContextClassRefBuilder);
		if (authContextClassList != null && !authContextClassList.isEmpty()) {
			RequestedAuthnContextBuilder requestedAuthnContextBuilder = new RequestedAuthnContextBuilder();
			RequestedAuthnContext requestedAuthnContext = requestedAuthnContextBuilder.buildObject();
	
			if( metadata.typeOfAuthnContextConfiguration(context.getIContext()) != null )
				requestedAuthnContext.setComparison(metadata.typeOfAuthnContextConfiguration(context.getIContext()));
	
			// AuthnContextClass
			for( AuthnContextClassRef accref : authContextClassList ) {
				requestedAuthnContext.getAuthnContextClassRefs().add(accref);
			}

			authRequest.setRequestedAuthnContext(requestedAuthnContext);
		}

		// Set the name policy
		if (metadata.disableNameIDPolicyConfiguration(context.getIContext()) != true) {
			NameIDPolicyBuilder policyBuilder = new NameIDPolicyBuilder();
			NameIDPolicy newNameIDPolicy = policyBuilder.buildObject(Constants.PROTOCOL, "NameIDPolicy", "samlp");
			newNameIDPolicy.setAllowCreate(true);
			newNameIDPolicy.setFormat(metadata.nameIDFormatConfiguration(context.getIContext()));
			
			authRequest.setNameIDPolicy(newNameIDPolicy);
		} // else skip altogether
		
		authRequest.setDestination(signonLocation.getLocation());
		authRequest.setIssueInstant(issueInstant);
		authRequest.setProtocolBinding(SAMLConstants.SAML2_POST_BINDING_URI);
		authRequest.setAssertionConsumerServiceURL(Constants._getInstance().getSP_URI() + Constants._getInstance().SSO_ASSERTION_PATH);
		authRequest.setConsent("urn:oasis:names:tc:SAML:2.0:consent:unspecified");
		authRequest.setIssuer(issuer);
		authRequest.setID(Constants.RELAYSTATE_SEPARATOR + SAMLUtil.getRequestIDFromRelayState(relayState));
		authRequest.setVersion(SAMLVersion.VERSION_20);

		return new MxSAMLAuthnRequest(authRequest, relayState);
	}

	private static List<AuthnContextClassRef> getAuthContextClassList( SSOConfiguration ssoconfig, AuthnContextClassRefBuilder authnContextClassRefBuilder ) {
		List<AuthnContextClassRef> tmpList = new ArrayList<AuthnContextClassRef>();
		try {
			List<SAMLAuthnContext> mxList = ssoconfig.getSSOConfiguration_SAMLAuthnContext();

			for( SAMLAuthnContext auc : mxList ) {
				AuthnContextClassRef authnContextClassRef = authnContextClassRefBuilder.buildObject(SAMLConstants.SAML20_NS, "AuthnContextClassRef", "saml");
				authnContextClassRef.setAuthnContextClassRef(auc.getValue());
				tmpList.add(authnContextClassRef);
			}

		}
		catch( CoreException e ) {
			_logNode.error("An exception occured while retrieving the authentication context", e);
		}

		return tmpList;
	}
}
