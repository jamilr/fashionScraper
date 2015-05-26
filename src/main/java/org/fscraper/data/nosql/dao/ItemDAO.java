package org.fscraper.data.nosql.dao;

import org.fscraper.data.nosql.model.ItemDocument;
import com.mongodb.DBObject;

import java.util.List;

/**
 * @author Jamil Rzayev March 2015
 */

public interface ItemDAO {

    void save(DBObject document);

    List<ItemDocument> getItemDocumentList(String shop);
}
