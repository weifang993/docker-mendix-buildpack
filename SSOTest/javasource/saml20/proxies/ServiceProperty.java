// This file was generated by Mendix Modeler.
//
// WARNING: Code you write here will be lost the next time you deploy the project.

package saml20.proxies;

public class ServiceProperty
{
	private final com.mendix.systemwideinterfaces.core.IMendixObject servicePropertyMendixObject;

	private final com.mendix.systemwideinterfaces.core.IContext context;

	/**
	 * Internal name of this entity
	 */
	public static final java.lang.String entityName = "SAML20.ServiceProperty";

	/**
	 * Enum describing members of this entity
	 */
	public enum MemberNames
	{
		_content_("_content_"),
		lang("lang");

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

	public ServiceProperty(com.mendix.systemwideinterfaces.core.IContext context)
	{
		this(context, com.mendix.core.Core.instantiate(context, "SAML20.ServiceProperty"));
	}

	protected ServiceProperty(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixObject servicePropertyMendixObject)
	{
		if (servicePropertyMendixObject == null)
			throw new java.lang.IllegalArgumentException("The given object cannot be null.");
		if (!com.mendix.core.Core.isSubClassOf("SAML20.ServiceProperty", servicePropertyMendixObject.getType()))
			throw new java.lang.IllegalArgumentException("The given object is not a SAML20.ServiceProperty");

		this.servicePropertyMendixObject = servicePropertyMendixObject;
		this.context = context;
	}

	/**
	 * @deprecated Use 'ServiceProperty.load(IContext, IMendixIdentifier)' instead.
	 */
	@java.lang.Deprecated
	public static saml20.proxies.ServiceProperty initialize(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixIdentifier mendixIdentifier) throws com.mendix.core.CoreException
	{
		return saml20.proxies.ServiceProperty.load(context, mendixIdentifier);
	}

	/**
	 * Initialize a proxy using context (recommended). This context will be used for security checking when the get- and set-methods without context parameters are called.
	 * The get- and set-methods with context parameter should be used when for instance sudo access is necessary (IContext.createSudoClone() can be used to obtain sudo access).
	 */
	public static saml20.proxies.ServiceProperty initialize(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixObject mendixObject)
	{
		return new saml20.proxies.ServiceProperty(context, mendixObject);
	}

	public static saml20.proxies.ServiceProperty load(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixIdentifier mendixIdentifier) throws com.mendix.core.CoreException
	{
		com.mendix.systemwideinterfaces.core.IMendixObject mendixObject = com.mendix.core.Core.retrieveId(context, mendixIdentifier);
		return saml20.proxies.ServiceProperty.initialize(context, mendixObject);
	}

	public static java.util.List<saml20.proxies.ServiceProperty> load(com.mendix.systemwideinterfaces.core.IContext context, java.lang.String xpathConstraint) throws com.mendix.core.CoreException
	{
		java.util.List<saml20.proxies.ServiceProperty> result = new java.util.ArrayList<saml20.proxies.ServiceProperty>();
		for (com.mendix.systemwideinterfaces.core.IMendixObject obj : com.mendix.core.Core.retrieveXPathQuery(context, "//SAML20.ServiceProperty" + xpathConstraint))
			result.add(saml20.proxies.ServiceProperty.initialize(context, obj));
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
	 * @return value of _content_
	 */
	public final java.lang.String get_content_()
	{
		return get_content_(getContext());
	}

	/**
	 * @param context
	 * @return value of _content_
	 */
	public final java.lang.String get_content_(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.lang.String) getMendixObject().getValue(context, MemberNames._content_.toString());
	}

	/**
	 * Set value of _content_
	 * @param _content_
	 */
	public final void set_content_(java.lang.String _content_)
	{
		set_content_(getContext(), _content_);
	}

	/**
	 * Set value of _content_
	 * @param context
	 * @param _content_
	 */
	public final void set_content_(com.mendix.systemwideinterfaces.core.IContext context, java.lang.String _content_)
	{
		getMendixObject().setValue(context, MemberNames._content_.toString(), _content_);
	}

	/**
	 * @return value of lang
	 */
	public final java.lang.String getlang()
	{
		return getlang(getContext());
	}

	/**
	 * @param context
	 * @return value of lang
	 */
	public final java.lang.String getlang(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.lang.String) getMendixObject().getValue(context, MemberNames.lang.toString());
	}

	/**
	 * Set value of lang
	 * @param lang
	 */
	public final void setlang(java.lang.String lang)
	{
		setlang(getContext(), lang);
	}

	/**
	 * Set value of lang
	 * @param context
	 * @param lang
	 */
	public final void setlang(com.mendix.systemwideinterfaces.core.IContext context, java.lang.String lang)
	{
		getMendixObject().setValue(context, MemberNames.lang.toString(), lang);
	}

	/**
	 * @return the IMendixObject instance of this proxy for use in the Core interface.
	 */
	public final com.mendix.systemwideinterfaces.core.IMendixObject getMendixObject()
	{
		return servicePropertyMendixObject;
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
			final saml20.proxies.ServiceProperty that = (saml20.proxies.ServiceProperty) obj;
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
		return "SAML20.ServiceProperty";
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
