package org.fscraper.entry;


/**
 * Created by jr on 3/22/2015.
 */
public class ScraperOptions {

    public static final String STORE = "store";
    public static final String CATEGORY = "category";
    public static final String QUANTITY = "quantity";
    public static final String MODE = "mode";
    public static final String DATASOURCE = "data";

    public static final String NO_VALUE = "N/A";

    private String store;

    private CategoryParam[] categories;

    private Boolean allCategories;

    private Integer quantity;

    private ScraperMode mode;

    private DataSourceType dataSourceType;

    public ScraperOptions() {
        this.allCategories = Boolean.FALSE;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public CategoryParam[] getCategories() {
        return categories;
    }

    public void setCategories(CategoryParam[] categories) {
        this.categories = categories;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public ScraperMode getMode() {
        return mode;
    }

    public void setMode(ScraperMode mode) {
        this.mode = mode;
    }

    public Boolean getAllCategories() {
        return allCategories;
    }

    public void setAllCategories(Boolean allCategories) {
        this.allCategories = allCategories;
    }

    public DataSourceType getDataSourceType() {
        return dataSourceType;
    }

    public void setDataSourceType(DataSourceType dataSourceType) {
        this.dataSourceType = dataSourceType;
    }
}
