package album.web;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
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

}
