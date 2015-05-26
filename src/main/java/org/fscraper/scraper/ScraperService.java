package org.fscraper.scraper;

import org.fscraper.data.sql.model.StoreCategory;
import org.fscraper.data.view.ProductView;

import java.util.Set;

/**
 * @author Jamil Rzayev March 2015
 */
public interface ScraperService {

    boolean hasNext();

    ProductView next();

    String getDataExtractorName();

    void setScraperConfig(ScraperConfig scraperConfig);
    void setStoreCategory(StoreCategory storeCategory);

    Set<String> getItemURL(Integer count);
    Set<String> getAllItemURL();
}
