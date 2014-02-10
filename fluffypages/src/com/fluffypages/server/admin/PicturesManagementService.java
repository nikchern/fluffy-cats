package com.fluffypages.server.admin;

import java.util.List;

public interface PicturesManagementService {
  void loadPicturesToDatastore(List<String> urls);
}
