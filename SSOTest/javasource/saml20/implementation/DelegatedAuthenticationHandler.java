package saml20.implementation;

import com.mendix.core.Core;
import com.mendix.logging.ILogNode;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.xerces.parsers.DOMParser;
import org.opensaml.common.SAMLException;
import org.opensaml.xml.ConfigurationException;
import org.w3c.dom.*;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import saml20.implementation.common.Constants;
import saml20.implementation.delegation.*;
import saml20.implementation.security.SAMLSessionInfo;
import saml20.implementation.wrapper.MxResource;
import saml20.implementation.wrapper.MxSAMLAssertion;

import javax.xml.soap.SOAPException;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;

public class DelegatedAuthenticationHandler extends SAMLHandler {

    protected static ILogNode _logNode = Core.getLogger(Constants.LOGNODE);

    // XML namespace context
    private static final SAMLNamespaceContext NAMESPACE_CONTEXT = new SAMLNamespaceContext();
    private static final XPathExpressionExecutor EXPRESSION_POOL = new XPathExpressionPool(NAMESPACE_CONTEXT);

    private DOMImplementationLS domLoadSaveImpl = null;
    private static final String SOAP_PREFIX = "soap";

    /**
     * Public default constructor that performs basic initialization
     */
    public DelegatedAuthenticationHandler() {
        DOMImplementationRegistry registry;
        try {
            registry = DOMImplementationRegistry.newInstance();
            this.domLoadSaveImpl = (DOMImplementationLS) registry.getDOMImplementation("LS");
        } catch (ClassCastException ex) {
            _logNode.error("Unable to initialize XML serializer implementation.  Make sure that the correct jar files are present.", ex);
        } catch (ClassNotFoundException ex) {
            _logNode.error("Unable to initialize XML serializer implementation.  Make sure that the correct jar files are present.", ex);
        } catch (InstantiationException ex) {
            _logNode.error("Unable to initialize XML serializer implementation.  Make sure that the correct jar files are present.", ex);
        } catch (IllegalAccessException ex) {
            _logNode.error("Unable to initialize XML serializer implementation.  Make sure that the correct jar files are present.", ex);
        }
    }

    @Override
    public void handleRequest(SAMLRequestContext context) throws SAMLException {

        String samlSessionID = context.getSAMLSessionID();
        SAMLSessionInfo sessionInfo = context.getSessionManager().getSessionDetails(samlSessionID);
        if (sessionInfo == null)
            throw new SAMLException("Unable to find a session for session id: " + samlSessionID);

        sessionInfo.setDeleteLock();
        MxSAMLAssertion samlAssertion = sessionInfo.getAssertion();

        DelegatedSAMLAuthenticationState authnState = new DelegatedSAMLAuthenticationState();
        // The following represents the entire delegated authentication flow
        if (getSOAPRequest(context, sessionInfo, authnState)) {
            String token = this.authenticate(context, sessionInfo, samlAssertion, authnState);
            if (token == null)
                throw new SAMLException("Unable to authenticate because no Delegated Token could be retrieved.");

            // _logNode.info( doTestCall( sessionInfo.getClientConnection(),
            // "https://asapis-dev.mit.edu/asapis/v1.0/Rfc/%7B%22rfcName%22:%22Z_VP_GET_KERBEROS_INFO%22,%22input%22:%7B%22IV_KERBEROS%22:%22sbmit%22%7D%7D",
            // null ) );
        }
    }

    // private static String doTestCall( HttpClient clientConnection, String resourceURL, String postMessage ) throws
    // SAMLException {
    // try {
    // HttpMethod method;
    // if( postMessage == null )
    // method = new GetMethod(resourceURL);
    // else {
    // method = new PostMethod(resourceURL);
    // StringRequestEntity postData = new StringRequestEntity(postMessage, "text/json", "UTF-8");
    // ((PostMethod)method).setRequestEntity(postData);
    // }
    // clientConnection.executeMethod(method);
    //
    // return "[" + method.getStatusCode() + "] " + method.getResponseBodyAsString();
    // }
    // catch( Exception ex ) {
    // // There is nothing that can be done about this exception other than to log it
    // // Exception must be caught and not rethrown to allow normal processing to continue
    // throw new
    // SAMLException("Exception caught while sending the delegated authentication assertion to the service provider.",
    // ex);
    // }
    // }

