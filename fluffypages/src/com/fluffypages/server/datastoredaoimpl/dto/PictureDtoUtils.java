package com.fluffypages.server.datastoredaoimpl.dto;

import java.util.ArrayList;
import java.util.List;

import com.fluffypages.server.datastoredaoimpl.dto.Picture.PictureBuilder;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.inject.Singleton;

@Singleton
public class PictureDtoUtils {

  public Entity createPictureEntity(Picture picture) {
    Entity nextEntity = new Entity(KeyFactory.createKey(Picture.class.getSimpleName(), picture.getUrl()));
    nextEntity.setProperty(PictureEntityFields.URL, picture.getUrl());
    nextEntity.setProperty(PictureEntityFields.RATING, picture.getRating());
    return nextEntity;
  }

  public Picture createPictureFromEntity(Entity entity) {
    PictureBuilder pb = new PictureBuilder((String) entity.getProperty(PictureEntityFields.URL));
    Long rating = (Long) entity.getProperty(PictureEntityFields.RATING);
    if (rating != null) {
      pb.rating(rating);
    }
    return pb.build();
  }

  public Entity createPictureEntity(String url) {
    Entity entity = new Entity(KeyFactory.createKey(Picture.class.getSimpleName(), url));
    entity.setProperty(PictureEntityFields.URL, url);
    return entity;
  }

  public List<Picture> createPicturesFromEntityList(List<Entity> entityList) {
    List<Picture> picturesList = new ArrayList<>();
    for (Entity entity : entityList) {
      picturesList.add(createPictureFromEntity(entity));
    }
    return picturesList;
  }

}
