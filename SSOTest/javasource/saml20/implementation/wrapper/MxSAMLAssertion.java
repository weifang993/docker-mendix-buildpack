package saml20.implementation.wrapper;

import java.util.List;

import org.joda.time.DateTime;
import org.opensaml.common.SAMLException;
import org.opensaml.common.SAMLVersion;
import org.opensaml.saml2.core.Assertion;
import org.opensaml.saml2.core.Audience;
import org.opensaml.saml2.core.AudienceRestriction;
import org.opensaml.saml2.core.AuthnStatement;
import org.opensaml.saml2.core.Conditions;
import org.opensaml.saml2.core.Issuer;
import org.opensaml.saml2.core.NameID;
import org.opensaml.saml2.core.Response;
import org.opensaml.saml2.core.Subject;
import org.opensaml.saml2.core.SubjectConfirmation;
import org.opensaml.saml2.core.SubjectConfirmationData;
import org.opensaml.xml.validation.ValidationException;

import saml20.implementation.SAMLRequestContext;
import saml20.implementation.common.Constants;
import saml20.implementation.common.Constants.ValidationLevel;


public class MxSAMLAssertion extends MxSAMLObject {

	private final Assertion assertion;

	public MxSAMLAssertion( Assertion assertion ) {
		super(assertion);
		this.assertion = assertion;
	}

	public static MxSAMLAssertion fromResponse( Response response ) {
		if ( response.getAssertions().isEmpty() ) {
			return null;
		}
		
		Assertion assertion = response.getAssertions().get(0);
		return new MxSAMLAssertion(assertion);
	}

	public Assertion getAssertion() {
		return this.assertion;
	}

	public void validateAssertion( SAMLRequestContext context ) throws ValidationException, SAMLException {
		String spEntityID = context.getSpMetadata().getEntityID(), 
				spAssertionConsumerURL = context.getSpMetadata().getAssertionConsumerServiceLocation(0);
		
		try {
			this.assertion.validate(false);
		}
		catch( org.opensaml.xml.validation.ValidationException e ) {
			throw new ValidationException(e);
		}
		// The SAML version must be 2.0
		if ( !SAMLVersion.VERSION_20.equals(this.assertion.getVersion()) ) {
			throw new ValidationException("The assertion must be version 2.0. Was " + this.assertion.getVersion());
		}
		// There must be an ID
		if ( this.assertion.getID() == null ) {
			throw new ValidationException("The assertion must contain an ID reference");
		}

		// TODO build some validation
		// According to the Standard We need to validate that Assertion Id's are only used once to prevent hackers
		// reusing previously send responses.
		// Can we validate something with the request / response versions? Technically we are not supposed to
		// respond to different versions, what does that mean? Does the documentation just talk about saml2.0 vs
		// saml1.0 or something else?

		if( Constants.validationLevel == ValidationLevel.Strict ) {
			Conditions conditions = this.assertion.getConditions();
			if( conditions != null ) {
				DateTime notBefore = conditions.getNotBefore();
				if( notBefore != null && (notBefore.isAfterNow() || notBefore.isEqualNow()) )
					throw new ValidationException("Assertion Conditions are not met. This request cannot be used before: " + notBefore.toString() );
				
				DateTime notAfter = conditions.getNotOnOrAfter();
				if( notAfter != null && notAfter.isBeforeNow() ) 
					throw new ValidationException("Assertion Conditions are not met. This request cannot be used after: " + notAfter.toString() );
				
				
				boolean anyAudiencesFound = false, appIsAudience = false;
				List<AudienceRestriction> audienceList = conditions.getAudienceRestrictions();
				for( AudienceRestriction restriction : audienceList ) {
					for( Audience audience : restriction.getAudiences() ) {
						anyAudiencesFound = true;
						if( spEntityID.equals(audience.getAudienceURI()) )
							appIsAudience = true;
					}
				}
				
				if( anyAudiencesFound && !appIsAudience ) 
					throw new ValidationException("Assertion Conditions are not met. This Service Provider application is not part of the designated audience list.");
			}
			else 
				_logNode.debug("Request: " + this.assertion.getID() + " does not have a condition to validate the dates or audience.");
			
			Subject subject = this.assertion.getSubject();
			if( subject != null ) {
				NameID nameID = subject.getNameID();
				if( nameID != null ) {
					if( !spEntityID.equals( nameID.getSPNameQualifier() ) )
						throw new ValidationException("Invalid Subject, this Assertion is not addressed to SP: " + nameID.getSPNameQualifier() + " but should have been addressed to " + spEntityID);
				}
				else 
					throw new ValidationException("Invalid Subject, this Assertion has not been addressed to any SP. It should have been addressed to " + spEntityID);
				
				List<SubjectConfirmation> sConfirmations = subject.getSubjectConfirmations();
				for( SubjectConfirmation confirmation : sConfirmations ) {
					SubjectConfirmationData confirmationData = confirmation.getSubjectConfirmationData();

					DateTime notBefore = confirmationData.getNotBefore();
					if( notBefore != null && (notBefore.isAfterNow() || notBefore.isEqualNow()) )
						throw new ValidationException("Invalid Subject, this Assertion cannot be used before: " + notBefore.toString() );
					
					DateTime notAfter = confirmationData.getNotOnOrAfter();
					if( notAfter != null && notAfter.isBeforeNow() ) 
						throw new ValidationException("Invalid Subject, this Assertion cannot be used after: " + notAfter.toString() );
					
					if( Constants.SAML2_BEARER_NS.equals(confirmation.getMethod()) ) { 
						if( !spAssertionConsumerURL.equals( confirmationData.getRecipient() ) )
							throw new ValidationException("Invalid Subject, this Assertion is sent to receipent: " + confirmationData.getRecipient() + " but should have been send to receipent: " + spAssertionConsumerURL);
					}
					else if( Constants.SAML2_HoK_NS.equals(confirmation.getMethod()) ) { 
						//TODO validate the SAML certificates.
					}
				}
			}
			else 
				_logNode.warn("Request: " + this.assertion.getID() + " does not have a subject to validate the issuer and validity.");
		}
	}

	public String getSessionIndex() {
		String retVal = null;
		if ( this.assertion != null && this.assertion.getAuthnStatements() != null ) {
			if ( this.assertion.getAuthnStatements().size() > 0 ) {
				// We only look into the first AuthnStatement
				AuthnStatement authnStatement = this.assertion.getAuthnStatements().get(0);
				retVal = authnStatement.getSessionIndex();
			}
		}
		return retVal;
	}

	public String getIssuer() {
		Issuer issuer = this.assertion.getIssuer();
		if( issuer == null )
			return null;
		
		return issuer.getValue();
	}
	
	public String getNameID() {
		Subject subject = this.assertion.getSubject();
		if (subject == null)
			return null;
		
		return subject.getNameID().getValue();		
	}
	
	public String getNameIDFormat() {
		Subject subject = this.assertion.getSubject();
		if (subject == null)
			return null;
		
		return subject.getNameID().getFormat();	
	}
}