    private String authenticate(SAMLRequestContext context, SAMLSessionInfo sessionInfo, MxSAMLAssertion samlAssertion, DelegatedSAMLAuthenticationState authnState) throws SAMLException {
        try {
            // The following represents the entire delegated authentication flow
            if (getIDP(samlAssertion, authnState) && processSOAPRequest(context, samlAssertion, authnState) && getSOAPResponse(context, sessionInfo, authnState) && processSOAPResponse(sessionInfo, authnState)) {

                // We do not need to do this step. Whenever we get here we have the SAML authentication information and
                // we can use that to send back to the user
                PostMethod response = sendSOAPResponse(sessionInfo, authnState);
                int resultCode = response.getStatusLine().getStatusCode();
                _logNode.info("Reponse: " + resultCode);
                try {
                    String result = response.getResponseBodyAsString();
                    _logNode.debug("Got SOAP response:\n" + result);
                } catch (IOException e) {
                    _logNode.error("Unable to get the response");
                }
                return authnState.getModifiedSOAPResponse();
            }

            return null;
        } catch (ConfigurationException e) {
            throw new SAMLException(e);
        }
    }

    /**
     * This method makes a request for a resource, but assuming that the resource is protected, it actually expects to
     * receive a SOAP request for authentication. This is referred to as a PAOS (reversed SOAP) request because the SOAP
     * request is returned as an http response.
     *
     * @param samlSession
     * @param resource
     * @param authnState
     * @return
     * @throws SAMLException
     * @throws MalformedURLException
     */
    private static boolean getSOAPRequest(SAMLRequestContext context, SAMLSessionInfo sessionInfo, DelegatedSAMLAuthenticationState authnState) throws SAMLException {
        MxResource resource = context.getResource();
        _logNode.debug("getSOAPRequest from " + resource.getResourceURL());
        HttpClient httpClient = sessionInfo.getClientConnection();
        GetMethod method = new GetMethod(resource.getResourceURL());
        method.addRequestHeader(new Header("Accept", Constants.HTTP_HEADER_PAOS_CONTENT_TYPE));
        method.addRequestHeader(new Header("PAOS", Constants.HTTP_HEADER_PAOS));

        try {
            // There is no need to check the HTTP response status because the HTTP
            // client will handle normal HTTP protocol flow, including redirects
            // In case of error, HTTP client will throw an exception
            try {
                try {
                    httpClient.executeMethod(method);
                    authnState.setSoapRequest(method.getResponseBody());

                    if (_logNode.isTraceEnabled())
                        _logNode.trace("Received Response: " + method.getResponseBodyAsString());
                } catch (HttpException e) {
                    _logNode.error(e);
                }
            } finally {
                method.releaseConnection();
            }
        } catch (Exception ex) {
            // There is nothing that can be done about this exception other than to log it
            // Exception must be caught and not rethrown to allow normal processing to continue
            throw new SAMLException("Exception caught when trying to retrieve the resource.", ex);
        }
        return true;
    }

