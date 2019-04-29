// This file was generated by Mendix Modeler.
//
// WARNING: Code you write here will be lost the next time you deploy the project.

package mxmodelreflection.proxies;

public class TestPattern
{
	private final com.mendix.systemwideinterfaces.core.IMendixObject testPatternMendixObject;

	private final com.mendix.systemwideinterfaces.core.IContext context;

	/**
	 * Internal name of this entity
	 */
	public static final java.lang.String entityName = "MxModelReflection.TestPattern";

	/**
	 * Enum describing members of this entity
	 */
	public enum MemberNames
	{
		DisplayPattern("DisplayPattern"),
		AttributeTypeEnum("AttributeTypeEnum"),
		BooleanAttribute("BooleanAttribute"),
		FloatAttribute("FloatAttribute"),
		DecimalAttribute("DecimalAttribute"),
		DateTimeAttribute("DateTimeAttribute"),
		IntegerAttribute("IntegerAttribute"),
		LongAttribute("LongAttribute"),
		StringAttribute("StringAttribute"),
		Result("Result");

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

	public TestPattern(com.mendix.systemwideinterfaces.core.IContext context)
	{
		this(context, com.mendix.core.Core.instantiate(context, "MxModelReflection.TestPattern"));
	}

	protected TestPattern(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixObject testPatternMendixObject)
	{
		if (testPatternMendixObject == null)
			throw new java.lang.IllegalArgumentException("The given object cannot be null.");
		if (!com.mendix.core.Core.isSubClassOf("MxModelReflection.TestPattern", testPatternMendixObject.getType()))
			throw new java.lang.IllegalArgumentException("The given object is not a MxModelReflection.TestPattern");

		this.testPatternMendixObject = testPatternMendixObject;
		this.context = context;
	}

	/**
	 * @deprecated Use 'TestPattern.load(IContext, IMendixIdentifier)' instead.
	 */
	@java.lang.Deprecated
	public static mxmodelreflection.proxies.TestPattern initialize(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixIdentifier mendixIdentifier) throws com.mendix.core.CoreException
	{
		return mxmodelreflection.proxies.TestPattern.load(context, mendixIdentifier);
	}

	/**
	 * Initialize a proxy using context (recommended). This context will be used for security checking when the get- and set-methods without context parameters are called.
	 * The get- and set-methods with context parameter should be used when for instance sudo access is necessary (IContext.createSudoClone() can be used to obtain sudo access).
	 */
	public static mxmodelreflection.proxies.TestPattern initialize(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixObject mendixObject)
	{
		return new mxmodelreflection.proxies.TestPattern(context, mendixObject);
	}

	public static mxmodelreflection.proxies.TestPattern load(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixIdentifier mendixIdentifier) throws com.mendix.core.CoreException
	{
		com.mendix.systemwideinterfaces.core.IMendixObject mendixObject = com.mendix.core.Core.retrieveId(context, mendixIdentifier);
		return mxmodelreflection.proxies.TestPattern.initialize(context, mendixObject);
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
	 * @return value of DisplayPattern
	 */
	public final java.lang.String getDisplayPattern()
	{
		return getDisplayPattern(getContext());
	}

	/**
	 * @param context
	 * @return value of DisplayPattern
	 */
	public final java.lang.String getDisplayPattern(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.lang.String) getMendixObject().getValue(context, MemberNames.DisplayPattern.toString());
	}

	/**
	 * Set value of DisplayPattern
	 * @param displaypattern
	 */
	public final void setDisplayPattern(java.lang.String displaypattern)
	{
		setDisplayPattern(getContext(), displaypattern);
	}

	/**
	 * Set value of DisplayPattern
	 * @param context
	 * @param displaypattern
	 */
	public final void setDisplayPattern(com.mendix.systemwideinterfaces.core.IContext context, java.lang.String displaypattern)
	{
		getMendixObject().setValue(context, MemberNames.DisplayPattern.toString(), displaypattern);
	}

	/**
	 * Set value of AttributeTypeEnum
	 * @param attributetypeenum
	 */
	public final mxmodelreflection.proxies.AttributeTypes getAttributeTypeEnum()
	{
		return getAttributeTypeEnum(getContext());
	}

	/**
	 * @param context
	 * @return value of AttributeTypeEnum
	 */
	public final mxmodelreflection.proxies.AttributeTypes getAttributeTypeEnum(com.mendix.systemwideinterfaces.core.IContext context)
	{
		Object obj = getMendixObject().getValue(context, MemberNames.AttributeTypeEnum.toString());
		if (obj == null)
			return null;

		return mxmodelreflection.proxies.AttributeTypes.valueOf((java.lang.String) obj);
	}

