package com.fluffypages.server.admin;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.UserService;

public class StandardResponses {
  private static Logger log = Logger.getLogger(StandardResponses.class.getSimpleName());
  private static String ERROR_403 = "Should be in admin role";

  public static void send403ifNotAdmin(UserService userService, HttpServletResponse resp) {
    resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
    writeHtmlResponse(resp, ERROR_403);
  }

  public static void writeHtmlResponse(HttpServletResponse resp, String message) {
    try {
      resp.getWriter().println("<html><body><p>" + message + "</p></body></html>");
    } catch (IOException e) {
      log.log(Level.SEVERE, e.getMessage());
    }
  }
}
