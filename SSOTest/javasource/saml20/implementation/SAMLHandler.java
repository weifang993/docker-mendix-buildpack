package saml20.implementation;

import org.opensaml.common.SAMLException;

import saml20.implementation.common.Constants;

import com.mendix.core.Core;
import com.mendix.logging.ILogNode;

public abstract class SAMLHandler {
	protected static ILogNode _logNode = Core.getLogger(Constants.LOGNODE);

	public abstract void handleRequest( SAMLRequestContext context ) throws SAMLException;

	protected void printTraceInfo( SAMLRequestContext context ) {
		if ( _logNode.isTraceEnabled() ) {
			StringBuilder builder = new StringBuilder();
			builder.append("Processing request: ").append(context.getRequest().getResourcePath()).append("\r\n - SAMLRequest: ").append(context.getRequest().getParameter("SAMLRequest")).append("\r\n - SAMLResponse: ").append(context.getRequest().getParameter(Constants.SAML_SAMLRESPONSE)).append("\r\n - RelayState: ").append(context.getRequest().getParameter(Constants.SAML_RELAYSTATE));

			_logNode.trace(builder);
		}
	}

}
