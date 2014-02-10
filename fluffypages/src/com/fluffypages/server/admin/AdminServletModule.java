package com.fluffypages.server.admin;

import com.google.inject.servlet.ServletModule;

public class AdminServletModule extends ServletModule {
  @Override protected void configureServlets() {
    serve("/uploadPictures").with(UploadPicturesServlet.class);

  }
}
