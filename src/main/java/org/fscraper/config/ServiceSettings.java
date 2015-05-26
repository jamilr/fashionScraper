package org.fscraper.config;

/**
 * @author Jamil Rzayev March, 2015
 * */
public final class ServiceSettings {

    private DataSourceSettings dataSourceSettings;

    public ServiceSettings() {

    }

    public DataSourceSettings getDataSourceSettings() {
        return dataSourceSettings;
    }

    public void setDataSourceSettings(DataSourceSettings dataSourceSettings) {
        this.dataSourceSettings = dataSourceSettings;
    }
}
