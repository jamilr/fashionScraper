package org.fscraper.config;

/**
 * @author Jamil Rzayev March, 2015
 */

public class DataSourceSettings {

    private String dbUrl;

    private Integer dbPort;

    private String dbName;

    private String dbUser;

    private String dbPassword;

    public DataSourceSettings(){

    }

    public DataSourceSettings(String connectionUrl,
                              Integer connectionPort,
                              String dbName,
                              String dbUserName,
                              String dbPassword){

        this.dbUrl = connectionUrl;
        this.dbPort = connectionPort;
        this.dbName = dbName;
        this.dbUser  = dbUserName;
        this.dbPassword = dbPassword;

    }

    public String getDbUrl() {
        return dbUrl;
    }

    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public Integer getDbPort() {
        return dbPort;
    }

    public void setDbPort(Integer dbPort) {
        this.dbPort = dbPort;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getDbUser() {
        return dbUser;
    }

    public void setDbUser(String dbUser) {
        this.dbUser = dbUser;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }
}
