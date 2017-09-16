package album.web.itest;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author sgdr
 */
public class IntegrationTest {

    @Test
    public void test() throws IOException {
        Properties properties = new Properties();
        try (InputStream is = getClass().getResourceAsStream("/itest.properties")) {
            properties.load(is);
        }
        Assert.assertTrue(true);
    }

}
