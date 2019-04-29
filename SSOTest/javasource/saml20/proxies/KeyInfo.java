// This file was generated by Mendix Modeler.
//
// WARNING: Code you write here will be lost the next time you deploy the project.

package saml20.proxies;

public class KeyInfo
{
	private final com.mendix.systemwideinterfaces.core.IMendixObject keyInfoMendixObject;

	private final com.mendix.systemwideinterfaces.core.IContext context;

	/**
	 * Internal name of this entity
	 */
	public static final java.lang.String entityName = "SAML20.KeyInfo";

	/**
	 * Enum describing members of this entity
	 */
	public enum MemberNames
	{
		_Id("_Id"),
		KeyInfo_EntityDescriptor("SAML20.KeyInfo_EntityDescriptor"),
		KeyInfo_X509Certificate("SAML20.KeyInfo_X509Certificate");

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

	public KeyInfo(com.mendix.systemwideinterfaces.core.IContext context)
	{
		this(context, com.mendix.core.Core.instantiate(context, "SAML20.KeyInfo"));
	}

	protected KeyInfo(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixObject keyInfoMendixObject)
	{
		if (keyInfoMendixObject == null)
			throw new java.lang.IllegalArgumentException("The given object cannot be null.");
		if (!com.mendix.core.Core.isSubClassOf("SAML20.KeyInfo", keyInfoMendixObject.getType()))
			throw new java.lang.IllegalArgumentException("The given object is not a SAML20.KeyInfo");

		this.keyInfoMendixObject = keyInfoMendixObject;
		this.context = context;
	}

	/**
	 * @deprecated Use 'KeyInfo.load(IContext, IMendixIdentifier)' instead.
	 */
	@java.lang.Deprecated
	public static saml20.proxies.KeyInfo initialize(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixIdentifier mendixIdentifier) throws com.mendix.core.CoreException
	{
		return saml20.proxies.KeyInfo.load(context, mendixIdentifier);
	}

	/**
	 * Initialize a proxy using context (recommended). This context will be used for security checking when the get- and set-methods without context parameters are called.
	 * The get- and set-methods with context parameter should be used when for instance sudo access is necessary (IContext.createSudoClone() can be used to obtain sudo access).
	 */
	public static saml20.proxies.KeyInfo initialize(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixObject mendixObject)
	{
		return new saml20.proxies.KeyInfo(context, mendixObject);
	}

	public static saml20.proxies.KeyInfo load(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixIdentifier mendixIdentifier) throws com.mendix.core.CoreException
	{
		com.mendix.systemwideinterfaces.core.IMendixObject mendixObject = com.mendix.core.Core.retrieveId(context, mendixIdentifier);
		return saml20.proxies.KeyInfo.initialize(context, mendixObject);
	}

	public static java.util.List<saml20.proxies.KeyInfo> load(com.mendix.systemwideinterfaces.core.IContext context, java.lang.String xpathConstraint) throws com.mendix.core.CoreException
	{
		java.util.List<saml20.proxies.KeyInfo> result = new java.util.ArrayList<saml20.proxies.KeyInfo>();
		for (com.mendix.systemwideinterfaces.core.IMendixObject obj : com.mendix.core.Core.retrieveXPathQuery(context, "//SAML20.KeyInfo" + xpathConstraint))
			result.add(saml20.proxies.KeyInfo.initialize(context, obj));
		return result;
	}

	/**
	 * Commit the changes made on this proxy object.
	 */
	public final void commit() throws com.mendix.core.CoreException
	{
		com.mendix.core.Core.commit(context, getMendixObject());
	}

	/**
	 * Commit the changes made on this proxy object using the specified context.
	 */
	public final void commit(com.mendix.systemwideinterfaces.core.IContext context) throws com.mendix.core.CoreException
	{
		com.mendix.core.Core.commit(context, getMendixObject());
	}

	/**
	 * Delete the object.
	 */
	public final void delete()
	{
		com.mendix.core.Core.delete(context, getMendixObject());
	}

	/**
	 * Delete the object using the specified context.
	 */
	public final void delete(com.mendix.systemwideinterfaces.core.IContext context)
	{
		com.mendix.core.Core.delete(context, getMendixObject());
	}
	/**
	 * @return value of _Id
	 */
	public final java.lang.String get_Id()
	{
		return get_Id(getContext());
	}

	/**
	 * @param context
	 * @return value of _Id
	 */
	public final java.lang.String get_Id(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.lang.String) getMendixObject().getValue(context, MemberNames._Id.toString());
	}

	/**
	 * Set value of _Id
	 * @param _id
	 */
	public final void set_Id(java.lang.String _id)
	{
		set_Id(getContext(), _id);
	}

	/**
	 * Set value of _Id
	 * @param context
	 * @param _id
	 */
	public final void set_Id(com.mendix.systemwideinterfaces.core.IContext context, java.lang.String _id)
	{
		getMendixObject().setValue(context, MemberNames._Id.toString(), _id);
	}

	/**
	 * @return value of KeyInfo_EntityDescriptor
	 */
	public final saml20.proxies.EntityDescriptor getKeyInfo_EntityDescriptor() throws com.mendix.core.CoreException
	{
		return getKeyInfo_EntityDescriptor(getContext());
	}

