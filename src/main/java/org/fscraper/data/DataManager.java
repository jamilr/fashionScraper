package org.fscraper.data;

import org.fscraper.data.sql.model.StoreCategory;
import org.fscraper.data.view.ProductView;
import org.fscraper.entry.DataSourceType;

/**
 * Created by jr on 4/4/2015.
 */
public interface DataManager {

    void setStoreCategory(StoreCategory storeCat);
    void setDataSourceType(DataSourceType dataSourceType);
    void save(ProductView productView);
}
