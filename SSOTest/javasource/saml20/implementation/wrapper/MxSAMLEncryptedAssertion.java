package saml20.implementation.wrapper;

import org.opensaml.common.SAMLException;
import org.opensaml.saml2.core.Assertion;
import org.opensaml.saml2.core.EncryptedAssertion;
import org.opensaml.saml2.core.Response;
import org.opensaml.saml2.encryption.Decrypter;
import org.opensaml.saml2.encryption.EncryptedElementTypeEncryptedKeyResolver;
import org.opensaml.xml.encryption.ChainingEncryptedKeyResolver;
import org.opensaml.xml.encryption.DecryptionException;
import org.opensaml.xml.encryption.InlineEncryptedKeyResolver;
import org.opensaml.xml.encryption.SimpleRetrievalMethodEncryptedKeyResolver;
import org.opensaml.xml.security.credential.Credential;
import org.opensaml.xml.security.keyinfo.KeyInfoCredentialResolver;
import org.opensaml.xml.security.keyinfo.StaticKeyInfoCredentialResolver;
import org.opensaml.xml.validation.ValidationException;

import saml20.implementation.common.SAMLUtil;

public class MxSAMLEncryptedAssertion extends MxSAMLObject {
	private final EncryptedAssertion encrypted;

	public MxSAMLEncryptedAssertion( EncryptedAssertion assertion ) {
		super(assertion);
		this.encrypted = assertion;
		if ( assertion.getEncryptedData().getType() == null ) {
			assertion.getEncryptedData().setType("http://www.w3.org/2001/04/xmlenc#Element");
		}
	}
	
	public static void decryptAssertion( Response response, Credential credential, boolean allowUnencrypted ) throws ValidationException, SAMLException {
		if ( response.getEncryptedAssertions().size() > 0 ) {
			MxSAMLEncryptedAssertion encryptedAssertion = new MxSAMLEncryptedAssertion(response.getEncryptedAssertions().get(0));
			
			MxSAMLAssertion assertion = encryptedAssertion.decryptAssertion(credential);
			response.getAssertions().add(assertion.getAssertion());
		}
		else {
			if ( !allowUnencrypted && !response.getAssertions().isEmpty() ) {
				throw new ValidationException("Assertion is not encrypted");
			}
		}
	}

	private MxSAMLAssertion decryptAssertion( Credential credential ) throws SAMLException, ValidationException {
		KeyInfoCredentialResolver keyResolver = new StaticKeyInfoCredentialResolver(credential);

		ChainingEncryptedKeyResolver kekResolver = new ChainingEncryptedKeyResolver();
		kekResolver.getResolverChain().add(new InlineEncryptedKeyResolver());
		kekResolver.getResolverChain().add(new EncryptedElementTypeEncryptedKeyResolver());
		kekResolver.getResolverChain().add(new SimpleRetrievalMethodEncryptedKeyResolver());

		try {
			if ( _logNode.isDebugEnabled() )
				_logNode.debug("Assertion encrypted: " + this.encrypted);

			Decrypter decrypter = new Decrypter(null, keyResolver, kekResolver);

			// due to a bug in OpenSAML, we have to convert the assertion to and from xml
			// otherwise the signature will not validate later on
			Assertion assertion = decrypter.decrypt(this.encrypted);
			MxSAMLAssertion res = new MxSAMLAssertion(assertion);
			assertion = (Assertion) SAMLUtil.unmarshallElementFromString(res.toXML());

			if ( _logNode.isDebugEnabled() )
				_logNode.debug("Decrypted assertion: " + res.toXML());

			return new MxSAMLAssertion(assertion);
		}
		catch( DecryptionException e ) {
			throw new ValidationException(e);
		}
	}

}
