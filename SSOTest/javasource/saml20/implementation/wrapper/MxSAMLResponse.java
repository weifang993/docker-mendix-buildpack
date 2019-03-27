package saml20.implementation.wrapper;

import java.security.cert.X509Certificate;
import java.util.List;

import org.opensaml.common.SAMLException;
import org.opensaml.saml2.core.Issuer;
import org.opensaml.saml2.core.Response;
import org.opensaml.saml2.core.StatusCode;
import org.opensaml.xml.security.credential.Credential;
import org.opensaml.xml.validation.ValidationException;

import saml20.implementation.common.Constants;
import saml20.implementation.common.Constants.ValidationLevel;
import saml20.implementation.metadata.IdpMetadata;
import saml20.proxies.EntityDescriptor;
import saml20.proxies.SAMLRequest;
import saml20.proxies.SSOConfiguration;

import com.mendix.core.Core;
import com.mendix.core.CoreException;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.systemwideinterfaces.core.IMendixIdentifier;
import com.mendix.systemwideinterfaces.core.IMendixObject;

public class MxSAMLResponse extends MxSAMLObject {
	private final Response response;

	private MxSAMLAssertion assertion;

	public MxSAMLResponse( Response response ) {
		super(response);
		this.response = response;
	}

	protected void validateResponse( String requestId, String expectedDestination, boolean allowPassive ) throws ValidationException {
		String statusCode = this.response.getStatus().getStatusCode().getValue();
		String msg = "";

		if ( !StatusCode.SUCCESS_URI.equals(statusCode) ) {

			StatusCode is = this.response.getStatus().getStatusCode().getStatusCode();
			if ( is == null || !(StatusCode.NO_PASSIVE_URI.equals(is.getValue()) && allowPassive) ) {
				msg = this.response.getStatus().getStatusMessage() == null ? "" : this.response.getStatus().getStatusMessage().getMessage();
				throw new ValidationException("Got StatusCode " + statusCode + (is == null ? "" : "/" + is.getValue()) + " should be " + StatusCode.SUCCESS_URI + ". Message: [" + msg + "]  ID:[" + this.response.getID() + "]");
			}
		}
		if ( !isDestinationOK(expectedDestination) ) {
			throw new ValidationException("Wrong destination. Expected " + expectedDestination + ", was " + this.response.getDestination() + " ID:[" + this.response.getID() + "]");
		}

		if ( requestId != null && !requestId.equals(this.response.getInResponseTo()) ) {
			throw new ValidationException("Wrong InResponseTo. Expected " + requestId + ", was " + this.response.getInResponseTo() + " ID:[" + this.response.getID() + "]");
		}

	}

	public void validateResponse( String expectedDestination, IdpMetadata metadata, String entityId, boolean allowPassive ) throws ValidationException, SAMLException {
		validateResponse(null, expectedDestination, allowPassive);

		if ( this.response.getAssertions().isEmpty() && !isPassive() ) {
			throw new ValidationException("Response must contain an Assertion. If the Response contains an encrypted Assertion, decrypt it before calling validate." + " ID:[" + this.response.getID() + "]");
		}

		if ( !hasSignature() && isPassive() && allowPassive ) {
			return;
		}

		if ( hasSignature() || isPassive() ) {
			boolean valid = false;
			for( X509Certificate certificate : metadata.getSigningCertificates(entityId) ) {
				if ( verifySignature(certificate) ) {
					valid = true;
					break;
				}
			}

			// Fall back to validate against all other certificates
			if ( !valid ) {
				for( X509Certificate certificate : metadata.getCertificates(entityId) ) {
					if ( verifySignature(certificate) ) {
						valid = true;
						break;
					}
				}

				if ( !valid ) {
					// none of the available certificates matched the signature, or other failure
					throw new ValidationException("The response is not signed correctly" + " ID:[" + this.response.getID() + "]");
				}
			}
		}

		// if the full response does not have a signature and it is not a passive response
		else {
			if ( !this.response.getAssertions().isEmpty() ) {
				boolean valid = false;

				for( X509Certificate certificate : metadata.getSigningCertificates(entityId) ) {
					if ( getAssertion(null).verifySignature(certificate) ) {
						valid = true;
						break;
					}
				}

				// Fall back to validate against all other certificates
				if ( !valid ) {
					for( X509Certificate certificate : metadata.getCertificates(entityId) ) {
						if ( getAssertion(null).verifySignature(certificate) ) {
							valid = true;
							break;
						}
					}
					if ( !valid )
						throw new ValidationException("The assertion is not signed correctly");
				}
			}
		}
	}

