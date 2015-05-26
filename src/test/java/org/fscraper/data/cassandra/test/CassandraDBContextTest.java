package org.fscraper.data.cassandra.test;/**
 * Created by jr on 5/25/2015.
 */

import com.datastax.driver.core.Session;
import org.fscraper.data.cassandra.CassandraDBContext;
import org.junit.Test;

/**
 * @author Jamil Rzayev
 */

public class CassandraDBContextTest {


    @Test
    public void get_metadata_on_connection(){

        CassandraDBContext context = new CassandraDBContext();

        context.init();

        Session session = (Session)context.getClient();

    }
}