    /**
     * This method takes the SOAP request that come from the WSP and removes the elements that need to be removed per
     * the SAML Profiles spec.
     *
     * @param samlSession
     * @param authnState
     * @return true, if successful
     * @throws SAMLException
     */
    private boolean processSOAPRequest(SAMLRequestContext context, MxSAMLAssertion samlAssertion, DelegatedSAMLAuthenticationState authnState) throws SAMLException {
        _logNode.debug("Step 3 of 5: Process SOAP Request");
        try {
            String expression = "/S:Envelope/S:Header/paos:Request";
            Document dom = authnState.getSoapRequestDom();
            Node node = EXPRESSION_POOL.evaluate(expression, dom, XPathConstants.NODE);

            if (node != null) {
                // Save the response consumer URL to samlSession
                String responseConsumerURL = node.getAttributes().getNamedItem("responseConsumerURL").getTextContent();
                _logNode.debug("Loaded response consumer URL " + responseConsumerURL);
                authnState.setResponseConsumerURL(responseConsumerURL);
                // Save the PAOS MessageID, if present
                Node paosMessageID = node.getAttributes().getNamedItem("messageID");

                if (paosMessageID != null)
                    authnState.setPaosMessageID(paosMessageID.getTextContent());
                else
                    authnState.setPaosMessageID(null);

                // This removes the paos:Request node
                node.getParentNode().removeChild(node);

                // Retrieve the RelayState cookie for sending it back to the WSP with the SOAP Response
                expression = "/S:Envelope/S:Header/ecp:RelayState";
                node = EXPRESSION_POOL.evaluate(expression, dom, XPathConstants.NODE);
                if (node != null) {
                    Element relayStateElement = (Element) node;
                    authnState.setRelayStateElement(relayStateElement);
                    node.getParentNode().removeChild(node);
                }

                // On to the ecp:Request for removal
                expression = "/S:Envelope/S:Header/ecp:Request";
                node = EXPRESSION_POOL.evaluate(expression, dom, XPathConstants.NODE);
                node.getParentNode().removeChild(node);

                // Now add some namespace bindings to the SOAP Header
                expression = "/S:Envelope/S:Header";
                Element soapHeader = EXPRESSION_POOL.evaluate(expression, dom, XPathConstants.NODE);

                // Add new elements to S:Header
                Element newElement = dom.createElementNS(NAMESPACE_CONTEXT.getNamespaceURI("sbf"), "sbf:Framework");
                newElement.setAttribute("version", "2.0");
                soapHeader.appendChild(newElement);
                newElement = dom.createElementNS(NAMESPACE_CONTEXT.getNamespaceURI("sb"), "sb:Sender");
                newElement.setAttribute("providerID", context.getSpMetadata().getEntityID());
                soapHeader.appendChild(newElement);
                newElement = dom.createElementNS(NAMESPACE_CONTEXT.getNamespaceURI("wsa"), "wsa:MessageID");
                String messageID = generateMessageID();
                newElement.setTextContent(messageID);
                soapHeader.appendChild(newElement);
                newElement = dom.createElementNS(NAMESPACE_CONTEXT.getNamespaceURI("wsa"), "wsa:Action");
                newElement.setTextContent("urn:liberty:ssos:2006-08:AuthnRequest");
                soapHeader.appendChild(newElement);

                // This is the wsse:Security element
                Element securityElement = dom.createElementNS("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd", "wsse:Security");
                securityElement.setAttribute(soapHeader.getPrefix() + ":mustUnderstand", "1");
                Element createdElement = dom.createElement("wsu:Created");
                // The examples use Zulu time zone, not local
                TimeZone zuluTimeZone = TimeZone.getTimeZone("Zulu");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SS'Z'");
                sdf.setTimeZone(zuluTimeZone);
                createdElement.setTextContent(sdf.format(new Date()));
                newElement = dom.createElementNS("http://www.docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd", "wsu:Timestamp");
                newElement.appendChild(createdElement);
                securityElement.appendChild(newElement);
                // Finally, insert the original SAML assertion
                Node samlAssertionNode = dom.importNode(samlAssertion.getAssertion().getDOM(), true);
                securityElement.appendChild(samlAssertionNode);
                soapHeader.appendChild(securityElement);

                // Store the modified SOAP Request in the SAML Session
                String modifiedSOAPRequest = writeDomToString(dom);
                authnState.setModifiedSOAPRequest(modifiedSOAPRequest);
                _logNode.debug("Completed processing of SOAP request");
                return true;
            }
            _logNode.debug("Failed to process SOAP request using expression " + expression);
        } catch (XPathExpressionException ex) {
            throw new SAMLException("Programming error.  Invalid XPath expression.", ex);
        }
        return false;
    }

