// This file was generated by Mendix Modeler.
//
// WARNING: Code you write here will be lost the next time you deploy the project.

package saml20.proxies;

public class EntityDescriptor
{
	private final com.mendix.systemwideinterfaces.core.IMendixObject entityDescriptorMendixObject;

	private final com.mendix.systemwideinterfaces.core.IContext context;

	/**
	 * Internal name of this entity
	 */
	public static final java.lang.String entityName = "SAML20.EntityDescriptor";

	/**
	 * Enum describing members of this entity
	 */
	public enum MemberNames
	{
		entityID("entityID"),
		validUntil("validUntil"),
		cacheDuration("cacheDuration"),
		_ID("_ID"),
		Updated("Updated"),
		EntityDescriptor_EntitiesDescriptor("SAML20.EntityDescriptor_EntitiesDescriptor");

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

	public EntityDescriptor(com.mendix.systemwideinterfaces.core.IContext context)
	{
		this(context, com.mendix.core.Core.instantiate(context, "SAML20.EntityDescriptor"));
	}

	protected EntityDescriptor(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixObject entityDescriptorMendixObject)
	{
		if (entityDescriptorMendixObject == null)
			throw new java.lang.IllegalArgumentException("The given object cannot be null.");
		if (!com.mendix.core.Core.isSubClassOf("SAML20.EntityDescriptor", entityDescriptorMendixObject.getType()))
			throw new java.lang.IllegalArgumentException("The given object is not a SAML20.EntityDescriptor");

		this.entityDescriptorMendixObject = entityDescriptorMendixObject;
		this.context = context;
	}

	/**
	 * @deprecated Use 'EntityDescriptor.load(IContext, IMendixIdentifier)' instead.
	 */
	@Deprecated
	public static saml20.proxies.EntityDescriptor initialize(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixIdentifier mendixIdentifier) throws com.mendix.core.CoreException
	{
		return saml20.proxies.EntityDescriptor.load(context, mendixIdentifier);
	}

	/**
	 * Initialize a proxy using context (recommended). This context will be used for security checking when the get- and set-methods without context parameters are called.
	 * The get- and set-methods with context parameter should be used when for instance sudo access is necessary (IContext.createSudoClone() can be used to obtain sudo access).
	 */
	public static saml20.proxies.EntityDescriptor initialize(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixObject mendixObject)
	{
		return new saml20.proxies.EntityDescriptor(context, mendixObject);
	}

	public static saml20.proxies.EntityDescriptor load(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixIdentifier mendixIdentifier) throws com.mendix.core.CoreException
	{
		com.mendix.systemwideinterfaces.core.IMendixObject mendixObject = com.mendix.core.Core.retrieveId(context, mendixIdentifier);
		return saml20.proxies.EntityDescriptor.initialize(context, mendixObject);
	}

	public static java.util.List<saml20.proxies.EntityDescriptor> load(com.mendix.systemwideinterfaces.core.IContext context, java.lang.String xpathConstraint) throws com.mendix.core.CoreException
	{
		java.util.List<saml20.proxies.EntityDescriptor> result = new java.util.ArrayList<saml20.proxies.EntityDescriptor>();
		for (com.mendix.systemwideinterfaces.core.IMendixObject obj : com.mendix.core.Core.retrieveXPathQuery(context, "//SAML20.EntityDescriptor" + xpathConstraint))
			result.add(saml20.proxies.EntityDescriptor.initialize(context, obj));
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
	 * @return value of entityID
	 */
	public final java.lang.String getentityID()
	{
		return getentityID(getContext());
	}

	/**
	 * @param context
	 * @return value of entityID
	 */
	public final java.lang.String getentityID(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.lang.String) getMendixObject().getValue(context, MemberNames.entityID.toString());
	}

	/**
	 * Set value of entityID
	 * @param entityid
	 */
	public final void setentityID(java.lang.String entityid)
	{
		setentityID(getContext(), entityid);
	}

	/**
	 * Set value of entityID
	 * @param context
	 * @param entityid
	 */
	public final void setentityID(com.mendix.systemwideinterfaces.core.IContext context, java.lang.String entityid)
	{
		getMendixObject().setValue(context, MemberNames.entityID.toString(), entityid);
	}

	/**
	 * @return value of validUntil
	 */
	public final java.util.Date getvalidUntil()
	{
		return getvalidUntil(getContext());
	}

	/**
	 * @param context
	 * @return value of validUntil
	 */
	public final java.util.Date getvalidUntil(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.util.Date) getMendixObject().getValue(context, MemberNames.validUntil.toString());
	}

	/**
	 * Set value of validUntil
	 * @param validuntil
	 */
	public final void setvalidUntil(java.util.Date validuntil)
	{
		setvalidUntil(getContext(), validuntil);
	}

	/**
	 * Set value of validUntil
	 * @param context
	 * @param validuntil
	 */
	public final void setvalidUntil(com.mendix.systemwideinterfaces.core.IContext context, java.util.Date validuntil)
	{
		getMendixObject().setValue(context, MemberNames.validUntil.toString(), validuntil);
	}

	/**
	 * @return value of cacheDuration
	 */
	public final java.lang.String getcacheDuration()
	{
		return getcacheDuration(getContext());
	}

