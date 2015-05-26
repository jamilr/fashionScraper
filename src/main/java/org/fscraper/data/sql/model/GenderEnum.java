package org.fscraper.data.sql.model;

/**
 * Created by jr on 3/22/2015.
 */

public enum GenderEnum {

    FEMALE("f"),
    MALE("m");

    private String value;

    GenderEnum(String v)  {
        this.value = v;
    }

    public static GenderEnum get(String v) {

        GenderEnum[] genderEnums = GenderEnum.values();

        GenderEnum result = GenderEnum.FEMALE;

        for (GenderEnum g : genderEnums) {
            if (g.getValue().equals(v)){
                result = g;
                break;
            }
        }

        return result;
    }

    public String getValue(){
        return this.value;
    }

    @Override
    public String toString(){
        return this.value;
    }
}
