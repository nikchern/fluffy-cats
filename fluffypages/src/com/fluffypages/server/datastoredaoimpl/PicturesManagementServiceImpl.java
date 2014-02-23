package com.fluffypages.server.datastoredaoimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fluffypages.server.admin.PicturesManagementService;
import com.fluffypages.server.datastoredaoimpl.dto.PictureDtoUtils;
import com.fluffypages.server.datastoredaoimpl.dto.Picture;
import com.fluffypages.server.datastoredaoimpl.dto.PictureEntityFields;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.appengine.labs.repackaged.com.google.common.base.Preconditions;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class PicturesManagementServiceImpl implements PicturesManagementService {

  private static final Logger log = Logger.getLogger(PicturesManagementServiceImpl.class.getSimpleName());
  private final DatastoreService datastore;
  private final PictureDtoUtils dtoUtils;
  
  @Inject
  public PicturesManagementServiceImpl(PictureDtoUtils dtoUtils, DatastoreService datastore) {
    this.datastore = Preconditions.checkNotNull(datastore);
    this.dtoUtils = Preconditions.checkNotNull(dtoUtils);
  }

  public void loadPicturesToDatastore(List<String> urls) {
    log.log(Level.INFO, "Creating " + urls.size() + " Pictures");
    List<Entity> entitiesToSave = new ArrayList<Entity>(urls.size());
    for (String url : urls) {
      Entity nextEntity = dtoUtils.createPictureEntity(url);
      entitiesToSave.add(nextEntity);
    }
    datastore.put(entitiesToSave);
  }

  @Override
  public List<Picture> getPicturesRange(int offset, int limit) {
    Query q = new Query(Picture.class.getSimpleName());//.addSort(PictureEntityFields.RATING, SortDirection.DESCENDING);
    log.log(Level.INFO, "Loading pictures range with query:" + q);
    PreparedQuery pq = datastore.prepare(q);
    List<Entity> entityList = pq.asList(FetchOptions.Builder.withLimit(5));
    log.log(Level.INFO, "Loaded " + entityList.size() +  " pictures");
    return dtoUtils.createPicturesFromEntityList(entityList);
  }

}
