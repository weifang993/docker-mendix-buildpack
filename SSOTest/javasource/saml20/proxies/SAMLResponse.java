// This file was generated by Mendix Modeler.
//
// WARNING: Code you write here will be lost the next time you deploy the project.

package saml20.proxies;

public class SAMLResponse extends system.proxies.FileDocument
{
	/**
	 * Internal name of this entity
	 */
	public static final java.lang.String entityName = "SAML20.SAMLResponse";

	/**
	 * Enum describing members of this entity
	 */
	public enum MemberNames
	{
		FileID("FileID"),
		Name("Name"),
		DeleteAfterDownload("DeleteAfterDownload"),
		Contents("Contents"),
		HasContents("HasContents"),
		Size("Size"),
		SAMLRequest_SAMLResponse("SAML20.SAMLRequest_SAMLResponse"),
		SAMLResponse_SSOConfiguration("SAML20.SAMLResponse_SSOConfiguration");

		private java.lang.String metaName;

		MemberNames(java.lang.String s)
		{
			metaName = s;
		}

		@java.lang.Override
		public java.lang.String toString()
		{
			return metaName;
		}
	}

	public SAMLResponse(com.mendix.systemwideinterfaces.core.IContext context)
	{
		this(context, com.mendix.core.Core.instantiate(context, "SAML20.SAMLResponse"));
	}

	protected SAMLResponse(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixObject sAMLResponseMendixObject)
	{
		super(context, sAMLResponseMendixObject);
		if (!com.mendix.core.Core.isSubClassOf("SAML20.SAMLResponse", sAMLResponseMendixObject.getType()))
			throw new java.lang.IllegalArgumentException("The given object is not a SAML20.SAMLResponse");
	}

	/**
	 * @deprecated Use 'SAMLResponse.load(IContext, IMendixIdentifier)' instead.
	 */
	@java.lang.Deprecated
	public static saml20.proxies.SAMLResponse initialize(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixIdentifier mendixIdentifier) throws com.mendix.core.CoreException
	{
		return saml20.proxies.SAMLResponse.load(context, mendixIdentifier);
	}

	/**
	 * Initialize a proxy using context (recommended). This context will be used for security checking when the get- and set-methods without context parameters are called.
	 * The get- and set-methods with context parameter should be used when for instance sudo access is necessary (IContext.createSudoClone() can be used to obtain sudo access).
	 */
	public static saml20.proxies.SAMLResponse initialize(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixObject mendixObject)
	{
		return new saml20.proxies.SAMLResponse(context, mendixObject);
	}

	public static saml20.proxies.SAMLResponse load(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixIdentifier mendixIdentifier) throws com.mendix.core.CoreException
	{
		com.mendix.systemwideinterfaces.core.IMendixObject mendixObject = com.mendix.core.Core.retrieveId(context, mendixIdentifier);
		return saml20.proxies.SAMLResponse.initialize(context, mendixObject);
	}

	public static java.util.List<saml20.proxies.SAMLResponse> load(com.mendix.systemwideinterfaces.core.IContext context, java.lang.String xpathConstraint) throws com.mendix.core.CoreException
	{
		java.util.List<saml20.proxies.SAMLResponse> result = new java.util.ArrayList<saml20.proxies.SAMLResponse>();
		for (com.mendix.systemwideinterfaces.core.IMendixObject obj : com.mendix.core.Core.retrieveXPathQuery(context, "//SAML20.SAMLResponse" + xpathConstraint))
			result.add(saml20.proxies.SAMLResponse.initialize(context, obj));
		return result;
	}

	/**
	 * @return value of SAMLRequest_SAMLResponse
	 */
	public final saml20.proxies.SAMLRequest getSAMLRequest_SAMLResponse() throws com.mendix.core.CoreException
	{
		return getSAMLRequest_SAMLResponse(getContext());
	}

	/**
	 * @param context
	 * @return value of SAMLRequest_SAMLResponse
	 */
	public final saml20.proxies.SAMLRequest getSAMLRequest_SAMLResponse(com.mendix.systemwideinterfaces.core.IContext context) throws com.mendix.core.CoreException
	{
		saml20.proxies.SAMLRequest result = null;
		com.mendix.systemwideinterfaces.core.IMendixIdentifier identifier = getMendixObject().getValue(context, MemberNames.SAMLRequest_SAMLResponse.toString());
		if (identifier != null)
			result = saml20.proxies.SAMLRequest.load(context, identifier);
		return result;
	}

