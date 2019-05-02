package saml20.implementation.security;

import java.util.UUID;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;
import org.opensaml.common.SAMLException;
import org.opensaml.saml2.core.NameID;

import com.mendix.systemwideinterfaces.core.IUser;

import saml20.implementation.common.SAMLUtil;
import saml20.implementation.security.SessionManager.Configuration;
import saml20.implementation.security.ssl.AuthSSLProtocolSocketFactory;
import saml20.implementation.wrapper.MxSAMLAssertion;

public class SAMLSessionInfo {
	private String nameIDValue = null;
	private String format = "urn:oasis:names:tc:SAML:2.0:nameid-format:persistent";
	private String sessionIndex = null;
	private String entityId = null;
	private MxSAMLAssertion assertion;
	private String SAMLToken = null;
	private IUser userRecord;

	public SAMLSessionInfo( MxSAMLAssertion assertion, String entityId, Configuration config, IUser user ) {
		updateInformation(assertion, entityId, config, user);
	}

	public void updateInformation( MxSAMLAssertion assertion, String entityId, Configuration config, IUser user ) {
		this.userRecord = user;

		if ( assertion.getAssertion().getSubject() != null && assertion.getAssertion().getSubject().getNameID() != null ) {
			this.nameIDValue = assertion.getAssertion().getSubject().getNameID().getValue();
			this.format = assertion.getAssertion().getSubject().getNameID().getFormat();
		}
		this.sessionIndex = assertion.getSessionIndex();
		this.entityId = entityId;

		if ( config.allowDelegatedAuthentication )
			this.assertion = assertion;
		else
			this.assertion = null;
	}

	public NameID getNameID() {
		NameID nameID = SAMLUtil.buildXMLObject(NameID.class);
		nameID.setValue(this.nameIDValue);
		nameID.setFormat(this.format);

		return nameID;
	}

	public String getSessionIndex() {
		return this.sessionIndex;
	}

	public String getEntityId() {
		return this.entityId;
	}

	public MxSAMLAssertion getAssertion() {
		return this.assertion;
	}

	public void setSAMLToken( String SAMLtoken ) {
		this.SAMLToken = SAMLtoken;
	}

	public String getSAMLToken() {
		return this.SAMLToken;
	}

	private HttpClient httpClient = null;
	private boolean preventRemoval;
	private UUID SessionId = null;
	private String samlSessionID = null;

	/**
	 * Sets up the SSL parameters of a connection to the WSP, including the client certificate and server certificate
	 * 
	 * @throws SAMLException
	 */
	public HttpClient getClientConnection() throws SAMLException {
		if ( this.httpClient == null ) {
			Protocol.registerProtocol("https", new Protocol("https", (ProtocolSocketFactory) new AuthSSLProtocolSocketFactory(CredentialRepository.getInstance().getSSLKeyStore(), System.getProperty("javax.net.ssl.keyStorePassword"), CredentialRepository.getInstance().getTrustStore()), 443));
			MultiThreadedHttpConnectionManager cm2 = new MultiThreadedHttpConnectionManager();
			HttpConnectionManagerParams params = new HttpConnectionManagerParams();
			cm2.setParams(params);

			this.httpClient = new HttpClient(cm2);

			HttpClientParams clientParams = new HttpClientParams();
			clientParams.setCookiePolicy(CookiePolicy.DEFAULT);
			this.httpClient.setParams(clientParams);

			// Fake the user agent string:
			this.httpClient.getParams().setParameter(HttpMethodParams.USER_AGENT, "Mozilla/5.0 (Windows; U; Windows NT 6.1; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
			this.httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
		}

		return this.httpClient;
	}

	/**
	 * Prevent the SAMLSessionInfo to be removed from the SAML SessionCache even though there might not be an active
	 * User Session found for this Session Record
	 */
	public void setDeleteLock() {
		this.preventRemoval = true;
	}

	/**
	 * Release the lock so this SAMLSession record can be processed normally in the Next evaluation run. 
	 * When no user is found this SessionInfo object will be removed
	 */
	public void releaseLock() {
		this.preventRemoval = false;
	}

	public boolean isRemovalAllowed() {
		return !this.preventRemoval;
	}

	public void setSAMLSessionID( String sessionID ) {
		this.samlSessionID = sessionID;
	}

	public String getSamlSessionID() {
		return this.samlSessionID;
	}

	/**
	 * Register the Session Id for the Mendix User Session
	 * 
	 * @param SessionId
	 */
	public void setSessionId( UUID SessionId ) {
		this.SessionId = SessionId;
	}

	/**
	 * @return the SessionId from the Mendix User Session
	 */
	public UUID getSessionId() {
		return this.SessionId;
	}

	public IUser getIUser() {
		return this.userRecord;
	}

	
	public void setUserRecord( IUser userRecord ) {
		this.userRecord = userRecord;
	}
}