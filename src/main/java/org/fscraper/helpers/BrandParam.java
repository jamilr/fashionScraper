package org.fscraper.helpers;

/**
 * Created by jr on 3/21/2015.
 */
public enum BrandParam {

    LV("Louis Vuitton");

    private String name;

    BrandParam(String brand) {
        this.name = brand;
    }

    public String toString(){
        return this.name;
    }
}
