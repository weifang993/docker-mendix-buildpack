package sso.actions.custom;

import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;

import com.mendix.core.Core;
import com.mendix.core.CoreException;
import com.mendix.logging.ILogNode;
import com.mendix.m2ee.api.IMxRuntimeRequest;
import com.mendix.m2ee.api.IMxRuntimeResponse;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.systemwideinterfaces.core.ISession;
import com.mendix.systemwideinterfaces.core.IUser;


public class LoginHelper {
    
	/** based on xas2.5 */
    private static final String XAS_SESSION_ID = "XASSESSIONID";
    private static final String XAS_ID = "XASID";
    private static final String OriginURI = "originURI";
    private static final String OriginURIValue = "index.html";

    private static final String INDEX_PAGE_CONSTANT = "SSO.IndexPage";

    public static final int SECONDS_PER_YEAR = 60 * 60 * 24 * 365;
    
    protected static ILogNode SSOLOGGER = Core.getLogger("SSOHandler");
    
    public static void createSession(IMxRuntimeRequest request, IMxRuntimeResponse response, IContext context, IUser user, ISession existingSession, String redirect) {
    	
    	// see if active session exists for the user already
    	String cookie = request.getCookie(XAS_SESSION_ID);
        if (cookie == null || cookie.isEmpty()) {
            cookie = null;
        }

        ISession session = null;
        if (existingSession != null) {
        	session = existingSession;
        }
        //for (ISession activeSession : Core.getActiveSessions()) {
        //    if (activeSession.getId().toString().equals(cookie)) {
        //    	Core.getLogger("OauthLogin").debug("Active session found for user");
        //        session = activeSession;
        //        break;
        //   }
        //}
        
        try {
            session = Core.initializeSession(user, session != null ? session.getId().toString() : null);
        } catch (CoreException e) {
        	SSOLOGGER.error("Failed to initialize new Mendix session " + e);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            throw new RuntimeException("Single Sign On unable to create new session");
          }
        
        
        //If no session was found then we want to login using the provided username
        SSOLOGGER.info("Setting Mendix runtime cookies (XASSESSIONID, XASID and originURI)");
        response.addCookie(XAS_SESSION_ID, session.getId().toString(), "/", "", -1);
        response.addCookie(XAS_ID, "0." + Core.getXASId(), "/", "", -1);
        response.addCookie(OriginURI, OriginURIValue, "/", "", SECONDS_PER_YEAR);

        String indexconfig = (String)Core.getConfiguration().getConstantValue(INDEX_PAGE_CONSTANT);
//        String indexpage = "/index.html?profile=Responsive&cpcid=5CCD7A22-E9C6-4429-A2B4-778F0F87B4B9&customer=0007023192";
        String indexpage = redirect;
//        if (StringUtils.isNotBlank(indexconfig)) {
//            indexpage = StringUtils.trim(indexconfig);
//        } else {
//        	SSOLOGGER.warn("Missing constant value "+ INDEX_PAGE_CONSTANT);
//        }

        redirect(response, indexpage);
    }
    
    protected static void redirect(IMxRuntimeResponse response, String path) {
    	SSOLOGGER.info("Redirecting to location: "+ path);
        response.setStatus(HttpServletResponse.SC_SEE_OTHER);
        response.addHeader("location", path);
    }
}
