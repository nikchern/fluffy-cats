package com.fluffypages.server.admin;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

public class ServletConfig extends GuiceServletContextListener {

  @Override
  protected Injector getInjector() {
    return Guice.createInjector(
        new AdminServletModule(),
        new AdminModule());
  }
  
  @Override
  public void contextInitialized(ServletContextEvent servletContextEvent)
  {
      // No call to super as it also calls getInjector()
      ServletContext sc = servletContextEvent.getServletContext();
      sc.setAttribute(Injector.class.getName(), getInjector());
  }

  @Override
  public void contextDestroyed(ServletContextEvent servletContextEvent)
  {
      ServletContext sc = servletContextEvent.getServletContext();
      sc.removeAttribute(Injector.class.getName());
      super.contextDestroyed(servletContextEvent);
  }
}