	/**
	 * Set value of SAMLRequest_SAMLResponse
	 * @param samlrequest_samlresponse
	 */
	public final void setSAMLRequest_SAMLResponse(saml20.proxies.SAMLRequest samlrequest_samlresponse)
	{
		setSAMLRequest_SAMLResponse(getContext(), samlrequest_samlresponse);
	}

	/**
	 * Set value of SAMLRequest_SAMLResponse
	 * @param context
	 * @param samlrequest_samlresponse
	 */
	public final void setSAMLRequest_SAMLResponse(com.mendix.systemwideinterfaces.core.IContext context, saml20.proxies.SAMLRequest samlrequest_samlresponse)
	{
		if (samlrequest_samlresponse == null)
			getMendixObject().setValue(context, MemberNames.SAMLRequest_SAMLResponse.toString(), null);
		else
			getMendixObject().setValue(context, MemberNames.SAMLRequest_SAMLResponse.toString(), samlrequest_samlresponse.getMendixObject().getId());
	}

	/**
	 * @return value of SAMLResponse_SSOConfiguration
	 */
	public final saml20.proxies.SSOConfiguration getSAMLResponse_SSOConfiguration() throws com.mendix.core.CoreException
	{
		return getSAMLResponse_SSOConfiguration(getContext());
	}

	/**
	 * @param context
	 * @return value of SAMLResponse_SSOConfiguration
	 */
	public final saml20.proxies.SSOConfiguration getSAMLResponse_SSOConfiguration(com.mendix.systemwideinterfaces.core.IContext context) throws com.mendix.core.CoreException
	{
		saml20.proxies.SSOConfiguration result = null;
		com.mendix.systemwideinterfaces.core.IMendixIdentifier identifier = getMendixObject().getValue(context, MemberNames.SAMLResponse_SSOConfiguration.toString());
		if (identifier != null)
			result = saml20.proxies.SSOConfiguration.load(context, identifier);
		return result;
	}

	/**
	 * Set value of SAMLResponse_SSOConfiguration
	 * @param samlresponse_ssoconfiguration
	 */
	public final void setSAMLResponse_SSOConfiguration(saml20.proxies.SSOConfiguration samlresponse_ssoconfiguration)
	{
		setSAMLResponse_SSOConfiguration(getContext(), samlresponse_ssoconfiguration);
	}

	/**
	 * Set value of SAMLResponse_SSOConfiguration
	 * @param context
	 * @param samlresponse_ssoconfiguration
	 */
	public final void setSAMLResponse_SSOConfiguration(com.mendix.systemwideinterfaces.core.IContext context, saml20.proxies.SSOConfiguration samlresponse_ssoconfiguration)
	{
		if (samlresponse_ssoconfiguration == null)
			getMendixObject().setValue(context, MemberNames.SAMLResponse_SSOConfiguration.toString(), null);
		else
			getMendixObject().setValue(context, MemberNames.SAMLResponse_SSOConfiguration.toString(), samlresponse_ssoconfiguration.getMendixObject().getId());
	}

	@java.lang.Override
	public boolean equals(Object obj)
	{
		if (obj == this)
			return true;

		if (obj != null && getClass().equals(obj.getClass()))
		{
			final saml20.proxies.SAMLResponse that = (saml20.proxies.SAMLResponse) obj;
			return getMendixObject().equals(that.getMendixObject());
		}
		return false;
	}

	@java.lang.Override
	public int hashCode()
	{
		return getMendixObject().hashCode();
	}

	/**
	 * @return String name of this class
	 */
	public static java.lang.String getType()
	{
		return "SAML20.SAMLResponse";
	}

	/**
	 * @return String GUID from this object, format: ID_0000000000
	 * @deprecated Use getMendixObject().getId().toLong() to get a unique identifier for this object.
	 */
	@java.lang.Override
	@java.lang.Deprecated
	public java.lang.String getGUID()
	{
		return "ID_" + getMendixObject().getId().toLong();
	}
}
