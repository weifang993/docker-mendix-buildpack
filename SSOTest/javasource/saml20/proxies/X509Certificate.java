// This file was generated by Mendix Modeler.
//
// WARNING: Code you write here will be lost the next time you deploy the project.

package saml20.proxies;

public class X509Certificate extends system.proxies.FileDocument
{
	/**
	 * Internal name of this entity
	 */
	public static final java.lang.String entityName = "SAML20.X509Certificate";

	/**
	 * Enum describing members of this entity
	 */
	public enum MemberNames
	{
		IssuerName("IssuerName"),
		SerialNumber("SerialNumber"),
		Subject("Subject"),
		ValidFrom("ValidFrom"),
		ValidUntil("ValidUntil"),
		Base64("Base64"),
		FileID("FileID"),
		Name("Name"),
		DeleteAfterDownload("DeleteAfterDownload"),
		Contents("Contents"),
		HasContents("HasContents"),
		Size("Size");

		private java.lang.String metaName;

		MemberNames(java.lang.String s)
		{
			metaName = s;
		}

		@Override
		public java.lang.String toString()
		{
			return metaName;
		}
	}

	public X509Certificate(com.mendix.systemwideinterfaces.core.IContext context)
	{
		this(context, com.mendix.core.Core.instantiate(context, "SAML20.X509Certificate"));
	}

	protected X509Certificate(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixObject x509CertificateMendixObject)
	{
		super(context, x509CertificateMendixObject);
		if (!com.mendix.core.Core.isSubClassOf("SAML20.X509Certificate", x509CertificateMendixObject.getType()))
			throw new java.lang.IllegalArgumentException("The given object is not a SAML20.X509Certificate");
	}

	/**
	 * @deprecated Use 'X509Certificate.load(IContext, IMendixIdentifier)' instead.
	 */
	@Deprecated
	public static saml20.proxies.X509Certificate initialize(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixIdentifier mendixIdentifier) throws com.mendix.core.CoreException
	{
		return saml20.proxies.X509Certificate.load(context, mendixIdentifier);
	}

	/**
	 * Initialize a proxy using context (recommended). This context will be used for security checking when the get- and set-methods without context parameters are called.
	 * The get- and set-methods with context parameter should be used when for instance sudo access is necessary (IContext.createSudoClone() can be used to obtain sudo access).
	 */
	public static saml20.proxies.X509Certificate initialize(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixObject mendixObject)
	{
		return new saml20.proxies.X509Certificate(context, mendixObject);
	}

	public static saml20.proxies.X509Certificate load(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixIdentifier mendixIdentifier) throws com.mendix.core.CoreException
	{
		com.mendix.systemwideinterfaces.core.IMendixObject mendixObject = com.mendix.core.Core.retrieveId(context, mendixIdentifier);
		return saml20.proxies.X509Certificate.initialize(context, mendixObject);
	}

	public static java.util.List<saml20.proxies.X509Certificate> load(com.mendix.systemwideinterfaces.core.IContext context, java.lang.String xpathConstraint) throws com.mendix.core.CoreException
	{
		java.util.List<saml20.proxies.X509Certificate> result = new java.util.ArrayList<saml20.proxies.X509Certificate>();
		for (com.mendix.systemwideinterfaces.core.IMendixObject obj : com.mendix.core.Core.retrieveXPathQuery(context, "//SAML20.X509Certificate" + xpathConstraint))
			result.add(saml20.proxies.X509Certificate.initialize(context, obj));
		return result;
	}

	/**
	 * @return value of IssuerName
	 */
	public final java.lang.String getIssuerName()
	{
		return getIssuerName(getContext());
	}

