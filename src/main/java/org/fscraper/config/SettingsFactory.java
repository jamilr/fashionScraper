package org.fscraper.config;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.log4j.Logger;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Jamil Rzayev March 2015
 */

public class SettingsFactory {

    private URL configPath;
    private ServiceSettings settings;
    private XMLConfiguration xmlConfig;

    private final Map<String, Object> settingMap;

    private static Logger logger = Logger.getLogger(SettingsFactory.class.getName());

    public SettingsFactory(URL configPath) {

        this.settings = new ServiceSettings();
        this.settingMap = new HashMap<>();
        this.configPath = configPath;

        init();
    }

    private void init(){

        try {

            String configFullPath = SystemHelper.getFilePath(configPath);
            this.xmlConfig = new XMLConfiguration(configFullPath);

        } catch (ConfigurationException e) {

            StringBuilder errorBuilder = new StringBuilder();
            errorBuilder.append("SettingsFactory - Error on initialization ");
            errorBuilder.append(System.getProperty("line.separator"));
            errorBuilder.append(e.getStackTrace());
            logger.error(errorBuilder.toString());
        }
    }

    public Map<String, Object> getSettingMap() {

        return this.settingMap;
    }

    public ServiceSettings getServiceSettings() {

        return this.settings;
    }

    public void build() {

        String dbConnectionUrl = ConfigHelper.getString(xmlConfig.getString(ServiceKey.DB_URL), "localhost");
        Integer dbConnectionPort = ConfigHelper.getInt(xmlConfig.getString(ServiceKey.DB_PORT), 27000);

        String dbName = ConfigHelper.getString(xmlConfig.getString(ServiceKey.DB_NAME), "MaiaMallDB");
        String dbUser = ConfigHelper.getString(xmlConfig.getString(ServiceKey.DB_USER), "sa");
        String dbPassword = ConfigHelper.getString(xmlConfig.getString(ServiceKey.DB_PASSWD), "sa");

        DataSourceSettings dataSourceSettings = new DataSourceSettings(dbConnectionUrl, dbConnectionPort, dbName, dbUser, dbPassword);

        settings.setDataSourceSettings(dataSourceSettings);
        settingMap.put(ServiceKey.DB_SETTINGS, dataSourceSettings);
    }
}
