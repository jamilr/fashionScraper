package org.fscraper.data;

import org.fscraper.data.view.ProductView;
import org.fscraper.helpers.TextHelper;

/**
 * Created by jr on 3/31/2015.
 */

public abstract class AbstractDataImportService {

    protected boolean isValid(ProductView productView) {

        boolean isValid = true;

        if (productView.isEmpty()
                || productView.getBrand().isEmpty()
                || productView.getImages().isEmpty()
                || productView.getColors().isEmpty()
                || productView.getTitle().isEmpty()
                || productView.getDescription().isEmpty()
                || !TextHelper.isDouble(productView.getPrice())) {

            isValid = false;
        }

        if (!productView.getCategory().contains("bag") && productView.getSizes().isEmpty())
                isValid = false;

        return isValid;
    }
}
