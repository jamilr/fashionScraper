package org.fscraper.data.nosql.bo;

import org.fscraper.data.nosql.dao.ItemDAO;
import org.fscraper.data.nosql.model.BaseDocument;
import org.fscraper.data.nosql.model.ItemDocument;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jamil Rzayev March 2015
 */

@Service("ItemBO")
public class ItemBOImpl implements ItemBO {

    private static final Logger logger = Logger.getLogger(ItemBOImpl.class.getName());

    @Autowired
    @Qualifier("ItemDAO")
    private ItemDAO itemDAO;

    public ItemBOImpl(){

    }


    @Override
    public void save(BaseDocument document) {
        this.itemDAO.save(document.getDocument());
    }

    @Override
    public List<ItemDocument> get(String shop) {
        return itemDAO.getItemDocumentList(shop);
    }
}