    /**
     * @return String containing a UUID
     */
    private static String generateMessageID() {
        UUID uuid = UUID.randomUUID();
        String messageID = "urn:uuid:" + uuid.toString();
        return messageID;
    }

    /**
     * This method takes the SOAP AuthnRequest, sends it to the IdP, and retrieves the result. This method does not
     * process the result.
     *
     * @param samlSession SAML session
     * @param authnState
     * @return true, if successful
     * @throws SAMLException
     */
    private static boolean getSOAPResponse(SAMLRequestContext context, SAMLSessionInfo sessionInfo, DelegatedSAMLAuthenticationState authnState) throws SAMLException {
        _logNode.debug("Step 4 of 5: Get SOAP response from IDP");

        String result = null;
        HttpMethodParams params = new HttpMethodParams();
        params.setVersion(org.apache.commons.httpclient.HttpVersion.HTTP_1_1);
        params.setContentCharset("UTF-8");
        params.setParameter("SOAPAction", "urn:liberty:ssos:2006-08:AuthnRequest");
        HttpClient client = sessionInfo.getClientConnection();

        try {
            _logNode.debug("Getting SOAP response from " + authnState.getIdpLocation() + " with POST body:\n" + authnState.getModifiedSOAPRequest());
            // setupIdPClientConnection(client, samlSession, authnState);
            PostMethod method = new PostMethod(authnState.getIdpLocation());
            method.setParams(params);
            int resultCode;
            try {
                method.setRequestHeader("Content-Type", "text/xml");
                StringRequestEntity postData = new StringRequestEntity(authnState.getModifiedSOAPRequest(), "text/xml", "UTF-8");
                method.setRequestEntity(postData);
                client.executeMethod(method);
                resultCode = method.getStatusCode();

                if (resultCode >= HttpStatus.SC_OK && resultCode < 300) {
                    result = method.getResponseBodyAsString();
                    _logNode.debug("Got SOAP response:\n" + result);
                    authnState.setSoapResponse(result);
                    return true;
                }
            } finally {
                method.releaseConnection();
            }

            throw new SAMLException("Unsupported HTTP result code when retrieving the resource: " + resultCode + ".");
        } catch (Exception ex) {
            throw new SAMLException("Exception caught when trying to retrieve the resource: " + context.getResource().getResourceURL() + ", by calling url: " + authnState.getIdpLocation(), ex);
        }
    }

