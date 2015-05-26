package org.fscraper.data.nosql.bo;

import org.fscraper.data.nosql.model.ItemDocument;
import org.fscraper.data.view.ProductView;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * Created by jr on 3/22/2015.
 */

@Service("MongoViewConvertor")
public class ViewConverter {

    private static Logger logger = Logger.getLogger(ViewConverter.class.getName());

    public ViewConverter(){

    }

    public ItemDocument convert(ProductView productView) {

        ItemDocument itemDocument = new ItemDocument();

        itemDocument.setValue(ItemDocument.TITLE,   productView.getTitle());
        itemDocument.setValue(ItemDocument.DESCRIPTION,   productView.getDescription());
        itemDocument.setValue(ItemDocument.URL,     productView.getUrl());
        itemDocument.setValue(ItemDocument.BRAND,   productView.getBrand());
        itemDocument.setValue(ItemDocument.SKU,     productView.getSku());
        itemDocument.setValue(ItemDocument.IMAGES,  productView.getImages().toArray( new String[0]));
        itemDocument.setValue(ItemDocument.PRICE,   productView.getPrice());
        itemDocument.setValue(ItemDocument.SIZE,    productView.getSizes().toArray( new String[0]));
        itemDocument.setValue(ItemDocument.COLOR,   productView.getColors().toArray( new String[0]));

        return itemDocument;
    }
}
