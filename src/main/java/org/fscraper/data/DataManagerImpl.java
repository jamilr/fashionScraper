package org.fscraper.data;

import org.fscraper.data.nosql.bo.MongoDataImportServiceImpl;
import org.fscraper.data.sql.bo.DataImportServiceImpl;
import org.fscraper.data.sql.model.StoreCategory;
import org.fscraper.data.view.ProductView;
import org.fscraper.entry.DataSourceType;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by jr on 4/4/2015.
 */

@Service("DataManager")
public class DataManagerImpl implements DataManager {

    private static Logger logger = Logger.getLogger(DataManagerImpl.class.getName());

    private StoreCategory storeCategory;

    private DataSourceType dataSourceType;

    @Autowired
    @Qualifier("MongoDataImportService")
    private DataImportService mongoDataImportService;

    @Autowired
    @Qualifier("SQLiteDataImportService")
    private DataImportService sqLiteDataImportService;

    public DataManagerImpl(){

    }

    @Override
    public void setStoreCategory(StoreCategory storeCat) {

        this.storeCategory = storeCat;

        ((DataImportServiceImpl)sqLiteDataImportService).setConfig(storeCategory);
        ((MongoDataImportServiceImpl)mongoDataImportService).setConfig(storeCategory);
    }

    @Override
    public void setDataSourceType(DataSourceType dataSourceType) {

        this.dataSourceType = dataSourceType;
    }

    @Override
    public void save(ProductView productView) {

        switch (dataSourceType) {
            case SQL: {
                sqLiteDataImportService.save(productView);
            }
            break;
            case MONGO: {
                mongoDataImportService.save(productView);
            }
            break;
            case SQLANDMONGO: {
                sqLiteDataImportService.save(productView);
                mongoDataImportService.save(productView);
            }
            break;
            default:
        }
    }
}
