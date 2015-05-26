package org.fscraper.data.nosql.model;

import com.mongodb.DBObject;

/**
 * @author Jamil Rzayev March 2015
 */

public class ItemDocument extends BaseDocument {

    public static final String DB_COLLECTION = "Item";

    public static final String ID =         "_id";
    public static final String TITLE =      "name";
    public static final String BRAND =      "brand";
    public static final String CATEGORY =   "category";
    public static final String SKU =        "sku";
    public static final String PRICE =      "price";
    public static final String SIZE =       "size";
    public static final String DESCRIPTION = "desc";
    public static final String IMAGES =     "images";
    public static final String STORE =      "store";
    public static final String COLOR =      "color";
    public static final String URL =        "url";

    public ItemDocument() {
        super();
    }

    public ItemDocument(DBObject dbObj){
        this.document = dbObj;
    }


}
