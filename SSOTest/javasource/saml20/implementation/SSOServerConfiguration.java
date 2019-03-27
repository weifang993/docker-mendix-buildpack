package saml20.implementation;

import saml20.implementation.common.Constants;

import com.mendix.core.Core;
import com.mendix.systemwideinterfaces.core.IContext;

public class SSOServerConfiguration {

	/**
	 * starts the Single Sign On servlet
	 * 
	 * @param context
	 * @throws Exception
	 */
	public static void start( IContext context ) throws Exception {
		try {
			SAMLRequestHandler.getInstance(context);
		}
		catch( Exception e ) {
			Core.getLogger(Constants.LOGNODE).error("Unable to add RequestHandler to path '" + Constants._getInstance().SSO_PATH + "': ", e);
		}
	}
}
