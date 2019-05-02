package saml20.implementation.binding;

import java.io.IOException;

import org.opensaml.common.SAMLException;
import org.opensaml.common.xml.SAMLConstants;
import org.opensaml.saml2.metadata.Endpoint;

import com.mendix.core.Core;
import com.mendix.logging.ILogNode;
import com.mendix.m2ee.api.IMxRuntimeRequest;
import com.mendix.m2ee.api.IMxRuntimeResponse;

import saml20.implementation.SAMLRequestContext;
import saml20.implementation.common.Constants;
import saml20.implementation.common.HTTPUtils;
import saml20.implementation.metadata.IdpMetadata.Metadata;
import saml20.implementation.wrapper.MxSAMLObject;

public class RedirectBindingHandler implements BindingHandler {
	private static final ILogNode _logNode = Core.getLogger(Constants.LOGNODE);

	@Override
	public String getBindingURI() {
		return SAMLConstants.SAML2_REDIRECT_BINDING_URI;
	}

	@Override
	public void handle( IMxRuntimeRequest req, IMxRuntimeResponse response, SAMLRequestContext context, Metadata metadata, Endpoint destination, MxSAMLObject obj, String relayState  ) throws SAMLException {
		String url = obj.getRedirectURL(context, metadata, destination, relayState);
		_logNode.debug("Issuing redirect to " + url);
		try {
			HTTPUtils.sendMetaRedirect(response, url, null);
		}
		catch( IOException e ) {
			throw new SAMLException("Unable to redirect for object: " + url, e);
		}
	}

}
