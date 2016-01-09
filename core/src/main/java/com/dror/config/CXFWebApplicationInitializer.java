package com.dror.config;

import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.util.EnumSet;

/**
 * User: Dror
 * Date: 1/8/2016
 */
public class CXFWebApplicationInitializer implements WebApplicationInitializer
{
    public void onStartup(ServletContext servletContext) throws ServletException
    {
        // Create the 'root' Spring application context
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(ApplicationConfig.class);

        // Manage the lifecycle of the root application context
        servletContext.addListener(new ContextLoaderListener(applicationContext));

        // Add security filter
        servletContext.addFilter("springSecurityFilterChain", new DelegatingFilterProxy("springSecurityFilterChain"))
                .addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), false, "/*");

        // Register and map the dispatcher servlet
        ServletRegistration.Dynamic dynamicReg =
                servletContext.addServlet("rest-api", CXFServlet.class);
        dynamicReg.setLoadOnStartup(1);
        dynamicReg.addMapping("/api/*");
    }
}
