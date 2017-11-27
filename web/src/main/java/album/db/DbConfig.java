package album.db;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.text.MessageFormat;

/**
 * Configuring of DB connection
 * @author sgdr
 */
@Configuration
@PropertySource("classpath:app.properties")
public class DbConfig {

    @Value("${db_url}")
    private String dbUrl;

    @Value("${db_name}")
    private String dbName;

    @Value("${db_schema}")
    private String dbSchema;

    @Value("${db_user}")
    private String dbUser;

    @Value("${db_user_password}")
    private String dbUserPassword;

    @Bean
    public BasicDataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        String url = MessageFormat.format("jdbc:postgresql://{0}/{1}?currentSchema=\"{2}\"",
                dbUrl, dbName, dbSchema);
        dataSource.setUrl(url);
        dataSource.setUsername(dbUser);
        dataSource.setPassword(dbUserPassword);
        dataSource.setInitialSize(5);
        dataSource.setMaxActive(10);
        return dataSource;
    }
}
