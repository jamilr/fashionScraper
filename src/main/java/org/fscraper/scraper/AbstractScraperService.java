package org.fscraper.scraper;

import org.fscraper.config.SpringContextProvider;
import org.fscraper.data.sql.model.StoreCategory;
import org.fscraper.data.view.ProductView;
import org.fscraper.helpers.CategoryParam;
import org.fscraper.helpers.ScraperHelper;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;

import java.util.Iterator;
import java.util.Set;

/**
 * @author Jamil Rzayev March 2015
 */

public abstract class AbstractScraperService implements ScraperService {

    private static Logger logger = Logger.getLogger(AbstractScraperService.class.getName());

    private static final String DATA_EXTRACTOR = "";

    protected Set<String> productURLSet;

    protected Iterator<String> itemURLIterator;

    protected Boolean pagesLoaded = Boolean.FALSE;

    protected Document document;

    protected StoreCategory storeCategory;

    protected ScraperConfig scraperConfig;

    protected DataExtractor dataExtractor;

    protected abstract Set<String> extractItemURL(Integer count);

    protected void setScrapeSizes(){

        Boolean toScrapeSizes =  (this.storeCategory.getCategory().getName().equalsIgnoreCase(CategoryParam.BAG.toString())) ?
                Boolean.FALSE :
                Boolean.TRUE;

        this.dataExtractor.setToScrapeSizes(toScrapeSizes);
    }

    protected void loadLandingPage() {

        this.document = ScraperHelper.loadHTMLDocument(this.storeCategory.getUrl());
    }


    @Override
    public void setStoreCategory(StoreCategory storeCategory) {
        this.storeCategory = storeCategory;
        this.itemURLIterator = null;
        this.document = null;
        this.pagesLoaded = Boolean.FALSE;
    }

    @Override
    public Set<String> getItemURL(Integer count) {
        return extractItemURL(count);
    }

    @Override
    public Set<String> getAllItemURL() {
        return extractItemURL(-1);
    }

    @Override
    public String getDataExtractorName(){
        return DATA_EXTRACTOR;
    }

    protected void tryThreadPause() {

        try {
            Thread.sleep(100);
        } catch (InterruptedException inEx) {
            logger.error(inEx.getMessage());
        }
    }

    private void initDatExtractor() {
        if (this.dataExtractor == null)
            this.dataExtractor = (DataExtractor) SpringContextProvider.getBean(getDataExtractorName());
    }

    @Override
    public void setScraperConfig(ScraperConfig scraperConfig){

        this.scraperConfig = scraperConfig;
    }

    @Override
    public boolean hasNext() {

        if (!pagesLoaded) {

            initDatExtractor();
            loadItemURL();
            pagesLoaded = Boolean.TRUE;
        }

        return itemURLIterator.hasNext();
    }

    protected void loadItemURL() {

        switch (scraperConfig.getMode()) {
            case ALL: {

                productURLSet = getAllItemURL();
            }
            break;
            case LIMITED: {

                Integer quantity  = (scraperConfig.getQuantity() > storeCategory.getQuantity())  ?
                        scraperConfig.getQuantity():
                        storeCategory.getQuantity();

                productURLSet = getItemURL(quantity);

            }
            break;
            default:
        }

        logger.info("Product List Size ".concat( new Integer(productURLSet.size()).toString()));

        itemURLIterator = productURLSet.iterator();
    }

    @Override
    public ProductView next() {

        tryThreadPause();

        String url = itemURLIterator.next();

        logger.debug("PRODUCT ".concat(url));

        Document itemDoc = ScraperHelper.loadHTMLDocument(url);


        if ( itemDoc == null || !itemDoc.hasText() || itemDoc.text().contains("Sorry, we couldn't find this page"))
            return new ProductView(Boolean.TRUE);

        ProductView productView = dataExtractor.extract(itemDoc);
        productView.setUrl(url);

        return productView;
    }
}
