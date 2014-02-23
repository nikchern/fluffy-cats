package com.fluffypages.server.admin;

import java.util.List;

import com.fluffypages.server.datastoredaoimpl.dto.Picture;

public interface PicturesManagementService {
  void loadPicturesToDatastore(List<String> urls);
  List<Picture> getPicturesRange(int offset, int limit);
}
