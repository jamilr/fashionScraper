package org.fscraper.data.nosql.dao;

import org.fscraper.data.nosql.context.DbContext;
import org.fscraper.data.nosql.model.ItemDocument;
import org.fscraper.helpers.DBParam;
import com.mongodb.*;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jamil Rzayev March 2015
 */

@Service("ItemDAO")
public class ItemDAOImpl implements ItemDAO {

    private static final Logger logger = Logger.getLogger(ItemDAOImpl.class.getName());

    @Autowired
    private DbContext dbContext;

    private DBCollection collection;

    public ItemDAOImpl() {

    }

    @PostConstruct
    public void init(){

        MongoClient mongoClient = (MongoClient)this.dbContext.getClient();
        DB database =  mongoClient.getDB(DBParam.DB_NAME.toString());
        collection = database.getCollection(ItemDocument.DB_COLLECTION);

        logger.info("MongoDB Collection has been initialized : ".concat(collection.getName()));
    }

    @Override
    public void save(DBObject document) {

        collection.save(document);
    }

    @Override
    public List<ItemDocument> getItemDocumentList(String store) {

        List<ItemDocument> itemDocumentList = new ArrayList<>();

        DBObject query = new BasicDBObject().append(ItemDocument.STORE.toString(), store);

        DBCursor cursor = collection.find(query);

        while (cursor.hasNext())
            itemDocumentList.add( new ItemDocument(cursor.next()));

        return itemDocumentList;
    }
}
