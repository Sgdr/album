package album.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Configuration to enable Spring MVC
 * @author sgdr
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "album.services")
public class AlbumConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        // let Spring to not handle requests for static resource
        configurer.enable();
    }

    @Bean
    public MultipartResolver multipartResolver() {
        // set a resolver to proceed multipart request. This implementation for the Servlet 3.0+ Part API
        return new StandardServletMultipartResolver();
    }
}