	/**
	 * @param context
	 * @return value of IssuerName
	 */
	public final java.lang.String getIssuerName(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.lang.String) getMendixObject().getValue(context, MemberNames.IssuerName.toString());
	}

	/**
	 * Set value of IssuerName
	 * @param issuername
	 */
	public final void setIssuerName(java.lang.String issuername)
	{
		setIssuerName(getContext(), issuername);
	}

	/**
	 * Set value of IssuerName
	 * @param context
	 * @param issuername
	 */
	public final void setIssuerName(com.mendix.systemwideinterfaces.core.IContext context, java.lang.String issuername)
	{
		getMendixObject().setValue(context, MemberNames.IssuerName.toString(), issuername);
	}

	/**
	 * @return value of SerialNumber
	 */
	public final java.lang.String getSerialNumber()
	{
		return getSerialNumber(getContext());
	}

	/**
	 * @param context
	 * @return value of SerialNumber
	 */
	public final java.lang.String getSerialNumber(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.lang.String) getMendixObject().getValue(context, MemberNames.SerialNumber.toString());
	}

	/**
	 * Set value of SerialNumber
	 * @param serialnumber
	 */
	public final void setSerialNumber(java.lang.String serialnumber)
	{
		setSerialNumber(getContext(), serialnumber);
	}

	/**
	 * Set value of SerialNumber
	 * @param context
	 * @param serialnumber
	 */
	public final void setSerialNumber(com.mendix.systemwideinterfaces.core.IContext context, java.lang.String serialnumber)
	{
		getMendixObject().setValue(context, MemberNames.SerialNumber.toString(), serialnumber);
	}

	/**
	 * @return value of Subject
	 */
	public final java.lang.String getSubject()
	{
		return getSubject(getContext());
	}

	/**
	 * @param context
	 * @return value of Subject
	 */
	public final java.lang.String getSubject(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.lang.String) getMendixObject().getValue(context, MemberNames.Subject.toString());
	}

	/**
	 * Set value of Subject
	 * @param subject
	 */
	public final void setSubject(java.lang.String subject)
	{
		setSubject(getContext(), subject);
	}

	/**
	 * Set value of Subject
	 * @param context
	 * @param subject
	 */
	public final void setSubject(com.mendix.systemwideinterfaces.core.IContext context, java.lang.String subject)
	{
		getMendixObject().setValue(context, MemberNames.Subject.toString(), subject);
	}

	/**
	 * @return value of ValidFrom
	 */
	public final java.util.Date getValidFrom()
	{
		return getValidFrom(getContext());
	}

	/**
	 * @param context
	 * @return value of ValidFrom
	 */
	public final java.util.Date getValidFrom(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.util.Date) getMendixObject().getValue(context, MemberNames.ValidFrom.toString());
	}

	/**
	 * Set value of ValidFrom
	 * @param validfrom
	 */
	public final void setValidFrom(java.util.Date validfrom)
	{
		setValidFrom(getContext(), validfrom);
	}

	/**
	 * Set value of ValidFrom
	 * @param context
	 * @param validfrom
	 */
	public final void setValidFrom(com.mendix.systemwideinterfaces.core.IContext context, java.util.Date validfrom)
	{
		getMendixObject().setValue(context, MemberNames.ValidFrom.toString(), validfrom);
	}

	/**
	 * @return value of ValidUntil
	 */
	public final java.util.Date getValidUntil()
	{
		return getValidUntil(getContext());
	}

	/**
	 * @param context
	 * @return value of ValidUntil
	 */
	public final java.util.Date getValidUntil(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.util.Date) getMendixObject().getValue(context, MemberNames.ValidUntil.toString());
	}

	/**
	 * Set value of ValidUntil
	 * @param validuntil
	 */
	public final void setValidUntil(java.util.Date validuntil)
	{
		setValidUntil(getContext(), validuntil);
	}

	/**
	 * Set value of ValidUntil
	 * @param context
	 * @param validuntil
	 */
	public final void setValidUntil(com.mendix.systemwideinterfaces.core.IContext context, java.util.Date validuntil)
	{
		getMendixObject().setValue(context, MemberNames.ValidUntil.toString(), validuntil);
	}

	/**
	 * @return value of Base64
	 */
	public final java.lang.String getBase64()
	{
		return getBase64(getContext());
	}

	/**
	 * @param context
	 * @return value of Base64
	 */
	public final java.lang.String getBase64(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.lang.String) getMendixObject().getValue(context, MemberNames.Base64.toString());
	}

	/**
	 * Set value of Base64
	 * @param base64
	 */
	public final void setBase64(java.lang.String base64)
	{
		setBase64(getContext(), base64);
	}

	/**
	 * Set value of Base64
	 * @param context
	 * @param base64
	 */
	public final void setBase64(com.mendix.systemwideinterfaces.core.IContext context, java.lang.String base64)
	{
		getMendixObject().setValue(context, MemberNames.Base64.toString(), base64);
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj == this)
			return true;

		if (obj != null && getClass().equals(obj.getClass()))
		{
			final saml20.proxies.X509Certificate that = (saml20.proxies.X509Certificate) obj;
			return getMendixObject().equals(that.getMendixObject());
		}
		return false;
	}

	@Override
	public int hashCode()
	{
		return getMendixObject().hashCode();
	}

	/**
	 * @return String name of this class
	 */
	public static java.lang.String getType()
	{
		return "SAML20.X509Certificate";
	}

	/**
	 * @return String GUID from this object, format: ID_0000000000
	 * @deprecated Use getMendixObject().getId().toLong() to get a unique identifier for this object.
	 */
	@Override
	@Deprecated
	public java.lang.String getGUID()
	{
		return "ID_" + getMendixObject().getId().toLong();
	}
}
