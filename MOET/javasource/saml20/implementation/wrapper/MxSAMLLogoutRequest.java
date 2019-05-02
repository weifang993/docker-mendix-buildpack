package saml20.implementation.wrapper;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.opensaml.common.SAMLException;
import org.opensaml.common.xml.SAMLConstants;
import org.opensaml.saml2.core.LogoutRequest;
import org.opensaml.saml2.core.RequestAbstractType;
import org.opensaml.saml2.core.SessionIndex;
import org.opensaml.saml2.core.impl.LogoutRequestBuilder;
import org.opensaml.saml2.core.impl.SessionIndexBuilder;
import org.opensaml.xml.Namespace;
import org.opensaml.xml.validation.ValidationException;

import com.mendix.core.Core;
import com.mendix.logging.ILogNode;
import com.mendix.m2ee.api.IMxRuntimeRequest;

import saml20.implementation.common.Constants;
import saml20.implementation.common.SAMLUtil;
import saml20.implementation.security.SAMLSessionInfo;
import saml20.implementation.security.SessionManager;

public class MxSAMLLogoutRequest extends MxSAMLRequest {
	private final static ILogNode _logNode = Core.getLogger(Constants.LOGNODE);

	public MxSAMLLogoutRequest( RequestAbstractType obj, String relayState ) {
		super(obj, relayState);
	}

	public static MxSAMLLogoutRequest buildLogoutRequest( IMxRuntimeRequest request, String logoutServiceLocation, String issuerEntityId, SessionManager handler ) throws SAMLException {
		LogoutRequest logoutRequest = new LogoutRequestBuilder().buildObject();

		String relayState = Constants.RELAYSTATE_SEPARATOR + java.util.UUID.randomUUID();

		logoutRequest.setID(relayState);
		logoutRequest.setIssueInstant(new DateTime(DateTimeZone.UTC));
		logoutRequest.getNamespaceManager().registerNamespace(new Namespace(SAMLConstants.SAML20_NS, SAMLConstants.SAML20_PREFIX));
		logoutRequest.setDestination(logoutServiceLocation);
		logoutRequest.setReason("urn:oasis:names:tc:SAML:2.0:logout:user");
		logoutRequest.setIssuer(SAMLUtil.createIssuer(issuerEntityId));

		SAMLSessionInfo sessionInfo = handler.getSessionDetails(request);
		if ( sessionInfo != null ) {
			SessionIndex sessionIndex = new SessionIndexBuilder().buildObject();
			sessionIndex.setSessionIndex(sessionInfo.getSessionIndex());

			logoutRequest.setNameID(sessionInfo.getNameID());
			logoutRequest.getSessionIndexes().add(sessionIndex);
		}

		try {
			_logNode.debug("Validate the logoutRequest...");
			logoutRequest.validate(true);
			_logNode.debug("...OK");

		}
		catch( ValidationException e ) {
			throw new SAMLException(e);
		}

		return new MxSAMLLogoutRequest(logoutRequest, relayState);
	}
}
