package org.fscraper.data.sql.model;

import javax.persistence.*;

/**
 * Created by jr on 3/21/2015.
 */

@Entity
@Table(name = "Category")
public class Category implements IEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Integer id;

    @Column(name = "name")
    private String name;

    public Category(){

    }

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == null || (obj instanceof Category))
            return false;

        Category category = (Category)obj;
        return (this.name.equals((category).getName()) && (this.id.equals(category.getId())));
    }
}
