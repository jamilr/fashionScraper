package org.fscraper.data.sql.bo;

import org.fscraper.data.AbstractDataImportService;
import org.fscraper.data.DataImportService;
import org.fscraper.data.DataImportServiceStatus;
import org.fscraper.data.view.ProductView;
import org.apache.log4j.Logger;

import org.fscraper.data.sql.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * JamiL Rzayev March 2015
 */

@Service("SQLiteDataImportService")
public class DataImportServiceImpl extends AbstractDataImportService
        implements DataImportService {

    private static Logger logger = Logger.getLogger(DataImportServiceImpl.class.getName());

    @Autowired
    @Qualifier("CoreBO")
    private CoreBO coreBO;

    private StoreCategory storeCategory;

    public DataImportServiceImpl(){

    }

    public void setConfig(StoreCategory storeCategory){
        this.storeCategory = storeCategory;
    }

    @Override
    public DataImportServiceStatus save(ProductView productView) {

        if (!super.isValid(productView)) {

            DataImportServiceStatus status = new DataImportServiceStatus();

            status.setStatus(Boolean.FALSE);
            status.setProduct(null);

            return status;
        }

        DataImportServiceStatus status = new DataImportServiceStatus();

        Product product = new Product();

        String brandName  = productView.getBrand();

        Brand brand = coreBO.findWithLike(Brand.class, "name", brandName);

        if (brand == null)
            brand = coreBO.addBrand(brandName);

        product.setUrl(productView.getUrl());
        product.setSku(productView.getSku());

        product.setStore(storeCategory.getStore());
        product.setGender(storeCategory.getGender());
        product.setCategory(storeCategory.getCategory());

        product.setBrand(brand);
        product.setTitle(productView.getTitle());
        product.setDescription(productView.getDescription());

        Double doublePriceValue = new Double(productView.getPrice());
        BigDecimal priceInBigDecimal = new BigDecimal(doublePriceValue);

        product.setPrice(priceInBigDecimal);
        product.setGender(storeCategory.getGender());

        product.setLiked(Boolean.FALSE);
        product.setHasOffer(Boolean.FALSE);

        setImages(product, productView);
        setColors(product, productView);
        setSizes(product, productView);

        product = (Product)coreBO.save(product);

        status.setStatus(Boolean.TRUE);
        status.setProduct(product);

        return status;
    }

    private void setImages(Product product, ProductView productView) {

        Set<ProductImage> productImageSet = new HashSet<>();



//        for (String imgURL : productView.getImages()) {

        String imgURL = productView.getImages().iterator().next();

        ProductImage productImage = new ProductImage();
        productImage.setProduct(product);
        productImage.setUrl(imgURL);

        productImageSet.add(productImage);

        //        }

        product.setProductImages(productImageSet);
    }

    public void setSizes(Product product, ProductView productView) {

        Set<String> sizeList = productView.getSizes();

        if (sizeList.isEmpty())
            return;

        Map<String, Object> params = new LinkedHashMap<>();

        Set<Size> productSizeSet = new HashSet<>();

        for (String sizeStrValue: sizeList) {

            params.clear();

            params.put("store.id", product.getStore().getId());
            params.put("category.id", product.getCategory().getId());
            params.put("value", sizeStrValue);

            List<Size> sizes = coreBO.find(Size.class, params, Boolean.FALSE);

            Size size = (sizes.isEmpty()) ? coreBO.addSize(product, sizeStrValue) : sizes.get(0);

            productSizeSet.add(size);
        }

        product.setSizes(productSizeSet);
    }

    public void setColors(Product product, ProductView productView) {

        Set<String> colorsStrSet = productView.getColors();

        if (colorsStrSet.isEmpty())
            return;

        Set<Color> colorSet = new HashSet<>();

        for (String colorStrValue: colorsStrSet) {

            Color color = coreBO.findWithLike(Color.class, "name", colorStrValue);
            if (color == null) {
                color = coreBO.addColor(colorStrValue);
                colorSet.add(color);
            }

        }

        product.setColors(colorSet);
    }
}
