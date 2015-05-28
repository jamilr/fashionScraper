package org.fscraper.data.cassandra;/**
 * Created by jr on 5/25/2015.
 */

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.Session;
import org.fscraper.data.nosql.context.DbContext;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author Jamil Rzayev
 */

@Service("CassandraDBContext")
@Scope(value = "singleton")
public class CassandraDBContext implements DbContext {

    private static Logger logger = Logger.getLogger(CassandraDBContext.class.getName());

    private Cluster cluster;

    private Session session;


    public void init() {

        logger.info("MongoDBContext is to read configuration settings and establish connection to the remote database instance");

        tryConnect();
    }

    private void tryConnect() {

        cluster = Cluster.builder()
                .addContactPoint("localhost")
                .build();

        session = cluster.connect("inventory");

        Metadata metadata = cluster.getMetadata();

        logger.info("Connected to cluster: ".concat(metadata.getClusterName()));
    }

    @Override
    public Object getClient() {
        return this.session;
    }
}
