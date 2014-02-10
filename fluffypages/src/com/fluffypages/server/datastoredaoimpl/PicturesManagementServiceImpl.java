package com.fluffypages.server.datastoredaoimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fluffypages.server.admin.PicturesManagementService;
import com.fluffypages.server.datastoredaoimpl.dto.Picture;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.inject.Singleton;

@Singleton
public class PicturesManagementServiceImpl implements PicturesManagementService {

  private static final Logger log = Logger
      .getLogger(PicturesManagementServiceImpl.class.getSimpleName());

  public void loadPicturesToDatastore(List<String> urls) {
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

    log.log(Level.INFO, "Creating " + urls.size() + " Pictures");
    List<Entity> entitiesToSave = new ArrayList<Entity>(urls.size());
    for (String url : urls) {
      Entity nextEntity = new Entity(KeyFactory.createKey(
          Picture.class.getSimpleName(), url));
      nextEntity.setProperty(Picture.URL, url);
      nextEntity.setProperty(Picture.VIEWS, 0L);
      entitiesToSave.add(nextEntity);
    }

    datastore.put(entitiesToSave);
  }

}
