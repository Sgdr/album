package album.web;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Components configuration
 * @author sgdr
 */
@Configuration
@ComponentScan(basePackages = {"album"},
        excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, value=EnableWebMvc.class)
})
public class RootConfig {

}
