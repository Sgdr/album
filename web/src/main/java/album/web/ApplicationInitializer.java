package album.web;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

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
        return  new Class<?>[]{AlbumConfiguration.class};
    }
}
