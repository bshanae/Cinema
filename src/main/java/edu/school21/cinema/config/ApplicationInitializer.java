package edu.school21.cinema.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

public class ApplicationInitializer implements WebApplicationInitializer {
    public void onStartup(ServletContext servletContext) {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(WebMVCConfig.class);
        context.setServletContext(servletContext);

        DispatcherServlet dispatcherServlet = new DispatcherServlet(context);

        ServletRegistration.Dynamic myCustomDispatcherServlet = servletContext.addServlet("dispatcher", dispatcherServlet);
        myCustomDispatcherServlet.setLoadOnStartup(1);
        myCustomDispatcherServlet.addMapping("/");
    }
}
