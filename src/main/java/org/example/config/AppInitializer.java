package org.example.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;  // No additional root configurations needed (e.g., database setup, security)
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        // Return the configuration class that configures Spring MVC
        return new Class[] { WebConfig.class };
    }

    @Override
    protected String [] getServletMappings() {
        // Map all incoming requests to the DispatcherServlet
        return new String[] { "/" };  // The "/" URL pattern means all URLs are handled by Spring
    }
}
