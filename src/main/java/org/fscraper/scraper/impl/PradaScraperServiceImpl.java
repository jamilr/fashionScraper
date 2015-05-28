package org.fscraper.scraper.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.fscraper.helpers.HttpHelpers;
import org.fscraper.scraper.AbstractScraperService;
import org.fscraper.scraper.ScraperService;

import org.apache.log4j.Logger;

import org.junit.Assert;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Jamil Rzayev April 2015
 */

@Service("PR")
public class PradaScraperServiceImpl
        extends AbstractScraperService
        implements ScraperService {

    public static Logger logger = Logger.getLogger(PradaScraperServiceImpl.class.getName());

    protected static String DATA_EXTRACTOR = "PradaDataExtractor";

    private static final String ITEM_URL_TEMPLATE = "http://www.prada.com/en/GB/e-STORE/woman/handbags/";
    private static final String PRODUCT = "/product/";
    private static final String ITEM_URL_POSTFIX = ".html";

    private static final String HTTP_GET_STORE_ITEMS_DATA = "http://www.prada.com/en/e-STORE/collection/department.getProducts.js/woman/handbags?STORE=%2Fetc%2Fcommerce%2Fprada%2Falpha%2Fcatalog%2Festore-eu";

    public PradaScraperServiceImpl(){

    }

    @Override
    public String getDataExtractorName() {

        return PradaScraperServiceImpl.DATA_EXTRACTOR;
    }

    @Override
    protected Set<String> extractItemURL(Integer count) {

        loadLandingPage();
        setScrapeSizes();

        Set<String> itemURLSet = getStoreItemData(count);

        return itemURLSet;
    }

    private Set<String> getStoreItemData(Integer count) {

        Set<String> itemURLSet = new HashSet<>();

        try {

            String response = HttpHelpers.httpGet(HTTP_GET_STORE_ITEMS_DATA);

            if (response.isEmpty())
                return itemURLSet;

            JsonParser jsonParser = new JsonParser();
            JsonObject jo = (JsonObject)jsonParser.parse(response);

            Assert.assertNotNull(jo);

            JsonArray itemCatalogArray = jo.getAsJsonArray("department");

            if (itemCatalogArray.isJsonNull() || itemCatalogArray.size() == 0)
                return itemURLSet;

            for (int i = 0, n = itemCatalogArray.size(); i < n; i++) {

                JsonElement products = itemCatalogArray.get(i);

                if (products.isJsonNull())
                    continue;

                String catalodName = ((JsonObject)products).get("id").getAsString();

                JsonArray productsArray = ((JsonObject)products).getAsJsonArray("products");

                for (int j = 0, m = productsArray.size(); j < m; j++) {

                    JsonElement product = productsArray.get(j);

                    if (!product.isJsonObject())
                        continue;

                    JsonObject productObj = (JsonObject)product;

                    JsonElement productIDEl = productObj.get("id");

                    if (productIDEl.isJsonNull())
                        continue;

                    String productID = productIDEl.getAsString();

                    if (productID == null || productID.isEmpty())
                        continue;

                    String productURL =  new StringBuilder().append(ITEM_URL_TEMPLATE)
                            .append(catalodName)
                            .append(PRODUCT)
                            .append(productID)
                            .append(ITEM_URL_POSTFIX)
                            .toString();

                    if (!itemURLSet.contains(productURL))
                        itemURLSet.add(productURL);

                    if (count > 0  && itemURLSet.size() == count)
                        return itemURLSet;
                }
            }

        } catch (IOException ex) {
            logger.error(ex.getMessage(), ex);
        }

        return itemURLSet;
    }
}
