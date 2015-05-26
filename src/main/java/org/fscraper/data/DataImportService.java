package org.fscraper.data;

import org.fscraper.data.view.ProductView;

/**
 * Created by jr on 3/22/2015.
 */
public interface DataImportService {

    DataImportServiceStatus save(ProductView productView);
}
