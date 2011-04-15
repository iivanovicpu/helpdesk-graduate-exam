package hr.iii.data;

import java.util.Properties;

/**
 * User: iivanovic
 * Date: 29.10.2010.
 * Time: 14:16:08
 */
public class AppConfiguration {
    private Properties properties;

    public String getAppName() {
        return properties.getProperty("name");
    }

    public boolean isDevelopmentMode() {
        return properties.getProperty("developmentMode").equals("true");
    }

    public String getVersion() {
        return properties.getProperty("version");
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
