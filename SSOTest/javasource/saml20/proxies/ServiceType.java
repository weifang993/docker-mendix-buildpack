// This file was generated by Mendix Modeler.
//
// WARNING: Code you write here will be lost the next time you deploy the project.

package saml20.proxies;

public enum ServiceType
{
	SingleSignOnService(new java.lang.String[][] { new java.lang.String[] { "en_US", "SingleSignOnService" }, new java.lang.String[] { "nl_NL", "SingleSignOnService" }, new java.lang.String[] { "en_GB", "SingleSignOnService" }, new java.lang.String[] { "en_ZA", "SingleSignOnService" }, new java.lang.String[] { "ja_JP", "SingleSignOnService" } }),
	AssertionIDRequestService(new java.lang.String[][] { new java.lang.String[] { "en_US", "AssertionIDRequestService" }, new java.lang.String[] { "nl_NL", "AssertionIDRequestService" }, new java.lang.String[] { "en_GB", "AssertionIDRequestService" }, new java.lang.String[] { "en_ZA", "AssertionIDRequestService" }, new java.lang.String[] { "ja_JP", "AssertionIDRequestService" } }),
	ManageNameIDService(new java.lang.String[][] { new java.lang.String[] { "en_US", "ManageNameIDService" }, new java.lang.String[] { "nl_NL", "ManageNameIDService" }, new java.lang.String[] { "en_GB", "ManageNameIDService" }, new java.lang.String[] { "en_ZA", "ManageNameIDService" }, new java.lang.String[] { "ja_JP", "ManageNameIDService" } }),
	NameIDMappingService(new java.lang.String[][] { new java.lang.String[] { "en_US", "NameIDMappingService" }, new java.lang.String[] { "nl_NL", "NameIDMappingService" }, new java.lang.String[] { "en_GB", "NameIDMappingService" }, new java.lang.String[] { "en_ZA", "NameIDMappingService" }, new java.lang.String[] { "ja_JP", "NameIDMappingService" } }),
	SingleLogoutService(new java.lang.String[][] { new java.lang.String[] { "en_US", "SingleLogoutService" }, new java.lang.String[] { "nl_NL", "SingleLogoutService" }, new java.lang.String[] { "en_GB", "SingleLogoutService" }, new java.lang.String[] { "en_ZA", "SingleLogoutService" }, new java.lang.String[] { "ja_JP", "SingleLogoutService" } }),
	AssertionConsumerService(new java.lang.String[][] { new java.lang.String[] { "en_US", "AssertionConsumerService" }, new java.lang.String[] { "nl_NL", "AssertionConsumerService" }, new java.lang.String[] { "en_GB", "AssertionConsumerService" }, new java.lang.String[] { "en_ZA", "AssertionConsumerService" }, new java.lang.String[] { "ja_JP", "AssertionConsumerService" } }),
	ArtifactResolutionService(new java.lang.String[][] { new java.lang.String[] { "en_US", "ArtifactResolutionService" }, new java.lang.String[] { "nl_NL", "ArtifactResolutionService" }, new java.lang.String[] { "en_GB", "ArtifactResolutionService" }, new java.lang.String[] { "en_ZA", "ArtifactResolutionService" }, new java.lang.String[] { "ja_JP", "ArtifactResolutionService" } }),
	AttributeService(new java.lang.String[][] { new java.lang.String[] { "en_US", "AttributeService" }, new java.lang.String[] { "nl_NL", "Attribute Service" }, new java.lang.String[] { "en_GB", "AttributeService" }, new java.lang.String[] { "en_ZA", "AttributeService" }, new java.lang.String[] { "ja_JP", "AttributeService" } });

	private java.util.Map<java.lang.String, java.lang.String> captions;

	private ServiceType(java.lang.String[][] captionStrings)
	{
		this.captions = new java.util.HashMap<java.lang.String, java.lang.String>();
		for (java.lang.String[] captionString : captionStrings)
			captions.put(captionString[0], captionString[1]);
	}

	public java.lang.String getCaption(java.lang.String languageCode)
	{
		if (captions.containsKey(languageCode))
			return captions.get(languageCode);
		return captions.get("en_US");
	}

	public java.lang.String getCaption()
	{
		return captions.get("en_US");
	}
}
