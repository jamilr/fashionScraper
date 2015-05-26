package org.fscraper.scraper;

import org.fscraper.config.SpringContextProvider;
import org.fscraper.data.DataManager;
import org.fscraper.data.sql.bo.CoreBO;
import org.fscraper.data.sql.model.Category;
import org.fscraper.data.sql.model.GenderEnum;
import org.fscraper.data.sql.model.Store;
import org.fscraper.data.sql.model.StoreCategory;
import org.fscraper.data.view.ProductView;
import org.fscraper.entry.CategoryParam;
import org.fscraper.entry.ScraperOptions;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Jamil Rzayev March 2015
 */

@Component("ScraperGateway")
public class ScraperGateway {

    private static final String STOREID = "store.id" ;
    private static final String CATEGORYID = "category.id";
    private static final String GENDER = "gender";

    private static Logger logger = Logger.getLogger(ScraperGateway.class.getName());

    @Autowired
    @Qualifier("CoreBO")
    private CoreBO coreBO;

    private Store store;

    private List<StoreCategory> storeCategories;

    private ScraperOptions scraperOptions;

    private ScraperService scraperService;

    @Autowired
    @Qualifier("DataManager")
    private DataManager dataManager;

    public void run(ScraperOptions scraperOptions) {

        this.scraperOptions = scraperOptions;

        dataManager.setDataSourceType(scraperOptions.getDataSourceType());

        findStore();

        if (store == null)
            return;

        findStoreCategory();

        if (storeCategories.isEmpty())
            return;

        scraperService = (ScraperService) SpringContextProvider.getBean(store.getShortName());

        ScraperConfig scraperConfig = new ScraperConfig();
        scraperConfig.setMode(scraperOptions.getMode());
        scraperConfig.setQuantity(scraperOptions.getQuantity());

        scraperService.setScraperConfig(scraperConfig);

        ProductView productView;

        for (StoreCategory storeCategory: storeCategories) {

            scraperService.setStoreCategory(storeCategory);

            dataManager.setStoreCategory(storeCategory);

            logger.info("StoreCategory ".concat(storeCategory.getUrl()));

            while(scraperService.hasNext()){


                productView = scraperService.next();

                productView.setCategory(storeCategory.getCategory().getName().toLowerCase());

                dataManager.save(productView);
            }
        }
    }

    private void findStoreCategory() {

        storeCategories = new ArrayList<>();

        Map<String, Object> params = new LinkedHashMap<>();

        if (scraperOptions.getAllCategories()) {
            this.storeCategories = coreBO.find(StoreCategory.class, "store.id", store.getId());
            return;
        }

        findCategories();

        CategoryParam[] categoryParams  = scraperOptions.getCategories();

        for (CategoryParam ctg: categoryParams) {

            params.clear();
            params.put(STOREID,      store.getId());
            params.put(CATEGORYID,   ctg.getCategoryId());
            params.put(GENDER,       GenderEnum.get(ctg.getGender()));

            List<StoreCategory> storeCategoryList = coreBO.find(StoreCategory.class, params, Boolean.FALSE);

            if (storeCategoryList.isEmpty())
                continue;

            storeCategories.add(storeCategoryList.get(0));
        }
    }

    private void findStore() {

        String storeName = scraperOptions.getStore();
        store = coreBO.findWithLike(Store.class, "shortName", storeName);
    }

    private void findCategories() {

        CategoryParam[] categories = scraperOptions.getCategories();

        Category category;
        for (CategoryParam cat: categories) {

            category = coreBO.findWithLike(Category.class, "name", cat.getCategory());

            if (category == null)
                continue;

            cat.setCategoryId(category.getId());
        }

        scraperOptions.setCategories(categories);
    }
}
