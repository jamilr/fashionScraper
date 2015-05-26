package org.fscraper.data.nosql.bo;

import org.fscraper.data.nosql.model.BaseDocument;
import org.fscraper.data.nosql.model.ItemDocument;

import java.util.List;

/**
 * @author Jamil Rzayev March 2015
 */
public interface ItemBO {

    void save(BaseDocument document);

    List<ItemDocument> get(String shop);
}
