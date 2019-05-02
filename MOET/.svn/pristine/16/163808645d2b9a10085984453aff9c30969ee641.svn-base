package saml20.implementation.wrapper;

import org.opensaml.saml2.core.RequestAbstractType;

public class MxSAMLRequest extends MxSAMLObject {

	private RequestAbstractType request;
	private String relayState;

	public MxSAMLRequest( RequestAbstractType obj, String relayState ) {
		super(obj);
		this.request = obj;
		this.relayState = relayState;
	}

	public String getRelayState() {
		return this.relayState;
	}

	public String getID() {
		return this.request.getID();
	}

	public String getDestination() {
		return this.request.getDestination();
	}
}
