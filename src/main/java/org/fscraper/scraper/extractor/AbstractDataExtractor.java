package org.fscraper.scraper.extractor;

import org.fscraper.data.view.ProductView;
import org.fscraper.helpers.TextHelper;
import org.fscraper.scraper.DataExtractor;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;

import java.util.Set;

/**
 * Created by Jamil Rzayev March 2015
 */

public abstract class AbstractDataExtractor implements DataExtractor {

    private static Logger logger = Logger.getLogger(AbstractDataExtractor.class.getName());

    protected Document document;

    protected Boolean toScrapeSizes = Boolean.FALSE;

    protected abstract String getTitle();
    protected abstract String getDescription();
    protected abstract String getBrand();
    protected abstract String getSKU();

    protected abstract Set<String> getSizes();
    protected abstract Set<String> getColors();
    protected abstract Set<String> getImages();

    protected abstract String getPrice();

    protected String parsePrice(String priceValue) {

        if (priceValue.contains(","))
            priceValue = priceValue.replace(",", "");

        logger.debug("PRICE ".concat(priceValue));

        if (priceValue.contains("£"))
            priceValue = priceValue.substring(1);

        return priceValue;
    }

    @Override
    public void setToScrapeSizes(Boolean toScrape) {
        this.toScrapeSizes = toScrape;
    }

    @Override
    public ProductView extract(Document document) {

        this.document = document;

        String title = TextHelper.removeAllNonUnicode(getTitle());
        String brand = TextHelper.removeAllNonUnicode(getBrand());

        String price = getPrice();
        String sku = TextHelper.removeAllNonUnicode(getSKU());
        String description = TextHelper.removeAllNonUnicode(getDescription());

        Set<String> imgURLSet = getImages();

        ProductView productView = new ProductView();

        productView.setTitle(title);
        productView.setBrand(brand);
        productView.setPrice(price);
        productView.setSku(sku);

        productView.setDescription(description);
        productView.setImgURLList(imgURLSet);

        if (this.toScrapeSizes)
            productView.setSizes(getSizes());

        productView.setColors(getColors());

        return productView;
    }
}
