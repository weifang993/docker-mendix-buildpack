package saml20.implementation.binding;

import org.opensaml.common.SAMLException;
import org.opensaml.saml2.metadata.Endpoint;

import com.mendix.m2ee.api.IMxRuntimeRequest;
import com.mendix.m2ee.api.IMxRuntimeResponse;

import saml20.implementation.SAMLRequestContext;
import saml20.implementation.metadata.IdpMetadata.Metadata;
import saml20.implementation.wrapper.MxSAMLObject;

public interface BindingHandler {

	public String getBindingURI();

	public void handle( IMxRuntimeRequest req, IMxRuntimeResponse response, SAMLRequestContext context, Metadata metadata, Endpoint endpoint, MxSAMLObject mxSamlObj, String relayState ) throws SAMLException;
}
