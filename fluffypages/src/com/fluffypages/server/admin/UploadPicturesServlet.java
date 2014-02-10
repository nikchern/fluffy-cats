package com.fluffypages.server.admin;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.labs.repackaged.com.google.common.base.Preconditions;
import com.google.inject.Inject;

public class UploadPicturesServlet extends HttpServlet {
  private static final long serialVersionUID = -7166912900741198437L;
  private final PicturesManagementService picService;
  
  @Inject
  UploadPicturesServlet(PicturesManagementService picService) {
    this.picService = Preconditions.checkNotNull(picService);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String picsToImport = Preconditions.checkNotNull(req.getParameter("urls"));
    List<String> picsList = Arrays.asList(picsToImport.split(","));
    picService.loadPicturesToDatastore(picsList);
    
    resp.getWriter().write("Saved " + picsList.size() + " pictures");
  }
}
