package org.fscraper.entry;

/**
 * Created by jr on 3/24/2015.
 */

public class CategoryParam {

    private String category;

    private String gender;

    private Integer categoryId;

    public CategoryParam(){

    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}
