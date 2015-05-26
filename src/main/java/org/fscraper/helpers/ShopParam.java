package org.fscraper.helpers;

/**
 * @author Jamil Rzayev March 2015
 */

public enum ShopParam {

    MS("MS"),
    DEBENHAMS("Debenhams");

    ShopParam(String name){
        this.shop = name;
    }

    private String shop;

    public String toString(){
        return this.shop;
    }
}
