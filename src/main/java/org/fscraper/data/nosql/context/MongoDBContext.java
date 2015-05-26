package org.fscraper.data.nosql.context;

import org.fscraper.config.ConfigReader;
import org.fscraper.config.DataSourceSettings;
import com.mongodb.MongoClient;


import com.mongodb.ServerAddress;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import java.net.UnknownHostException;

/**
 * @author Jamil Rzayev March, 2015
 */

@Service("MongoDBContext")
@Scope(value = "singleton")
public class MongoDBContext implements DbContext {

    private static Logger logger = Logger.getLogger(MongoDBContext.class.getName());

    private MongoClient mongoClient;

    private String connectionUrl;
    private Integer connectionPort;

    @Autowired
    @Qualifier("ConfigReader")
    private ConfigReader configReader;

    public MongoDBContext() {
    }

    @Override
    public Object getClient() {
        return this.mongoClient;
    }

    @PostConstruct
    public void init() {

        logger.info("MongoDBContext is to read configuration settings and establish connection to the remote database instance");

        tryAcquiringConfig();
        tryConnect();
    }

    private void tryAcquiringConfig() {

        DataSourceSettings dataSourceSettings = configReader.getSettingsFactory().getServiceSettings().getDataSourceSettings();

        connectionUrl = dataSourceSettings.getDbUrl();
        connectionPort = dataSourceSettings.getDbPort();

        logger.info("MongoDBContext has completed reading configuration settings");
    }

    @PreDestroy
    public void cleanUp() throws Exception {

        tryClosing();
        logger.info("MongoClient has been successfully destroyed");
    }

    private void tryClosing(){

        try {
            mongoClient.close();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
    }

    private void tryConnect() {

        ServerAddress serverAddress = createServerAddress();

        if (serverAddress == null)
            return;

        this.mongoClient = new MongoClient(createServerAddress());
    }

    private ServerAddress createServerAddress() {

        ServerAddress serverAddress = null;
        try {
            serverAddress = new ServerAddress(connectionUrl, connectionPort);
        } catch (UnknownHostException hostEx) {

            logger.error(hostEx.getMessage(), hostEx);
        }

        return serverAddress;
    }
}
