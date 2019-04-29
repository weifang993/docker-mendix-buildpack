// This file was generated by Mendix Modeler.
//
// WARNING: Code you write here will be lost the next time you deploy the project.

package saml20.proxies;

public class ClaimMap
{
	private final com.mendix.systemwideinterfaces.core.IMendixObject claimMapMendixObject;

	private final com.mendix.systemwideinterfaces.core.IContext context;

	/**
	 * Internal name of this entity
	 */
	public static final java.lang.String entityName = "SAML20.ClaimMap";

	/**
	 * Enum describing members of this entity
	 */
	public enum MemberNames
	{
		ClaimMap_SSOConfiguration("SAML20.ClaimMap_SSOConfiguration"),
		ClaimMap_Attribute("SAML20.ClaimMap_Attribute"),
		ClaimMap_MxObjectMember("SAML20.ClaimMap_MxObjectMember");

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

	public ClaimMap(com.mendix.systemwideinterfaces.core.IContext context)
	{
		this(context, com.mendix.core.Core.instantiate(context, "SAML20.ClaimMap"));
	}

	protected ClaimMap(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixObject claimMapMendixObject)
	{
		if (claimMapMendixObject == null)
			throw new java.lang.IllegalArgumentException("The given object cannot be null.");
		if (!com.mendix.core.Core.isSubClassOf("SAML20.ClaimMap", claimMapMendixObject.getType()))
			throw new java.lang.IllegalArgumentException("The given object is not a SAML20.ClaimMap");

		this.claimMapMendixObject = claimMapMendixObject;
		this.context = context;
	}

	/**
	 * @deprecated Use 'ClaimMap.load(IContext, IMendixIdentifier)' instead.
	 */
	@java.lang.Deprecated
	public static saml20.proxies.ClaimMap initialize(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixIdentifier mendixIdentifier) throws com.mendix.core.CoreException
	{
		return saml20.proxies.ClaimMap.load(context, mendixIdentifier);
	}

	/**
	 * Initialize a proxy using context (recommended). This context will be used for security checking when the get- and set-methods without context parameters are called.
	 * The get- and set-methods with context parameter should be used when for instance sudo access is necessary (IContext.createSudoClone() can be used to obtain sudo access).
	 */
	public static saml20.proxies.ClaimMap initialize(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixObject mendixObject)
	{
		return new saml20.proxies.ClaimMap(context, mendixObject);
	}

	public static saml20.proxies.ClaimMap load(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixIdentifier mendixIdentifier) throws com.mendix.core.CoreException
	{
		com.mendix.systemwideinterfaces.core.IMendixObject mendixObject = com.mendix.core.Core.retrieveId(context, mendixIdentifier);
		return saml20.proxies.ClaimMap.initialize(context, mendixObject);
	}