    /**
     * This method processes the SOAP response from the IdP, and converts it for presenting it back to the WSP that
     * requested a delegated SAML assertion.
     *
     * @param samlSession SAML session
     * @param authnState
     * @return true, if successful
     * @throws SAMLException
     */
    private boolean processSOAPResponse(SAMLSessionInfo sessionInfo, DelegatedSAMLAuthenticationState authnState) throws SAMLException {
        _logNode.debug("Step 5 of 5: Processing SOAP response");

        try {
            String expression = "/soap:Envelope/soap:Header/ecp:Response";
            String expression2 = "/soap:Envelope/*[local-name()='Body']/*[local-name()='Response']/*[local-name()='Status']";
            Document doc;
            try (InputStream is = new ByteArrayInputStream(authnState.getSoapResponse().getBytes())) {
                InputSource source = new InputSource(is);

                DOMParser parser = new DOMParser();
                parser.setFeature("http://xml.org/sax/features/namespaces", true);
                parser.parse(source);

                doc = parser.getDocument();
            }

            String validationErrors = "";
            Node statusNode = EXPRESSION_POOL.evaluate(expression2, doc, XPathConstants.NODE);
            if (statusNode != null) {
                NodeList statusElements = statusNode.getChildNodes();
                for (int i = 0; i < statusElements.getLength(); i++) {
                    Node node = statusElements.item(i);
                    if ("StatusMessage".equals(node.getLocalName())) {

                        String messageContent = node.getTextContent();
                        if (messageContent != null && messageContent.contains("error"))
                            validationErrors += messageContent + "\r\n";
                    }
                }
            }

            if ("".equals(validationErrors)) {
                Node node = EXPRESSION_POOL.evaluate(expression, doc, XPathConstants.NODE);
                if (node != null) {
                    String responseConsumerURL = node.getAttributes().getNamedItem("AssertionConsumerServiceURL").getTextContent();

                    _logNode.debug("Found " + expression + " node found in SOAP response.");

                    if (responseConsumerURL != null && responseConsumerURL.equals(authnState.getResponseConsumerURL())) {
                        _logNode.debug("responseConsumerURL " + responseConsumerURL + " matches " + authnState.getResponseConsumerURL());

                        // Retrieve and save the SOAP prefix used
                        String soapPrefix = node.getParentNode().getPrefix();
                        Element ecpResponse = (Element) node;
                        Element soapHeader = (Element) ecpResponse.getParentNode();
                        removeAllChildren(soapHeader);

                        // Now on to the PAOS Response
                        Element paosResponse = doc.createElementNS("urn:liberty:paos:2003-08", "paos:Response");
                        paosResponse.setAttribute(soapPrefix + ":mustUnderstand", "1");
                        paosResponse.setAttribute(soapPrefix + ":actor", "http://schemas.xmlsoap.org/soap/actor/next");

                        // messageID is optional
                        if (authnState.getPaosMessageID() != null)
                            paosResponse.setAttribute("refToMessageID", authnState.getPaosMessageID());

                        soapHeader.appendChild(paosResponse);

                        if (authnState.getRelayStateElement() != null) {
                            Node relayState = doc.importNode(authnState.getRelayStateElement(), true);
                            soapHeader.appendChild(relayState);
                        }

                        // Store the modified SOAP Request in the SAML Session
                        String modifiedSOAPResponse = writeDomToString(doc);
                        authnState.setModifiedSOAPResponse(modifiedSOAPResponse);
                        _logNode.trace("Finished processing: " + responseConsumerURL + " modified SOAP response: " + modifiedSOAPResponse);

                        return true;
                    }

                    _logNode.debug("responseConsumerURL " + responseConsumerURL + " does not match " + authnState.getResponseConsumerURL());
                    Document soapFaultMessage = createSOAPFaultDocument("AssertionConsumerServiceURL attribute missing or not matching the expected value.");
                    Element soapHeader = (Element) soapFaultMessage.getFirstChild().getFirstChild();
                    // Now on to the PAOS Response
                    Element paosResponse = soapFaultMessage.createElementNS("urn:liberty:paos:2003-08", "paos:Response");
                    paosResponse.setAttribute(SOAP_PREFIX + ":mustUnderstand", "1");
                    paosResponse.setAttribute(SOAP_PREFIX + ":actor", "http://schemas.xmlsoap.org/soap/actor/next");

                    // messageID is optional
                    if (authnState.getPaosMessageID() != null) {
                        paosResponse.setAttribute("refToMessageID", authnState.getPaosMessageID());
                    }

                    soapHeader.appendChild(paosResponse);

                    if (authnState.getRelayStateElement() != null) {
                        Node relayState = soapFaultMessage.importNode(authnState.getRelayStateElement(), true);
                        soapHeader.appendChild(relayState);
                    }
                    // Store the SOAP Fault in the SAML Session
                    String modifiedSOAPResponse = writeDomToString(soapFaultMessage);
                    authnState.setModifiedSOAPResponse(modifiedSOAPResponse);
                    sendSOAPFault(sessionInfo, authnState);
                    return false;

                }
            }

            // There was no response for the ECP. Look for and propagate an error.
            String errorMessage = getSOAPFaultAsString(doc);
            if (errorMessage == null)
                errorMessage = "";
            errorMessage = "\r\n" + validationErrors;

            _logNode.warn("Eror while evaluating SOAP response. Error: " + errorMessage);

            if (errorMessage != null && !errorMessage.trim().isEmpty()) {
                throw new SAMLException(errorMessage);
            }

            return false;
        } catch (XPathExpressionException ex) {
            throw new SAMLException("XPath programming error.", ex);
        } catch (SAXNotRecognizedException ex) {
            throw new SAMLException("XPath programming error.", ex);
        } catch (SAXNotSupportedException ex) {
            throw new SAMLException("Exception caught when trying to process the SOAP esponse from the IdP.", ex);
        } catch (SAXException ex) {
            throw new SAMLException("Exception caught when trying to process the SOAP esponse from the IdP.", ex);
        } catch (DOMException ex) {
            throw new SAMLException("Exception caught when trying to process the SOAP esponse from the IdP.", ex);
        } catch (IOException ex) {
            throw new SAMLException("This exception should not ever really occur, as the only I/O this method performs is on a ByteArrayInputStream.", ex);
        } catch (SOAPException ex) {
            throw new SAMLException("Error processing a SOAP message.", ex);
        }
    }