	/**
	 * @param context
	 * @return value of KeyInfo_EntityDescriptor
	 */
	public final saml20.proxies.EntityDescriptor getKeyInfo_EntityDescriptor(com.mendix.systemwideinterfaces.core.IContext context) throws com.mendix.core.CoreException
	{
		saml20.proxies.EntityDescriptor result = null;
		com.mendix.systemwideinterfaces.core.IMendixIdentifier identifier = getMendixObject().getValue(context, MemberNames.KeyInfo_EntityDescriptor.toString());
		if (identifier != null)
			result = saml20.proxies.EntityDescriptor.load(context, identifier);
		return result;
	}

	/**
	 * Set value of KeyInfo_EntityDescriptor
	 * @param keyinfo_entitydescriptor
	 */
	public final void setKeyInfo_EntityDescriptor(saml20.proxies.EntityDescriptor keyinfo_entitydescriptor)
	{
		setKeyInfo_EntityDescriptor(getContext(), keyinfo_entitydescriptor);
	}

	/**
	 * Set value of KeyInfo_EntityDescriptor
	 * @param context
	 * @param keyinfo_entitydescriptor
	 */
	public final void setKeyInfo_EntityDescriptor(com.mendix.systemwideinterfaces.core.IContext context, saml20.proxies.EntityDescriptor keyinfo_entitydescriptor)
	{
		if (keyinfo_entitydescriptor == null)
			getMendixObject().setValue(context, MemberNames.KeyInfo_EntityDescriptor.toString(), null);
		else
			getMendixObject().setValue(context, MemberNames.KeyInfo_EntityDescriptor.toString(), keyinfo_entitydescriptor.getMendixObject().getId());
	}

	/**
	 * @return value of KeyInfo_X509Certificate
	 */
	public final java.util.List<saml20.proxies.X509Certificate> getKeyInfo_X509Certificate() throws com.mendix.core.CoreException
	{
		return getKeyInfo_X509Certificate(getContext());
	}

	/**
	 * @param context
	 * @return value of KeyInfo_X509Certificate
	 */
	@SuppressWarnings("unchecked")
	public final java.util.List<saml20.proxies.X509Certificate> getKeyInfo_X509Certificate(com.mendix.systemwideinterfaces.core.IContext context) throws com.mendix.core.CoreException
	{
		java.util.List<saml20.proxies.X509Certificate> result = new java.util.ArrayList<saml20.proxies.X509Certificate>();
		Object valueObject = getMendixObject().getValue(context, MemberNames.KeyInfo_X509Certificate.toString());
		if (valueObject == null)
			return result;
		for (com.mendix.systemwideinterfaces.core.IMendixObject mendixObject : com.mendix.core.Core.retrieveIdList(context, (java.util.List<com.mendix.systemwideinterfaces.core.IMendixIdentifier>) valueObject))
			result.add(saml20.proxies.X509Certificate.initialize(context, mendixObject));
		return result;
	}

	/**
	 * Set value of KeyInfo_X509Certificate
	 * @param keyinfo_x509certificate
	 */
	public final void setKeyInfo_X509Certificate(java.util.List<saml20.proxies.X509Certificate> keyinfo_x509certificate)
	{
		setKeyInfo_X509Certificate(getContext(), keyinfo_x509certificate);
	}

	/**
	 * Set value of KeyInfo_X509Certificate
	 * @param context
	 * @param keyinfo_x509certificate
	 */
	public final void setKeyInfo_X509Certificate(com.mendix.systemwideinterfaces.core.IContext context, java.util.List<saml20.proxies.X509Certificate> keyinfo_x509certificate)
	{
		java.util.List<com.mendix.systemwideinterfaces.core.IMendixIdentifier> identifiers = new java.util.ArrayList<com.mendix.systemwideinterfaces.core.IMendixIdentifier>();
		for (saml20.proxies.X509Certificate proxyObject : keyinfo_x509certificate)
			identifiers.add(proxyObject.getMendixObject().getId());
		getMendixObject().setValue(context, MemberNames.KeyInfo_X509Certificate.toString(), identifiers);
	}

	/**
	 * @return the IMendixObject instance of this proxy for use in the Core interface.
	 */
	public final com.mendix.systemwideinterfaces.core.IMendixObject getMendixObject()
	{
		return keyInfoMendixObject;
	}

	/**
	 * @return the IContext instance of this proxy, or null if no IContext instance was specified at initialization.
	 */
	public final com.mendix.systemwideinterfaces.core.IContext getContext()
	{
		return context;
	}

	@java.lang.Override
	public boolean equals(Object obj)
	{
		if (obj == this)
			return true;

		if (obj != null && getClass().equals(obj.getClass()))
		{
			final saml20.proxies.KeyInfo that = (saml20.proxies.KeyInfo) obj;
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
		return "SAML20.KeyInfo";
	}

	/**
	 * @return String GUID from this object, format: ID_0000000000
	 * @deprecated Use getMendixObject().getId().toLong() to get a unique identifier for this object.
	 */
	@java.lang.Deprecated
	public java.lang.String getGUID()
	{
		return "ID_" + getMendixObject().getId().toLong();
	}
}