	public static java.util.List<saml20.proxies.ClaimMap> load(com.mendix.systemwideinterfaces.core.IContext context, java.lang.String xpathConstraint) throws com.mendix.core.CoreException
	{
		java.util.List<saml20.proxies.ClaimMap> result = new java.util.ArrayList<saml20.proxies.ClaimMap>();
		for (com.mendix.systemwideinterfaces.core.IMendixObject obj : com.mendix.core.Core.retrieveXPathQuery(context, "//SAML20.ClaimMap" + xpathConstraint))
			result.add(saml20.proxies.ClaimMap.initialize(context, obj));
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
	 * @return value of ClaimMap_SSOConfiguration
	 */
	public final saml20.proxies.SSOConfiguration getClaimMap_SSOConfiguration() throws com.mendix.core.CoreException
	{
		return getClaimMap_SSOConfiguration(getContext());
	}

	/**
	 * @param context
	 * @return value of ClaimMap_SSOConfiguration
	 */
	public final saml20.proxies.SSOConfiguration getClaimMap_SSOConfiguration(com.mendix.systemwideinterfaces.core.IContext context) throws com.mendix.core.CoreException
	{
		saml20.proxies.SSOConfiguration result = null;
		com.mendix.systemwideinterfaces.core.IMendixIdentifier identifier = getMendixObject().getValue(context, MemberNames.ClaimMap_SSOConfiguration.toString());
		if (identifier != null)
			result = saml20.proxies.SSOConfiguration.load(context, identifier);
		return result;
	}

	/**
	 * Set value of ClaimMap_SSOConfiguration
	 * @param claimmap_ssoconfiguration
	 */
	public final void setClaimMap_SSOConfiguration(saml20.proxies.SSOConfiguration claimmap_ssoconfiguration)
	{
		setClaimMap_SSOConfiguration(getContext(), claimmap_ssoconfiguration);
	}

	/**
	 * Set value of ClaimMap_SSOConfiguration
	 * @param context
	 * @param claimmap_ssoconfiguration
	 */
	public final void setClaimMap_SSOConfiguration(com.mendix.systemwideinterfaces.core.IContext context, saml20.proxies.SSOConfiguration claimmap_ssoconfiguration)
	{
		if (claimmap_ssoconfiguration == null)
			getMendixObject().setValue(context, MemberNames.ClaimMap_SSOConfiguration.toString(), null);
		else
			getMendixObject().setValue(context, MemberNames.ClaimMap_SSOConfiguration.toString(), claimmap_ssoconfiguration.getMendixObject().getId());
	}

	/**
	 * @return value of ClaimMap_Attribute
	 */
	public final saml20.proxies.Attribute getClaimMap_Attribute() throws com.mendix.core.CoreException
	{
		return getClaimMap_Attribute(getContext());
	}

	/**
	 * @param context
	 * @return value of ClaimMap_Attribute
	 */
	public final saml20.proxies.Attribute getClaimMap_Attribute(com.mendix.systemwideinterfaces.core.IContext context) throws com.mendix.core.CoreException
	{
		saml20.proxies.Attribute result = null;
		com.mendix.systemwideinterfaces.core.IMendixIdentifier identifier = getMendixObject().getValue(context, MemberNames.ClaimMap_Attribute.toString());
		if (identifier != null)
			result = saml20.proxies.Attribute.load(context, identifier);
		return result;
	}

	/**
	 * Set value of ClaimMap_Attribute
	 * @param claimmap_attribute
	 */
	public final void setClaimMap_Attribute(saml20.proxies.Attribute claimmap_attribute)
	{
		setClaimMap_Attribute(getContext(), claimmap_attribute);
	}

	/**
	 * Set value of ClaimMap_Attribute
	 * @param context
	 * @param claimmap_attribute
	 */
	public final void setClaimMap_Attribute(com.mendix.systemwideinterfaces.core.IContext context, saml20.proxies.Attribute claimmap_attribute)
	{
		if (claimmap_attribute == null)
			getMendixObject().setValue(context, MemberNames.ClaimMap_Attribute.toString(), null);
		else
			getMendixObject().setValue(context, MemberNames.ClaimMap_Attribute.toString(), claimmap_attribute.getMendixObject().getId());
	}

	/**
	 * @return value of ClaimMap_MxObjectMember
	 */
	public final mxmodelreflection.proxies.MxObjectMember getClaimMap_MxObjectMember() throws com.mendix.core.CoreException
	{
		return getClaimMap_MxObjectMember(getContext());
	}

	/**
	 * @param context
	 * @return value of ClaimMap_MxObjectMember
	 */
	public final mxmodelreflection.proxies.MxObjectMember getClaimMap_MxObjectMember(com.mendix.systemwideinterfaces.core.IContext context) throws com.mendix.core.CoreException
	{
		mxmodelreflection.proxies.MxObjectMember result = null;
		com.mendix.systemwideinterfaces.core.IMendixIdentifier identifier = getMendixObject().getValue(context, MemberNames.ClaimMap_MxObjectMember.toString());
		if (identifier != null)
			result = mxmodelreflection.proxies.MxObjectMember.load(context, identifier);
		return result;
	}

	/**
	 * Set value of ClaimMap_MxObjectMember
	 * @param claimmap_mxobjectmember
	 */
	public final void setClaimMap_MxObjectMember(mxmodelreflection.proxies.MxObjectMember claimmap_mxobjectmember)
	{
		setClaimMap_MxObjectMember(getContext(), claimmap_mxobjectmember);
	}

	/**
	 * Set value of ClaimMap_MxObjectMember
	 * @param context
	 * @param claimmap_mxobjectmember
	 */
	public final void setClaimMap_MxObjectMember(com.mendix.systemwideinterfaces.core.IContext context, mxmodelreflection.proxies.MxObjectMember claimmap_mxobjectmember)
	{
		if (claimmap_mxobjectmember == null)
			getMendixObject().setValue(context, MemberNames.ClaimMap_MxObjectMember.toString(), null);
		else
			getMendixObject().setValue(context, MemberNames.ClaimMap_MxObjectMember.toString(), claimmap_mxobjectmember.getMendixObject().getId());
	}

	/**
	 * @return the IMendixObject instance of this proxy for use in the Core interface.
	 */
	public final com.mendix.systemwideinterfaces.core.IMendixObject getMendixObject()
	{
		return claimMapMendixObject;
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
			final saml20.proxies.ClaimMap that = (saml20.proxies.ClaimMap) obj;
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
		return "SAML20.ClaimMap";
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
