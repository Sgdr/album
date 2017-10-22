package album.web;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

/**
 * Configure DispatcherServlet
 * @author sgdr
 */
public class ApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {


    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{RootConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{AlbumConfiguration.class};
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        // set MultipartConfig to enable request with Content-Type = multipart/form-data
        // (or if use Tomcat set in context.xml: <Context allowCasualMultipartParsing="true">)
        registration.setMultipartConfig(new MultipartConfigElement(""));
    }
}