	/**
	 * Resolve the IdP Entity id.  The preferred resolution is to retrieve the SSOConfig and EntityDescriptor by the corresponding SAML request. 
	 * If nothing has been found, it will fallback on the Issuer from the XML message.
	 *  
	 * @param context
	 * @param correspondingSAMLRequest
	 * @return
	 * @throws SAMLException
	 */
	public String getOrigalIdpEntityId( IContext context, SAMLRequest correspondingSAMLRequest ) throws SAMLException {

		String issuerName = null;
		if ( correspondingSAMLRequest != null ) {
			IMendixIdentifier ssoConfigId = correspondingSAMLRequest.getMendixObject().getValue(context, SAMLRequest.MemberNames.SAMLRequest_SSOConfiguration.toString());
			if ( ssoConfigId != null ) {
				List<IMendixObject> result;
				try {
					result = Core.retrieveXPathQueryEscaped(context, "//%s[%s=%s]", EntityDescriptor.entityName, SSOConfiguration.MemberNames.SSOConfiguration_PreferedEntityDescriptor.toString(), String.valueOf(ssoConfigId.toLong()));

					for( IMendixObject obj : result ) {
						issuerName = obj.getValue(context, EntityDescriptor.MemberNames.entityID.toString());
						break;
					}
				}
				catch( CoreException e ) {
					_logNode.error("Unabel to find the preferred Entity descriptor for message: " + this.response.getInResponseTo(), e); 
				}
			}
		}

		/*
		 * Fallback necessary in case the SAML request is no longer valid, or unsolicited requests are allowed.
		 */
		if( issuerName == null || issuerName.isEmpty() ) {
			Issuer issuer = null;
			if ( !this.response.getAssertions().isEmpty() ) {
				issuer = this.response.getAssertions().get(0).getIssuer();
			}
			if ( issuer == null ) {
				issuer = this.response.getIssuer();
			}
			
			if( issuer != null )
				issuerName = issuer.getValue();
		}
		
		if( issuerName != null )
			return issuerName;
		
		throw new SAMLException("SAML Response does not contain a issuer, this is required for unsolicited Responses");

	}

	public boolean isDestinationOK( String destination ) {
		if ( this.response.getDestination() == null )
			return true;

		if( Constants.validationLevel == ValidationLevel.Loose )
			return true;
		else 
			return this.response.getDestination() != null && this.response.getDestination().equals(destination);
	}

	public boolean isPassive() {
		if ( this.response.getStatus() == null )
			return false;
		if ( this.response.getStatus().getStatusCode() == null )
			return false;
		if ( this.response.getStatus().getStatusCode().getStatusCode() == null )
			return false;
		return StatusCode.NO_PASSIVE_URI.equals(this.response.getStatus().getStatusCode().getStatusCode().getValue());
	}

	/**
	 * Get the response assertion.
	 * 
	 * @param credential
	 * @throws SAMLException
	 * @throws ValidationException
	 */
	public MxSAMLAssertion getAssertion( Credential credential ) throws ValidationException, SAMLException {
		if ( this.assertion != null )
			return this.assertion;

		if ( credential != null )
			MxSAMLEncryptedAssertion.decryptAssertion(this.response, credential, true);
		// TODO it is also possible to only encrypt attributes and not the full assertion
		// element. Do we want to support encrypted attributes?

		/*
		 * Intentional fall through during the initial decryption, in case we decrypted the response, the assertion is
		 * added to the original response. The MxSAMLAssertion.fromResponse function is supposed to evaluate all
		 * possible combinations of having more assertions.
		 */
		if ( this.assertion != null ) {
			if ( _logNode.isDebugEnabled() )
				_logNode.debug("Found encrypted assertion, returning decrypted");
			return this.assertion;
		}

		return MxSAMLAssertion.fromResponse(this.response);
	}

	public Response getResponse() {
		return this.response;
	}
}
