// This file was generated by Mendix Modeler 7.23.
//
// WARNING: Code you write here will be lost the next time you deploy the project.

package saml20.proxies.constants;

import com.mendix.core.Core;

public class Constants
{
	// These are the constants for the SAML20 module

	/**
	* If you encounter the error (most likely you are using Mac OSX and a Safari browser):
	* “MSIS7046: The SAML protocol parameter ‘RelayState’ was not found or not valid.”
	* 
	* Setting this Boolean to true might help you resolve the issue. By default we favour the Post binding as the maximum size exceeds that of a Redirect binding due to it using cookies en post information instead of URL parameters (redirect). The size can be a factor when using encryption.
	*/
	public static boolean getBindingURI_Redirect()
	{
		return (java.lang.Boolean)Core.getConfiguration().getConstantValue("SAML20.BindingURI_Redirect");
	}

	/**
	* You could define another login page here, for example when you configured the index.html page to redirect to '/SSO/'
	* 
	* This page allows the user to open a default Mendix login page so he can access the application with his regular credentials. The page opened from this location should contain a Mendix login page.
	* 
	* If you leave this constant empty the user will not get a button to open the default login page in case the SSO action fails.
	* Or you could specify a page starting without any slashes, such as:   login.html
	*/
	public static java.lang.String getDefaultLoginPage()
	{
		return (java.lang.String)Core.getConfiguration().getConstantValue("SAML20.DefaultLoginPage");
	}

	/**
	* It is recommended to remove the sign out button, but if you choose to keep the sign out button the user will be redirected to a page. 
	* 
	* After signing out the user will be redirected to this location.
	*/
	public static java.lang.String getDefaultLogoutPage()
	{
		return (java.lang.String)Core.getConfiguration().getConstantValue("SAML20.DefaultLogoutPage");
	}

	public static java.lang.String getKeystorePassword()
	{
		return (java.lang.String)Core.getConfiguration().getConstantValue("SAML20.KeystorePassword");
	}

	/**
	* You could define another landing page here, for example:
	* When you would like to redirect to '/SSO/' directly from your index.html page by adding '<meta http-equiv="refresh" content="0;URL=/SSO/" />', you don't want to end up on 'index.html' again.  
	* By renaming this constant to '/index3.html', you'll land on index3 instead of index.
	* Don't forget to add a 'index3.html' to your theme in this case! (You can copy the contents from index.html to index3.html)
	* 
	* 
	* 
	* 
	*/
	public static java.lang.String getSSOLandingPage()
	{
		return (java.lang.String)Core.getConfiguration().getConstantValue("SAML20.SSOLandingPage");
	}
}