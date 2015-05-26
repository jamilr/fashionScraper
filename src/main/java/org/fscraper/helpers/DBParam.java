package org.fscraper.helpers;

/**
 * @author Jamil Rzayev March 2015
 */
public enum  DBParam {

    PORT("27017"),
    IP("localhost"),
    DB_NAME("MaiaMallAppDB");

    DBParam(String name){
        this.value = name;
    }

    private String value;

    public String toString(){
        return this.value;
    }

}
