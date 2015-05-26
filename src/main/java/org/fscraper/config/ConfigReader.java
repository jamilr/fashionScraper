package org.fscraper.config;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * @author Jamil Rzayev March, 2015
 */

@Component("ConfigReader")
public class ConfigReader {

    private static final String CONFIG_PROPERTIES = "/config.properties";
    private static final String CONFIGURATION_FILE_NAME = "configFileName";
    private static final String LOG4J_PROPERTIES = "logFileName";

    private static SettingsFactory INSTANCE;

    private static Properties properties;

    private static Logger logger = Logger.getLogger(ConfigReader.class.getName());

    public ConfigReader(){

    }

    @PostConstruct
    public void initReader()
            throws Exception {

        tryLoadingProperties();

        loadLog4jConfig();

        INSTANCE = initSettingsFactory();
    }

    private SettingsFactory initSettingsFactory() throws Exception {

        URL configFilePath = findConfigFilePath(properties);
        return getSettings(configFilePath);
    }

    private void tryLoadingProperties() {

        try {

            properties = getConfigProperties();
        } catch (Exception e) {

            StringBuilder errorBuilder = new StringBuilder();

            errorBuilder.append("Exception on loading properties from configuration file - ")
                    .append(System.getProperty("line.separator"));

            errorBuilder.append(e.getMessage());

            logger.info(errorBuilder.toString());
        }
    }

    public SettingsFactory getSettingsFactory() {
        return INSTANCE;
    }

    private Properties getConfigProperties() throws IOException {

        Properties prop = new Properties();

        URL path = ConfigReader.class.getResource(CONFIG_PROPERTIES);
        InputStream input = new FileInputStream(path.getPath());

        prop.load(input);

        return prop;
    }

    private URL findConfigFilePath(Properties prop) {

        if (prop.isEmpty())
            return null;

        String fileName = prop.getProperty(CONFIGURATION_FILE_NAME);
        return ConfigReader.class.getResource(fileName);
    }

    private static SettingsFactory getSettings(URL configFilePath)
            throws Exception {

        if (configFilePath == null)
            throw  new Exception("Configuration file path is not valid or empty");

        SettingsFactory settingsFactory = new SettingsFactory(configFilePath);
        settingsFactory.build();

        return settingsFactory;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        ConfigReader.properties = properties;
    }

    private void loadLog4jConfig() throws Exception {

        URL logResource = ConfigReader.class.getResource(properties.getProperty(LOG4J_PROPERTIES));

        PropertyConfigurator.configure(logResource);
    }
}
