package org.fscraper.helpers;

/**
 * Created by jr on 3/22/2015.
 */
public enum CategoryParam {

    BAG("Bags"),
    SHOE("Shoes"),
    DRESS("Dresses");

    private String value;

    CategoryParam(String value) {
        this.value =value;
    }

    public String toString(){
        return this.value;
    }

}
