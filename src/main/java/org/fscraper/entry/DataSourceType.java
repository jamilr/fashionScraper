package org.fscraper.entry;

/**
 * Created by jr on 4/4/2015.
 */

public enum DataSourceType {

    SQL("sql"),
    MONGO("mongo"),
    SQLANDMONGO("sqlmongo");

    private String value;

    DataSourceType(String value){
        this.value = value;
    }

    public String getName(){
        return this.value;
    }

    public String toString(){
        return this.value;
    }

    public static DataSourceType find(String value) {

        for(DataSourceType type: DataSourceType.values()) {
            if (type.name().equalsIgnoreCase(value))
                return type;

        }

        return DataSourceType.SQL;
    }
}
