// This file was generated by Mendix Modeler.
//
// WARNING: Only the following code will be retained when actions are regenerated:
// - the import list
// - the code between BEGIN USER CODE and END USER CODE
// - the code between BEGIN EXTRA CODE and END EXTRA CODE
// Other code you write will be lost the next time you deploy the project.
// Special characters, e.g., é, ö, à, etc. are supported in comments.

package objecthandling.actions;

import com.mendix.systemwideinterfaces.core.IMendixObject;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.webui.CustomJavaAction;

/**
 * Returns the actual type of an Entity. Useful as alternative way to split upon inheritance, or as input of other functions in this module.
 */
public class getTypeAsString extends CustomJavaAction<java.lang.String>
{
	private IMendixObject instance;

	public getTypeAsString(IContext context, IMendixObject instance)
	{
		super(context);
		this.instance = instance;
	}

	@java.lang.Override
	public java.lang.String executeAction() throws Exception
	{
		// BEGIN USER CODE
		if (instance == null)
			return "";
		return instance.getType();
		// END USER CODE
	}

	/**
	 * Returns a string representation of this action
	 */
	@java.lang.Override
	public java.lang.String toString()
	{
		return "getTypeAsString";
	}

	// BEGIN EXTRA CODE
	// END EXTRA CODE
}
