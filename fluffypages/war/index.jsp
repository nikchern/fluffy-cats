<%@ page import="com.google.inject.Injector"%>
<%@ page import="com.google.inject.Guice"%>
<%@ page import="java.util.List"%>
<%@ page import="com.fluffypages.server.admin.PicturesManagementService"%>
<%@ page import="com.fluffypages.server.datastoredaoimpl.dto.Picture"%>


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
    List<Picture> pics = picturesService.getPicturesRange(0, 10);
    for (Picture picture : pics) {
%>
		  <div>
		    <%= picture.getUrl() %>
		  </div>
<%  } %>
    <div>boo</div>
  </body>
</html>
