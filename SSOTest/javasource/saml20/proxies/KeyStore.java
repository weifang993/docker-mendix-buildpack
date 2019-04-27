// This file was generated by Mendix Modeler.
//
// WARNING: Code you write here will be lost the next time you deploy the project.

package saml20.proxies;

public class KeyStore extends system.proxies.FileDocument
{
	/**
	 * Internal name of this entity
	 */
	public static final java.lang.String entityName = "SAML20.KeyStore";

	/**
	 * Enum describing members of this entity
	 */
	public enum MemberNames
	{
		LastChangedOn("LastChangedOn"),
		Alias("Alias"),
		RebuildKeyStore("RebuildKeyStore"),
		FileID("FileID"),
		Name("Name"),
		DeleteAfterDownload("DeleteAfterDownload"),
		Contents("Contents"),
		HasContents("HasContents"),
		Size("Size"),
		SPMetadata_KeyStore("SAML20.SPMetadata_KeyStore");

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

	public KeyStore(com.mendix.systemwideinterfaces.core.IContext context)
	{
		this(context, com.mendix.core.Core.instantiate(context, "SAML20.KeyStore"));
	}

	protected KeyStore(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixObject keyStoreMendixObject)
	{
		super(context, keyStoreMendixObject);
		if (!com.mendix.core.Core.isSubClassOf("SAML20.KeyStore", keyStoreMendixObject.getType()))
			throw new java.lang.IllegalArgumentException("The given object is not a SAML20.KeyStore");
	}

	/**
	 * @deprecated Use 'KeyStore.load(IContext, IMendixIdentifier)' instead.
	 */
	@Deprecated
	public static saml20.proxies.KeyStore initialize(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixIdentifier mendixIdentifier) throws com.mendix.core.CoreException
	{
		return saml20.proxies.KeyStore.load(context, mendixIdentifier);
	}

	/**
	 * Initialize a proxy using context (recommended). This context will be used for security checking when the get- and set-methods without context parameters are called.
	 * The get- and set-methods with context parameter should be used when for instance sudo access is necessary (IContext.createSudoClone() can be used to obtain sudo access).
	 */
	public static saml20.proxies.KeyStore initialize(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixObject mendixObject)
	{
		return new saml20.proxies.KeyStore(context, mendixObject);
	}

	public static saml20.proxies.KeyStore load(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixIdentifier mendixIdentifier) throws com.mendix.core.CoreException
	{
		com.mendix.systemwideinterfaces.core.IMendixObject mendixObject = com.mendix.core.Core.retrieveId(context, mendixIdentifier);
		return saml20.proxies.KeyStore.initialize(context, mendixObject);
	}

	public static java.util.List<saml20.proxies.KeyStore> load(com.mendix.systemwideinterfaces.core.IContext context, java.lang.String xpathConstraint) throws com.mendix.core.CoreException
	{
		java.util.List<saml20.proxies.KeyStore> result = new java.util.ArrayList<saml20.proxies.KeyStore>();
		for (com.mendix.systemwideinterfaces.core.IMendixObject obj : com.mendix.core.Core.retrieveXPathQuery(context, "//SAML20.KeyStore" + xpathConstraint))
			result.add(saml20.proxies.KeyStore.initialize(context, obj));
		return result;
	}

	/**
	 * @return value of LastChangedOn
	 */
	public final java.util.Date getLastChangedOn()
	{
		return getLastChangedOn(getContext());
	}

	/**
	 * @param context
	 * @return value of LastChangedOn
	 */
	public final java.util.Date getLastChangedOn(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.util.Date) getMendixObject().getValue(context, MemberNames.LastChangedOn.toString());
	}

	/**
	 * Set value of LastChangedOn
	 * @param lastchangedon
	 */
	public final void setLastChangedOn(java.util.Date lastchangedon)
	{
		setLastChangedOn(getContext(), lastchangedon);
	}

