package com.fluffypages.server.admin;

import com.fluffypages.server.datastoredaoimpl.PicturesManagementServiceImpl;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.inject.AbstractModule;
import com.google.inject.Module;
import com.google.inject.Provides;
import com.google.inject.Singleton;

public class AdminModule extends AbstractModule implements Module {

  @Override
  protected void configure() {
    bind(PicturesManagementService.class).to(
        PicturesManagementServiceImpl.class).in(Singleton.class);
  }

  @Provides
  public DatastoreService getDatastoreService() {
    return DatastoreServiceFactory.getDatastoreService();
  }
  
  @Provides
  public UserService getUserService() {
    return UserServiceFactory.getUserService();
  }
  
}
