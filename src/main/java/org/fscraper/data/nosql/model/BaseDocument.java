package org.fscraper.data.nosql.model;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.apache.log4j.Logger;

/**
 * @author Jamil Rzayev March 2015
 */

public abstract class BaseDocument {

    private static final Logger logger = Logger.getLogger(BaseDocument.class.getName());

    protected DBObject document;

    public BaseDocument() {
        this.document = new BasicDBObject();
    }

    public DBObject getDocument(){
        return this.document;
    }

    public void setValue(String attribute, Object value) {
        this.document.put(attribute, value);
    }

    public Object getValue(String attribute) {
        return (document.containsKey(attribute)) ? this.document.get(attribute): null;
    }

    public boolean isEmpty() {
        return (document == null || document.keySet().isEmpty()) ? true: false;
    }
}
