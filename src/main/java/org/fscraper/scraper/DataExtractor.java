package org.fscraper.scraper;

import org.fscraper.data.view.ProductView;
import org.jsoup.nodes.Document;

/**
 * @author Jamil Rzayev March 2015
 */

public interface DataExtractor {

    ProductView extract(Document document);
    void setToScrapeSizes(Boolean toScrape);

}
