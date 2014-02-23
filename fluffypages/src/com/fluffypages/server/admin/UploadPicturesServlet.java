package com.fluffypages.server.admin;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.UserService;
import com.google.appengine.labs.repackaged.com.google.common.base.Preconditions;
import com.google.appengine.labs.repackaged.com.google.common.io.CharStreams;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class UploadPicturesServlet extends HttpServlet {
  private static final long serialVersionUID = -7166912900741198437L;
  private static final Logger log = Logger.getLogger(UploadPicturesServlet.class.getSimpleName());

  private final PicturesManagementService picService;
  private final UserService userService;

  @Inject
  UploadPicturesServlet(PicturesManagementService picService, UserService userService) {
    this.picService = Preconditions.checkNotNull(picService);
    this.userService = Preconditions.checkNotNull(userService);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    StandardResponses.send403ifNotAdmin(userService, resp);

    ServletInputStream in = req.getInputStream();
    String data = CharStreams.toString(new InputStreamReader(in));

    List<String> picsList = Arrays.asList(data.split(","));
    picService.loadPicturesToDatastore(picsList);

    StandardResponses.writeHtmlResponse(resp, "Saved " + picsList.size() + " pictures");
  }
}
