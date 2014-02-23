<%@ page import="com.google.inject.Injector"%>
<%@ page import="com.google.inject.Guice"%>
<%@ page import="com.fluffypages.server.service.admin.PicturesManagementService"%>

<!DOCTYPE HTML>
<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>Fluffy Pages!</title>
  </head>

  <body>
<% 
    Injector inj = (Injector) pageContext.getServletContext().getAttribute(Injector.class.getName());
    PicturesManagementService picturesService = inj.getInstance(PicturesManagementService.class);
    List<Picture> pics = picturesService.getAll();
%>
  </body>
</html>
