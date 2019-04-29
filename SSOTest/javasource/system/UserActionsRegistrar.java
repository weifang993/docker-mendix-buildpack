package system;

import com.mendix.core.actionmanagement.IActionRegistrator;

public class UserActionsRegistrar
{
  public void registerActions(IActionRegistrator registrator)
  {
    registrator.bundleComponentLoaded();
    registrator.registerUserAction(mxmodelreflection.actions.ReplaceToken.class);
    registrator.registerUserAction(mxmodelreflection.actions.SyncObjects.class);
    registrator.registerUserAction(mxmodelreflection.actions.TestThePattern.class);
    registrator.registerUserAction(mxmodelreflection.actions.ValidateTokensInMessage.class);
    registrator.registerUserAction(saml20.actions.CreateSSOMetadataFile.class);
    registrator.registerUserAction(saml20.actions.DelegatedAuthentication.class);
    registrator.registerUserAction(saml20.actions.GetApplicationRootURL.class);
    registrator.registerUserAction(saml20.actions.PrepareAuthnContext.class);
    registrator.registerUserAction(saml20.actions.PreValidateIdPFile.class);
    registrator.registerUserAction(saml20.actions.ReEvaluateImportedData.class);
    registrator.registerUserAction(saml20.actions.ReloadConfiguration.class);
    registrator.registerUserAction(saml20.actions.StartSSO.class);
    registrator.registerUserAction(saml20.actions.StoreURLToFileDocument.class);
    registrator.registerUserAction(system.actions.VerifyPassword.class);
  }
}
