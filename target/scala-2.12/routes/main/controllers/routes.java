
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/kpyancey/Projects/Library-Demo-for-OOD/conf/routes
// @DATE:Mon Jan 08 09:29:48 KST 2018

package controllers;

import router.RoutesPrefix;

public class routes {
  
  public static final controllers.ReverseLibraryAppController LibraryAppController = new controllers.ReverseLibraryAppController(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseAssets Assets = new controllers.ReverseAssets(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final controllers.javascript.ReverseLibraryAppController LibraryAppController = new controllers.javascript.ReverseLibraryAppController(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseAssets Assets = new controllers.javascript.ReverseAssets(RoutesPrefix.byNamePrefix());
  }

}