	/**
	 * Set value of AttributeTypeEnum
	 * @param attributetypeenum
	 */
	public final void setAttributeTypeEnum(mxmodelreflection.proxies.AttributeTypes attributetypeenum)
	{
		setAttributeTypeEnum(getContext(), attributetypeenum);
	}

	/**
	 * Set value of AttributeTypeEnum
	 * @param context
	 * @param attributetypeenum
	 */
	public final void setAttributeTypeEnum(com.mendix.systemwideinterfaces.core.IContext context, mxmodelreflection.proxies.AttributeTypes attributetypeenum)
	{
		if (attributetypeenum != null)
			getMendixObject().setValue(context, MemberNames.AttributeTypeEnum.toString(), attributetypeenum.toString());
		else
			getMendixObject().setValue(context, MemberNames.AttributeTypeEnum.toString(), null);
	}

	/**
	 * @return value of BooleanAttribute
	 */
	public final java.lang.Boolean getBooleanAttribute()
	{
		return getBooleanAttribute(getContext());
	}

	/**
	 * @param context
	 * @return value of BooleanAttribute
	 */
	public final java.lang.Boolean getBooleanAttribute(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.lang.Boolean) getMendixObject().getValue(context, MemberNames.BooleanAttribute.toString());
	}

	/**
	 * Set value of BooleanAttribute
	 * @param booleanattribute
	 */
	public final void setBooleanAttribute(java.lang.Boolean booleanattribute)
	{
		setBooleanAttribute(getContext(), booleanattribute);
	}

	/**
	 * Set value of BooleanAttribute
	 * @param context
	 * @param booleanattribute
	 */
	public final void setBooleanAttribute(com.mendix.systemwideinterfaces.core.IContext context, java.lang.Boolean booleanattribute)
	{
		getMendixObject().setValue(context, MemberNames.BooleanAttribute.toString(), booleanattribute);
	}

	/**
	 * @return value of FloatAttribute
	 */
	public final java.lang.Double getFloatAttribute()
	{
		return getFloatAttribute(getContext());
	}

