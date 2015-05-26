package org.fscraper.data.view;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by jr on 3/22/2015.
 */

public class ProductView {

    private Boolean empty = Boolean.FALSE;

    private String url;

    private String title;

    private String description;

    private String sku;

    private String price;

    private String brand;

    private String category;

    private String store;

    private String color;

    private Set<String> imgURLList;

    private Set<String> sizeList;

    private Set<String> colorList;

    public ProductView(){
        this.sizeList = new HashSet<>();
        this.colorList = new HashSet<>();
        this.imgURLList = new HashSet<>();
    }

    public ProductView(Boolean isEmpty){
        super();
        this.empty = isEmpty;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Set<String> getImages() {
        return imgURLList;
    }

    public void setImgURLList(Set<String> imgURLList) {
        this.imgURLList = imgURLList;
    }

    public Set<String> getSizes() {
        return sizeList;
    }

    public void setSizes(Set<String> sizeList) {
        this.sizeList = sizeList;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Set<String> getColors() {
        return colorList;
    }

    public void setColors(Set<String> colorList) {
        this.colorList = colorList;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean isEmpty() {
        return empty;
    }

    public void setEmpty(Boolean empty) {
        this.empty = empty;
    }
}
