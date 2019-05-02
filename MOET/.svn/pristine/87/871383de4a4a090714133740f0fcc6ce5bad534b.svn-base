package saml20.implementation.common;

import com.mendix.core.Core;
import com.mendix.m2ee.api.IMxRuntimeRequest;
import com.mendix.m2ee.api.IMxRuntimeResponse;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.velocity.app.VelocityEngine;
import org.opensaml.common.SAMLException;
import org.opensaml.common.SignableSAMLObject;
import org.opensaml.saml2.core.Response;
import org.opensaml.xml.XMLObject;
import org.opensaml.xml.util.Base64;
import saml20.implementation.wrapper.MxSAMLResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.zip.Inflater;
import java.util.zip.InflaterOutputStream;
import java.util.zip.ZipException;

public class HTTPUtils {

    public static VelocityEngine getEngine() {
        VelocityEngine engine = new VelocityEngine();
        Properties p = new Properties();
        p.setProperty("file.resource.loader.path", Core.getConfiguration().getResourcesPath().getAbsolutePath() + File.separator + "SAML" + File.separator);
        p.setProperty("runtime.log", Core.getConfiguration().getTempPath().getAbsolutePath() + File.separator + "velocity.log");
        engine.init(p);

        return engine;
    }

    /**
     * Send a redirect using a meta tag.
     *
     * @param mxResponse Http response object.
     * @param url        URL to redirect to.
     * @throws IOException
     */
    public static void sendMetaRedirect(IMxRuntimeResponse mxResponse, String url, String query) throws IOException {
        mxResponse.setContentType("text/html");
        mxResponse.addHeader("Pragma", "no-cache");
        mxResponse.addHeader("Expires", "-1");
        mxResponse.addHeader("Cache-Control", "no-cache");
        mxResponse.addHeader("Cache-Control", "no-store");

        try (Writer writer = mxResponse.getWriter()) {
            writer.write("<html><head>");
            writer.write("<meta http-equiv=\"refresh\" content=\"0;url=");
            writer.write(url);
            if (query != null) {
                if (url.contains("?")) {
                    writer.write("&");
                } else {
                    writer.write("?");
                }
                writer.write(query);
            }
            writer.write("\">");
            writer.write("</head><body>");

            writer.write("</body></html>");
            writer.flush();
        }
    }

    public static boolean isSAMLResponse(HttpServletRequest request) {
        String responseParam = request.getParameter(Constants.SAML_SAMLRESPONSE);
        return responseParam != null && !responseParam.isEmpty();
    }

    public static boolean isSAMLRequest(HttpServletRequest request) {
        String responseParam = request.getParameter(Constants.SAML_SAMLREQUEST);
        return responseParam != null && !responseParam.isEmpty();
    }

    public static MxSAMLResponse extract(HttpServletRequest request) throws SAMLException {
        String samlResponse = request.getParameter(Constants.SAML_SAMLRESPONSE);
        if (samlResponse == null || samlResponse.trim().isEmpty()) {
            // 2015-05-07: Removed "temp hack" that was present here
            throw new IllegalStateException("SAMLResponse parameter cannot be null");
        }

        try {
            String xml = new String(Base64.decode(samlResponse), "UTF-8");
            XMLObject obj = SAMLUtil.unmarshallElementFromString(xml);
            if (!(obj instanceof Response)) {
                throw new IllegalArgumentException("SAMLResponse must be of type Response. Was " + obj);
            }
            return new MxSAMLResponse((Response) obj);
        } catch (UnsupportedEncodingException e) {
            throw new SAMLException(e);
        }
    }

    public static <T extends SignableSAMLObject> T extractSAMLRequest(HttpServletRequest request) throws SAMLException {
        return extractSAMLObject(request, Constants.SAML_SAMLREQUEST);
    }

    public static <T extends SignableSAMLObject> T extractSAMLResponse(HttpServletRequest request) throws SAMLException {
        return extractSAMLObject(request, Constants.SAML_SAMLRESPONSE);
    }

    @SuppressWarnings("unchecked")
    private static <T extends SignableSAMLObject> T extractSAMLObject(HttpServletRequest request, String httpParameter) throws SAMLException {
        String samlResponseBase64 = request.getParameter(httpParameter);
        byte[] samlResponseBytes = Base64.decode(samlResponseBase64);

        if (samlResponseBase64 == null || samlResponseBase64.trim().isEmpty()) {
            // 2015-05-07: Removed "temp hack" that was present here
            throw new IllegalStateException("SAMLResponse parameter cannot be null");
        }

        try {
            // BJHL 2015-10-23 For a redirect binding, the message is usually deflate compressed.
            String samlResponseXML = attemptInflate(samlResponseBytes);

            XMLObject obj = SAMLUtil.unmarshallElementFromString(samlResponseXML);
            if (obj instanceof SignableSAMLObject) {
                return (T) obj;
            } else {
                throw new IllegalArgumentException("SAMLResponse must be a SignableSAMLObject. Was " + obj);
            }
        } catch (UnsupportedEncodingException e) {
            throw new SAMLException(e);
        }
    }

    private static String attemptInflate(byte[] samlBytes) throws UnsupportedEncodingException {
        final String encoding = "UTF-8";
        try (ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();
             InflaterOutputStream inflaterStream = new InflaterOutputStream(bytesOut, new Inflater(true))) {
            inflaterStream.write(samlBytes);
            // return the uncompressed version
            return bytesOut.toString(encoding);
        } catch (ZipException e) {
            // it was not compressed, return original. If it is not UTF-8, this will throw UnsupportedEncodingException
            return new String(samlBytes, encoding);
        } catch (UnsupportedEncodingException e) {
            // it was compressed but wasn't UTF-8, rethrow
            throw e;
        } catch (IOException e) {
            // some other exception occurred during decompression, assume it's not compressed and return original
            return new String(samlBytes, encoding);
        }
    }

    public static void redirect(IMxRuntimeResponse response, String path) {
        response.setStatus(HttpServletResponse.SC_SEE_OTHER);
        response.addHeader("location", path);
    }

    public static String[] decodeDiscoveryValue(String value) {
        if (value == null) {
            return new String[0];
        }
        String[] ids = value.split(" ");
        for (int i = 0; i < ids.length; i++) {
            ids[i] = new String(Base64.decode(ids[i]));
        }
        return ids;
    }

    public static String[] extractResourceArguments(IMxRuntimeRequest request) {
        String requestResourcePath = request.getResourcePath();
        String[] resourceArgs = requestResourcePath.substring(1).split("/");
        switch (resourceArgs.length) {
            // When there are no arguments or just one we only have the /SSO/ in the url, so do nothing.
            case 0:
            case 1:
                resourceArgs = new String[]{"", ""};
                break;
            case 2:
                resourceArgs = new String[]{resourceArgs[1], ""};
                break;
            default:
                resourceArgs = new String[]{resourceArgs[1], resourceArgs[2]};
                break;
        }

        return resourceArgs;
    }

    public static String appendParamToUrl(String url, String paramName,
                                          String paramValue) {
        return url + (url.contains("?") ? "&" : "?") + urlEncode(paramName) + "=" + urlEncode(paramValue);
    }

    static void includeHeaders(HttpMethodBase request, Map<String, String> headers) {
        if (headers != null) {
            for (Entry<String, String> e : headers.entrySet())
                request.addRequestHeader(e.getKey(), e.getValue());
        }
    }

    public static String urlEncode(String value) {
        try {
            return URLEncoder.encode(value, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

}
