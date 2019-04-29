// This file was generated by Mendix Modeler.
//
// WARNING: Code you write here will be lost the next time you deploy the project.

package saml20.proxies;

public class Endpoint
{
	private final com.mendix.systemwideinterfaces.core.IMendixObject endpointMendixObject;

	private final com.mendix.systemwideinterfaces.core.IContext context;

	/**
	 * Internal name of this entity
	 */
	public static final java.lang.String entityName = "SAML20.Endpoint";

	/**
	 * Enum describing members of this entity
	 */
	public enum MemberNames
	{
		Binding("Binding"),
		Location("Location"),
		ResponseLocation("ResponseLocation"),
		index("index"),
		isDefault("isDefault"),
		ServiceType("ServiceType"),
		BindingType("BindingType"),
		Endpoint_RoleDescriptor("SAML20.Endpoint_RoleDescriptor");

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

	public Endpoint(com.mendix.systemwideinterfaces.core.IContext context)
	{
		this(context, com.mendix.core.Core.instantiate(context, "SAML20.Endpoint"));
	}

	protected Endpoint(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixObject endpointMendixObject)
	{
		if (endpointMendixObject == null)
			throw new java.lang.IllegalArgumentException("The given object cannot be null.");
		if (!com.mendix.core.Core.isSubClassOf("SAML20.Endpoint", endpointMendixObject.getType()))
			throw new java.lang.IllegalArgumentException("The given object is not a SAML20.Endpoint");

		this.endpointMendixObject = endpointMendixObject;
		this.context = context;
	}

	/**
	 * @deprecated Use 'Endpoint.load(IContext, IMendixIdentifier)' instead.
	 */
	@java.lang.Deprecated
	public static saml20.proxies.Endpoint initialize(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixIdentifier mendixIdentifier) throws com.mendix.core.CoreException
	{
		return saml20.proxies.Endpoint.load(context, mendixIdentifier);
	}

	/**
	 * Initialize a proxy using context (recommended). This context will be used for security checking when the get- and set-methods without context parameters are called.
	 * The get- and set-methods with context parameter should be used when for instance sudo access is necessary (IContext.createSudoClone() can be used to obtain sudo access).
	 */
	public static saml20.proxies.Endpoint initialize(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixObject mendixObject)
	{
		return new saml20.proxies.Endpoint(context, mendixObject);
	}

	public static saml20.proxies.Endpoint load(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixIdentifier mendixIdentifier) throws com.mendix.core.CoreException
	{
		com.mendix.systemwideinterfaces.core.IMendixObject mendixObject = com.mendix.core.Core.retrieveId(context, mendixIdentifier);
		return saml20.proxies.Endpoint.initialize(context, mendixObject);
	}

	public static java.util.List<saml20.proxies.Endpoint> load(com.mendix.systemwideinterfaces.core.IContext context, java.lang.String xpathConstraint) throws com.mendix.core.CoreException
	{
		java.util.List<saml20.proxies.Endpoint> result = new java.util.ArrayList<saml20.proxies.Endpoint>();
		for (com.mendix.systemwideinterfaces.core.IMendixObject obj : com.mendix.core.Core.retrieveXPathQuery(context, "//SAML20.Endpoint" + xpathConstraint))
			result.add(saml20.proxies.Endpoint.initialize(context, obj));
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
	 * @return value of Binding
	 */
	public final java.lang.String getBinding()
	{
		return getBinding(getContext());
	}

	/**
	 * @param context
	 * @return value of Binding
	 */
	public final java.lang.String getBinding(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.lang.String) getMendixObject().getValue(context, MemberNames.Binding.toString());
	}

	/**
	 * Set value of Binding
	 * @param binding
	 */
	public final void setBinding(java.lang.String binding)
	{
		setBinding(getContext(), binding);
	}

	/**
	 * Set value of Binding
	 * @param context
	 * @param binding
	 */
	public final void setBinding(com.mendix.systemwideinterfaces.core.IContext context, java.lang.String binding)
	{
		getMendixObject().setValue(context, MemberNames.Binding.toString(), binding);
	}

