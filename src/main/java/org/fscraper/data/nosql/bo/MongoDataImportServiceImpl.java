package org.fscraper.data.nosql.bo;

import org.fscraper.data.AbstractDataImportService;
import org.fscraper.data.DataImportService;
import org.fscraper.data.DataImportServiceStatus;
import org.fscraper.data.nosql.model.ItemDocument;
import org.fscraper.data.sql.model.StoreCategory;
import org.fscraper.data.view.ProductView;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by jr on 3/22/2015.
 */

@Service("MongoDataImportService")
public class MongoDataImportServiceImpl extends AbstractDataImportService
        implements DataImportService {

    private static Logger logger = Logger.getLogger(MongoDataImportServiceImpl.class.getName());

    @Autowired
    @Qualifier("ItemBO")
    private ItemBO itemBO;

    @Autowired
    private ViewConverter viewConverter;

    private StoreCategory storeCategory;

    public MongoDataImportServiceImpl() {

    }

    public void setConfig(StoreCategory storeCategory){
        this.storeCategory = storeCategory;
    }

    @Override
    public DataImportServiceStatus save(ProductView productView) {

        DataImportServiceStatus serviceStatus = new DataImportServiceStatus();

        ItemDocument itemDocument = viewConverter.convert(productView);

        itemDocument.setValue(ItemDocument.STORE,        storeCategory.getStore().getLongName());
        itemDocument.setValue(ItemDocument.CATEGORY,    storeCategory.getCategory().getName());

        itemBO.save(itemDocument);

        serviceStatus.setStatus(Boolean.TRUE);

        logger.info("SKU ".concat(productView.getSku()));

        return serviceStatus;
    }
}
