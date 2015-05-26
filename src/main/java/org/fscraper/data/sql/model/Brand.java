package org.fscraper.data.sql.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by jr on 3/21/2015.
 */

@Entity
@Table(name = "Brand")
public class Brand implements IEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Integer id;

    @NotNull
    @Column(name = "name")
    private String name;

    @Column(name = "logourl")
    private String logourl;

    public Brand(){

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

        if (obj == null || (obj instanceof Brand))
            return false;

        Brand brand = (Brand)obj;

        return (this.name.equals((brand).getName()) && (this.id.equals(brand.getId())));
    }

    public String getLogourl() {
        return logourl;
    }

    public void setLogourl(String logourl) {
        this.logourl = logourl;
    }
}