	/**
	 * @return value of Location
	 */
	public final java.lang.String getLocation()
	{
		return getLocation(getContext());
	}

	/**
	 * @param context
	 * @return value of Location
	 */
	public final java.lang.String getLocation(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.lang.String) getMendixObject().getValue(context, MemberNames.Location.toString());
	}

	/**
	 * Set value of Location
	 * @param location
	 */
	public final void setLocation(java.lang.String location)
	{
		setLocation(getContext(), location);
	}

	/**
	 * Set value of Location
	 * @param context
	 * @param location
	 */
	public final void setLocation(com.mendix.systemwideinterfaces.core.IContext context, java.lang.String location)
	{
		getMendixObject().setValue(context, MemberNames.Location.toString(), location);
	}

	/**
	 * @return value of ResponseLocation
	 */
	public final java.lang.String getResponseLocation()
	{
		return getResponseLocation(getContext());
	}

	/**
	 * @param context
	 * @return value of ResponseLocation
	 */
	public final java.lang.String getResponseLocation(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.lang.String) getMendixObject().getValue(context, MemberNames.ResponseLocation.toString());
	}

	/**
	 * Set value of ResponseLocation
	 * @param responselocation
	 */
	public final void setResponseLocation(java.lang.String responselocation)
	{
		setResponseLocation(getContext(), responselocation);
	}

	/**
	 * Set value of ResponseLocation
	 * @param context
	 * @param responselocation
	 */
	public final void setResponseLocation(com.mendix.systemwideinterfaces.core.IContext context, java.lang.String responselocation)
	{
		getMendixObject().setValue(context, MemberNames.ResponseLocation.toString(), responselocation);
	}

	/**
	 * @return value of index
	 */
	public final java.lang.Integer getindex()
	{
		return getindex(getContext());
	}

	/**
	 * @param context
	 * @return value of index
	 */
	public final java.lang.Integer getindex(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.lang.Integer) getMendixObject().getValue(context, MemberNames.index.toString());
	}

	/**
	 * Set value of index
	 * @param index
	 */
	public final void setindex(java.lang.Integer index)
	{
		setindex(getContext(), index);
	}

	/**
	 * Set value of index
	 * @param context
	 * @param index
	 */
	public final void setindex(com.mendix.systemwideinterfaces.core.IContext context, java.lang.Integer index)
	{
		getMendixObject().setValue(context, MemberNames.index.toString(), index);
	}

	/**
	 * @return value of isDefault
	 */
	public final java.lang.Boolean getisDefault()
	{
		return getisDefault(getContext());
	}

	/**
	 * @param context
	 * @return value of isDefault
	 */
	public final java.lang.Boolean getisDefault(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.lang.Boolean) getMendixObject().getValue(context, MemberNames.isDefault.toString());
	}

	/**
	 * Set value of isDefault
	 * @param isdefault
	 */
	public final void setisDefault(java.lang.Boolean isdefault)
	{
		setisDefault(getContext(), isdefault);
	}

	/**
	 * Set value of isDefault
	 * @param context
	 * @param isdefault
	 */
	public final void setisDefault(com.mendix.systemwideinterfaces.core.IContext context, java.lang.Boolean isdefault)
	{
		getMendixObject().setValue(context, MemberNames.isDefault.toString(), isdefault);
	}

	/**
	 * Set value of ServiceType
	 * @param servicetype
	 */
	public final saml20.proxies.ServiceType getServiceType()
	{
		return getServiceType(getContext());
	}

	/**
	 * @param context
	 * @return value of ServiceType
	 */
	public final saml20.proxies.ServiceType getServiceType(com.mendix.systemwideinterfaces.core.IContext context)
	{
		Object obj = getMendixObject().getValue(context, MemberNames.ServiceType.toString());
		if (obj == null)
			return null;

		return saml20.proxies.ServiceType.valueOf((java.lang.String) obj);
	}

	/**
	 * Set value of ServiceType
	 * @param servicetype
	 */
	public final void setServiceType(saml20.proxies.ServiceType servicetype)
	{
		setServiceType(getContext(), servicetype);
	}

	/**
	 * Set value of ServiceType
	 * @param context
	 * @param servicetype
	 */
	public final void setServiceType(com.mendix.systemwideinterfaces.core.IContext context, saml20.proxies.ServiceType servicetype)
	{
		if (servicetype != null)
			getMendixObject().setValue(context, MemberNames.ServiceType.toString(), servicetype.toString());
		else
			getMendixObject().setValue(context, MemberNames.ServiceType.toString(), null);
	}

	/**
	 * Set value of BindingType
	 * @param bindingtype
	 */
	public final saml20.proxies.BindingType getBindingType()
	{
		return getBindingType(getContext());
	}

	/**
	 * @param context
	 * @return value of BindingType
	 */
	public final saml20.proxies.BindingType getBindingType(com.mendix.systemwideinterfaces.core.IContext context)
	{
		Object obj = getMendixObject().getValue(context, MemberNames.BindingType.toString());
		if (obj == null)
			return null;

		return saml20.proxies.BindingType.valueOf((java.lang.String) obj);
	}

	/**
	 * Set value of BindingType
	 * @param bindingtype
	 */
	public final void setBindingType(saml20.proxies.BindingType bindingtype)
	{
		setBindingType(getContext(), bindingtype);
	}

	/**
	 * Set value of BindingType
	 * @param context
	 * @param bindingtype
	 */
	public final void setBindingType(com.mendix.systemwideinterfaces.core.IContext context, saml20.proxies.BindingType bindingtype)
	{
		if (bindingtype != null)
			getMendixObject().setValue(context, MemberNames.BindingType.toString(), bindingtype.toString());
		else
			getMendixObject().setValue(context, MemberNames.BindingType.toString(), null);
	}

	/**
	 * @return value of Endpoint_RoleDescriptor
	 */
	public final saml20.proxies.RoleDescriptor getEndpoint_RoleDescriptor() throws com.mendix.core.CoreException
	{
		return getEndpoint_RoleDescriptor(getContext());
	}

	/**
	 * @param context
	 * @return value of Endpoint_RoleDescriptor
	 */
	public final saml20.proxies.RoleDescriptor getEndpoint_RoleDescriptor(com.mendix.systemwideinterfaces.core.IContext context) throws com.mendix.core.CoreException
	{
		saml20.proxies.RoleDescriptor result = null;
		com.mendix.systemwideinterfaces.core.IMendixIdentifier identifier = getMendixObject().getValue(context, MemberNames.Endpoint_RoleDescriptor.toString());
		if (identifier != null)
			result = saml20.proxies.RoleDescriptor.load(context, identifier);
		return result;
	}

	/**
	 * Set value of Endpoint_RoleDescriptor
	 * @param endpoint_roledescriptor
	 */
	public final void setEndpoint_RoleDescriptor(saml20.proxies.RoleDescriptor endpoint_roledescriptor)
	{
		setEndpoint_RoleDescriptor(getContext(), endpoint_roledescriptor);
	}

	/**
	 * Set value of Endpoint_RoleDescriptor
	 * @param context
	 * @param endpoint_roledescriptor
	 */
	public final void setEndpoint_RoleDescriptor(com.mendix.systemwideinterfaces.core.IContext context, saml20.proxies.RoleDescriptor endpoint_roledescriptor)
	{
		if (endpoint_roledescriptor == null)
			getMendixObject().setValue(context, MemberNames.Endpoint_RoleDescriptor.toString(), null);
		else
			getMendixObject().setValue(context, MemberNames.Endpoint_RoleDescriptor.toString(), endpoint_roledescriptor.getMendixObject().getId());
	}

	/**
	 * @return the IMendixObject instance of this proxy for use in the Core interface.
	 */
	public final com.mendix.systemwideinterfaces.core.IMendixObject getMendixObject()
	{
		return endpointMendixObject;
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
			final saml20.proxies.Endpoint that = (saml20.proxies.Endpoint) obj;
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
		return "SAML20.Endpoint";
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