    /**
     * Utility method for serializing DOM to a String
     *
     * @param doc Document to serialize
     * @return XML document as a String
     */
    private String writeDomToString(Node doc) {
        LSSerializer writer = this.domLoadSaveImpl.createLSSerializer();
        DOMConfiguration domConfig = writer.getDomConfig();
        domConfig.setParameter("xml-declaration", false);
        String xmlString = writer.writeToString(doc);
        return xmlString;
    }

    /**
     * Despite its name, this method performs two tasks: 1)sending the SOAP response to the WSP is the final step of the
     * delegated SAML authentication 2)when this succeeds, the WSP returns the resource originally requested, so this
     * also means that upon return from this method, the DelegatedSAMLAuthenticationState object will contain a String
     * representation of the requested resource.
     *
     * @param samlSession SAML session representing this user and a SAML assertion for the WSP on behalf of this user.
     * @param authnState  DelegatedSAMLAuthenticationState object that tracks the state of the authentication
     * @return HttpResponse from the WSP after authentication. Depending on the HTTP method used, this will either
     * include an HTTP 302 redirect to the originally requested resource or a result of submitting form data in
     * case if the initial request was from HTTP POST.
     * @throws SAMLException
     */
    private static PostMethod sendSOAPResponse(SAMLSessionInfo sessionInfo, DelegatedSAMLAuthenticationState authnState) throws SAMLException {
        PostMethod method = new PostMethod(authnState.getResponseConsumerURL());
        method.setRequestHeader("Content-Type", Constants.HTTP_HEADER_PAOS_CONTENT_TYPE);

        try {
            StringRequestEntity postData = new StringRequestEntity(authnState.getModifiedSOAPResponse(), "text/xml", "UTF-8");
            method.setRequestEntity(postData);
            sessionInfo.getClientConnection().executeMethod(method);

            return method;
        } catch (Exception ex) {
            throw new SAMLException("Exception caught while sending the delegated authentication assertion to the service provider.", ex);
        }
    }

    /**
     * This method sends the SOAP response to the WSP without retrieving the result. This method assumes that it is
     * merely communicating a failure to the WSP, and the SAMLSession contains the failure message, a SOAP Fault
     *
     * @param samlSession
     * @return true, if successful
     * @throws SAMLException
     */
    private static boolean sendSOAPFault(SAMLSessionInfo sessionInfo, DelegatedSAMLAuthenticationState authnState) throws SAMLException {
        PostMethod method = new PostMethod(authnState.getResponseConsumerURL());
        method.setRequestHeader("Content-Type", Constants.HTTP_HEADER_PAOS_CONTENT_TYPE);
        method.setRequestHeader("PAOS", Constants.HTTP_HEADER_PAOS);

        try {
            StringRequestEntity postData = new StringRequestEntity(authnState.getModifiedSOAPResponse(), "text/xml", "UTF-8");
            method.setRequestEntity(postData);
            sessionInfo.getClientConnection().executeMethod(method);

            return true;
        } catch (Exception ex) {
            throw new SAMLException("Exception caught while sending the delegated authentication assertion to the service provider.", ex);
        }
    }