	/**
	 * @param context
	 * @return value of FloatAttribute
	 */
	public final java.lang.Double getFloatAttribute(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.lang.Double) getMendixObject().getValue(context, MemberNames.FloatAttribute.toString());
	}

	/**
	 * Set value of FloatAttribute
	 * @param floatattribute
	 */
	public final void setFloatAttribute(java.lang.Double floatattribute)
	{
		setFloatAttribute(getContext(), floatattribute);
	}

	/**
	 * Set value of FloatAttribute
	 * @param context
	 * @param floatattribute
	 */
	public final void setFloatAttribute(com.mendix.systemwideinterfaces.core.IContext context, java.lang.Double floatattribute)
	{
		getMendixObject().setValue(context, MemberNames.FloatAttribute.toString(), floatattribute);
	}

	/**
	 * @return value of DecimalAttribute
	 */
	public final java.math.BigDecimal getDecimalAttribute()
	{
		return getDecimalAttribute(getContext());
	}

	/**
	 * @param context
	 * @return value of DecimalAttribute
	 */
	public final java.math.BigDecimal getDecimalAttribute(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.math.BigDecimal) getMendixObject().getValue(context, MemberNames.DecimalAttribute.toString());
	}

	/**
	 * Set value of DecimalAttribute
	 * @param decimalattribute
	 */
	public final void setDecimalAttribute(java.math.BigDecimal decimalattribute)
	{
		setDecimalAttribute(getContext(), decimalattribute);
	}

	/**
	 * Set value of DecimalAttribute
	 * @param context
	 * @param decimalattribute
	 */
	public final void setDecimalAttribute(com.mendix.systemwideinterfaces.core.IContext context, java.math.BigDecimal decimalattribute)
	{
		getMendixObject().setValue(context, MemberNames.DecimalAttribute.toString(), decimalattribute);
	}

	/**
	 * @return value of DateTimeAttribute
	 */
	public final java.util.Date getDateTimeAttribute()
	{
		return getDateTimeAttribute(getContext());
	}

	/**
	 * @param context
	 * @return value of DateTimeAttribute
	 */
	public final java.util.Date getDateTimeAttribute(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.util.Date) getMendixObject().getValue(context, MemberNames.DateTimeAttribute.toString());
	}

	/**
	 * Set value of DateTimeAttribute
	 * @param datetimeattribute
	 */
	public final void setDateTimeAttribute(java.util.Date datetimeattribute)
	{
		setDateTimeAttribute(getContext(), datetimeattribute);
	}

	/**
	 * Set value of DateTimeAttribute
	 * @param context
	 * @param datetimeattribute
	 */
	public final void setDateTimeAttribute(com.mendix.systemwideinterfaces.core.IContext context, java.util.Date datetimeattribute)
	{
		getMendixObject().setValue(context, MemberNames.DateTimeAttribute.toString(), datetimeattribute);
	}

	/**
	 * @return value of IntegerAttribute
	 */
	public final java.lang.Integer getIntegerAttribute()
	{
		return getIntegerAttribute(getContext());
	}

	/**
	 * @param context
	 * @return value of IntegerAttribute
	 */
	public final java.lang.Integer getIntegerAttribute(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.lang.Integer) getMendixObject().getValue(context, MemberNames.IntegerAttribute.toString());
	}

	/**
	 * Set value of IntegerAttribute
	 * @param integerattribute
	 */
	public final void setIntegerAttribute(java.lang.Integer integerattribute)
	{
		setIntegerAttribute(getContext(), integerattribute);
	}

	/**
	 * Set value of IntegerAttribute
	 * @param context
	 * @param integerattribute
	 */
	public final void setIntegerAttribute(com.mendix.systemwideinterfaces.core.IContext context, java.lang.Integer integerattribute)
	{
		getMendixObject().setValue(context, MemberNames.IntegerAttribute.toString(), integerattribute);
	}

	/**
	 * @return value of LongAttribute
	 */
	public final java.lang.Long getLongAttribute()
	{
		return getLongAttribute(getContext());
	}

	/**
	 * @param context
	 * @return value of LongAttribute
	 */
	public final java.lang.Long getLongAttribute(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.lang.Long) getMendixObject().getValue(context, MemberNames.LongAttribute.toString());
	}

	/**
	 * Set value of LongAttribute
	 * @param longattribute
	 */
	public final void setLongAttribute(java.lang.Long longattribute)
	{
		setLongAttribute(getContext(), longattribute);
	}

	/**
	 * Set value of LongAttribute
	 * @param context
	 * @param longattribute
	 */
	public final void setLongAttribute(com.mendix.systemwideinterfaces.core.IContext context, java.lang.Long longattribute)
	{
		getMendixObject().setValue(context, MemberNames.LongAttribute.toString(), longattribute);
	}

	/**
	 * @return value of StringAttribute
	 */
	public final java.lang.String getStringAttribute()
	{
		return getStringAttribute(getContext());
	}

	/**
	 * @param context
	 * @return value of StringAttribute
	 */
	public final java.lang.String getStringAttribute(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.lang.String) getMendixObject().getValue(context, MemberNames.StringAttribute.toString());
	}

	/**
	 * Set value of StringAttribute
	 * @param stringattribute
	 */
	public final void setStringAttribute(java.lang.String stringattribute)
	{
		setStringAttribute(getContext(), stringattribute);
	}

	/**
	 * Set value of StringAttribute
	 * @param context
	 * @param stringattribute
	 */
	public final void setStringAttribute(com.mendix.systemwideinterfaces.core.IContext context, java.lang.String stringattribute)
	{
		getMendixObject().setValue(context, MemberNames.StringAttribute.toString(), stringattribute);
	}

	/**
	 * @return value of Result
	 */
	public final java.lang.String getResult()
	{
		return getResult(getContext());
	}

	/**
	 * @param context
	 * @return value of Result
	 */
	public final java.lang.String getResult(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.lang.String) getMendixObject().getValue(context, MemberNames.Result.toString());
	}

	/**
	 * Set value of Result
	 * @param result
	 */
	public final void setResult(java.lang.String result)
	{
		setResult(getContext(), result);
	}

	/**
	 * Set value of Result
	 * @param context
	 * @param result
	 */
	public final void setResult(com.mendix.systemwideinterfaces.core.IContext context, java.lang.String result)
	{
		getMendixObject().setValue(context, MemberNames.Result.toString(), result);
	}

	/**
	 * @return the IMendixObject instance of this proxy for use in the Core interface.
	 */
	public final com.mendix.systemwideinterfaces.core.IMendixObject getMendixObject()
	{
		return testPatternMendixObject;
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
			final mxmodelreflection.proxies.TestPattern that = (mxmodelreflection.proxies.TestPattern) obj;
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
		return "MxModelReflection.TestPattern";
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
