package org.fscraper.data;

import org.fscraper.data.sql.model.Product;

/**
 * Created by jr on 3/22/2015.
 */

public class DataImportServiceStatus {

    private Boolean status;

    private Product product;

    public DataImportServiceStatus() {
        this.status = Boolean.FALSE;
        this.product = null;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