	/**
	 * @param context
	 * @return value of cacheDuration
	 */
	public final java.lang.String getcacheDuration(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.lang.String) getMendixObject().getValue(context, MemberNames.cacheDuration.toString());
	}

	/**
	 * Set value of cacheDuration
	 * @param cacheduration
	 */
	public final void setcacheDuration(java.lang.String cacheduration)
	{
		setcacheDuration(getContext(), cacheduration);
	}

	/**
	 * Set value of cacheDuration
	 * @param context
	 * @param cacheduration
	 */
	public final void setcacheDuration(com.mendix.systemwideinterfaces.core.IContext context, java.lang.String cacheduration)
	{
		getMendixObject().setValue(context, MemberNames.cacheDuration.toString(), cacheduration);
	}

	/**
	 * @return value of _ID
	 */
	public final java.lang.String get_ID()
	{
		return get_ID(getContext());
	}

	/**
	 * @param context
	 * @return value of _ID
	 */
	public final java.lang.String get_ID(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.lang.String) getMendixObject().getValue(context, MemberNames._ID.toString());
	}

	/**
	 * Set value of _ID
	 * @param _id
	 */
	public final void set_ID(java.lang.String _id)
	{
		set_ID(getContext(), _id);
	}

	/**
	 * Set value of _ID
	 * @param context
	 * @param _id
	 */
	public final void set_ID(com.mendix.systemwideinterfaces.core.IContext context, java.lang.String _id)
	{
		getMendixObject().setValue(context, MemberNames._ID.toString(), _id);
	}

	/**
	 * @return value of Updated
	 */
	public final java.lang.Boolean getUpdated()
	{
		return getUpdated(getContext());
	}

	/**
	 * @param context
	 * @return value of Updated
	 */
	public final java.lang.Boolean getUpdated(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.lang.Boolean) getMendixObject().getValue(context, MemberNames.Updated.toString());
	}

	/**
	 * Set value of Updated
	 * @param updated
	 */
	public final void setUpdated(java.lang.Boolean updated)
	{
		setUpdated(getContext(), updated);
	}

	/**
	 * Set value of Updated
	 * @param context
	 * @param updated
	 */
	public final void setUpdated(com.mendix.systemwideinterfaces.core.IContext context, java.lang.Boolean updated)
	{
		getMendixObject().setValue(context, MemberNames.Updated.toString(), updated);
	}

	/**
	 * @return value of EntityDescriptor_EntitiesDescriptor
	 */
	public final saml20.proxies.EntitiesDescriptor getEntityDescriptor_EntitiesDescriptor() throws com.mendix.core.CoreException
	{
		return getEntityDescriptor_EntitiesDescriptor(getContext());
	}

	/**
	 * @param context
	 * @return value of EntityDescriptor_EntitiesDescriptor
	 */
	public final saml20.proxies.EntitiesDescriptor getEntityDescriptor_EntitiesDescriptor(com.mendix.systemwideinterfaces.core.IContext context) throws com.mendix.core.CoreException
	{
		saml20.proxies.EntitiesDescriptor result = null;
		com.mendix.systemwideinterfaces.core.IMendixIdentifier identifier = getMendixObject().getValue(context, MemberNames.EntityDescriptor_EntitiesDescriptor.toString());
		if (identifier != null)
			result = saml20.proxies.EntitiesDescriptor.load(context, identifier);
		return result;
	}

	/**
	 * Set value of EntityDescriptor_EntitiesDescriptor
	 * @param entitydescriptor_entitiesdescriptor
	 */
	public final void setEntityDescriptor_EntitiesDescriptor(saml20.proxies.EntitiesDescriptor entitydescriptor_entitiesdescriptor)
	{
		setEntityDescriptor_EntitiesDescriptor(getContext(), entitydescriptor_entitiesdescriptor);
	}

	/**
	 * Set value of EntityDescriptor_EntitiesDescriptor
	 * @param context
	 * @param entitydescriptor_entitiesdescriptor
	 */
	public final void setEntityDescriptor_EntitiesDescriptor(com.mendix.systemwideinterfaces.core.IContext context, saml20.proxies.EntitiesDescriptor entitydescriptor_entitiesdescriptor)
	{
		if (entitydescriptor_entitiesdescriptor == null)
			getMendixObject().setValue(context, MemberNames.EntityDescriptor_EntitiesDescriptor.toString(), null);
		else
			getMendixObject().setValue(context, MemberNames.EntityDescriptor_EntitiesDescriptor.toString(), entitydescriptor_entitiesdescriptor.getMendixObject().getId());
	}

	/**
	 * @return the IMendixObject instance of this proxy for use in the Core interface.
	 */
	public final com.mendix.systemwideinterfaces.core.IMendixObject getMendixObject()
	{
		return entityDescriptorMendixObject;
	}

	/**
	 * @return the IContext instance of this proxy, or null if no IContext instance was specified at initialization.
	 */
	public final com.mendix.systemwideinterfaces.core.IContext getContext()
	{
		return context;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj == this)
			return true;

		if (obj != null && getClass().equals(obj.getClass()))
		{
			final saml20.proxies.EntityDescriptor that = (saml20.proxies.EntityDescriptor) obj;
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
		return "SAML20.EntityDescriptor";
	}

	/**
	 * @return String GUID from this object, format: ID_0000000000
	 * @deprecated Use getMendixObject().getId().toLong() to get a unique identifier for this object.
	 */
	@Deprecated
	public java.lang.String getGUID()
	{
		return "ID_" + getMendixObject().getId().toLong();
	}
}