	/**
	 * Set value of LastChangedOn
	 * @param context
	 * @param lastchangedon
	 */
	public final void setLastChangedOn(com.mendix.systemwideinterfaces.core.IContext context, java.util.Date lastchangedon)
	{
		getMendixObject().setValue(context, MemberNames.LastChangedOn.toString(), lastchangedon);
	}

	/**
	 * @return value of Alias
	 */
	public final java.lang.String getAlias()
	{
		return getAlias(getContext());
	}

	/**
	 * @param context
	 * @return value of Alias
	 */
	public final java.lang.String getAlias(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.lang.String) getMendixObject().getValue(context, MemberNames.Alias.toString());
	}

	/**
	 * Set value of Alias
	 * @param alias
	 */
	public final void setAlias(java.lang.String alias)
	{
		setAlias(getContext(), alias);
	}

	/**
	 * Set value of Alias
	 * @param context
	 * @param alias
	 */
	public final void setAlias(com.mendix.systemwideinterfaces.core.IContext context, java.lang.String alias)
	{
		getMendixObject().setValue(context, MemberNames.Alias.toString(), alias);
	}

	/**
	 * @return value of RebuildKeyStore
	 */
	public final java.lang.Boolean getRebuildKeyStore()
	{
		return getRebuildKeyStore(getContext());
	}

	/**
	 * @param context
	 * @return value of RebuildKeyStore
	 */
	public final java.lang.Boolean getRebuildKeyStore(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.lang.Boolean) getMendixObject().getValue(context, MemberNames.RebuildKeyStore.toString());
	}

	/**
	 * Set value of RebuildKeyStore
	 * @param rebuildkeystore
	 */
	public final void setRebuildKeyStore(java.lang.Boolean rebuildkeystore)
	{
		setRebuildKeyStore(getContext(), rebuildkeystore);
	}

	/**
	 * Set value of RebuildKeyStore
	 * @param context
	 * @param rebuildkeystore
	 */
	public final void setRebuildKeyStore(com.mendix.systemwideinterfaces.core.IContext context, java.lang.Boolean rebuildkeystore)
	{
		getMendixObject().setValue(context, MemberNames.RebuildKeyStore.toString(), rebuildkeystore);
	}

	/**
	 * @return value of SPMetadata_KeyStore
	 */
	public final saml20.proxies.SPMetadata getSPMetadata_KeyStore() throws com.mendix.core.CoreException
	{
		return getSPMetadata_KeyStore(getContext());
	}

	/**
	 * @param context
	 * @return value of SPMetadata_KeyStore
	 */
	public final saml20.proxies.SPMetadata getSPMetadata_KeyStore(com.mendix.systemwideinterfaces.core.IContext context) throws com.mendix.core.CoreException
	{
		saml20.proxies.SPMetadata result = null;
		com.mendix.systemwideinterfaces.core.IMendixIdentifier identifier = getMendixObject().getValue(context, MemberNames.SPMetadata_KeyStore.toString());
		if (identifier != null)
			result = saml20.proxies.SPMetadata.load(context, identifier);
		return result;
	}

	/**
	 * Set value of SPMetadata_KeyStore
	 * @param spmetadata_keystore
	 */
	public final void setSPMetadata_KeyStore(saml20.proxies.SPMetadata spmetadata_keystore)
	{
		setSPMetadata_KeyStore(getContext(), spmetadata_keystore);
	}

	/**
	 * Set value of SPMetadata_KeyStore
	 * @param context
	 * @param spmetadata_keystore
	 */
	public final void setSPMetadata_KeyStore(com.mendix.systemwideinterfaces.core.IContext context, saml20.proxies.SPMetadata spmetadata_keystore)
	{
		if (spmetadata_keystore == null)
			getMendixObject().setValue(context, MemberNames.SPMetadata_KeyStore.toString(), null);
		else
			getMendixObject().setValue(context, MemberNames.SPMetadata_KeyStore.toString(), spmetadata_keystore.getMendixObject().getId());
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj == this)
			return true;

		if (obj != null && getClass().equals(obj.getClass()))
		{
			final saml20.proxies.KeyStore that = (saml20.proxies.KeyStore) obj;
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
		return "SAML20.KeyStore";
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