    /*
     * Empties the contents of an element
     */
    private static void removeAllChildren(Element element) {
        Node child = element.getFirstChild();

        while (child != null) {
            Node next = child.getNextSibling();
            element.removeChild(child);
            child = next;
        }
    }

    /**
     * Assume that the InputStream has a SOAP fault message and return a String suitable to present as an exception
     * message
     *
     * @param doc InputStream that contains a SOAP message
     * @return String containing a formated error message
     * @throws IOException
     * @throws SOAPException
     * @throws SAMLException
     */
    private String getSOAPFaultAsString(Document doc) throws SAMLException {
        try {
            String expressionForFault = "/soap:Envelope/soap:Body/soap:Fault";
            Element faultNode = EXPRESSION_POOL.evaluate(expressionForFault, doc, XPathConstants.NODE);
            if (faultNode != null) {
                String code = null, string = null, actor = null;
                NodeList nList = faultNode.getElementsByTagNameNS("*", "faultcode");
                if (nList.getLength() > 0)
                    code = nList.item(0).getTextContent();

                nList = faultNode.getElementsByTagNameNS("*", "faultstring");
                if (nList.getLength() > 0)
                    string = nList.item(0).getTextContent();

                nList = faultNode.getElementsByTagNameNS("*", "faultactor");
                if (nList.getLength() > 0)
                    actor = nList.item(0).getTextContent();

                String formatedMessage = "SOAP transaction resulted in a SOAP fault.";

                if (code != null)
                    formatedMessage += "  Code=\"" + code + ".\"";

                if (string != null)
                    formatedMessage += "  String=\"" + string + ".\"";

                if (actor != null)
                    formatedMessage += "  Actor=\"" + actor + ".\"";

                return formatedMessage;
            }
        } catch (Exception e) {
            _logNode.error("Error while processin the response: \r\n" + writeDomToString(doc), e);
            throw new SAMLException(e);
        }

        return null;
    }

    private static Document createSOAPFaultDocument(String faultString) throws SOAPException {
        // TODO implement this create SOAP Fault Document function

        // MessageFactory factory = MessageFactory.newInstance();
        // SOAPMessage message = factory.createMessage();
        // SOAPPart sp = message.getSOAPPart();
        // SOAPEnvelope se = sp.getEnvelope();
        // se.setPrefix(SOAP_PREFIX);
        // se.getHeader().detachNode();
        // se.addHeader();
        // se.getBody().detachNode();
        // SOAPBody body = se.addBody();
        // SOAPFault fault = body.addFault();
        // Name faultCode = se.createName("Client", null, SOAPConstants.URI_NS_SOAP_ENVELOPE);
        // fault.setFaultCode(faultCode);
        // fault.setFaultString(faultString);
        // return se.getOwnerDocument();

        return null;
    }

    /**
     * This method extracts the IDP from the SAML assertion
     *
     * @param authnState
     * @throws SAMLException
     * @throws ConfigurationException
     */
    private static boolean getIDP(MxSAMLAssertion samlAssertion, DelegatedSAMLAuthenticationState authnState) throws SAMLException, ConfigurationException {
        _logNode.debug("Step 1 of 5: get IDP from SAML Assertion");
        if (samlAssertion == null)
            throw new SAMLException("No Assertion");

        AssertionIdpResolverImpl resolver = new AssertionIdpResolverImpl(EXPRESSION_POOL);

        resolver.resolve(samlAssertion.getAssertion().getDOM().getOwnerDocument(), samlAssertion, authnState);

        return true;
    }

}