package org.fscraper.data.sql.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by jr on 3/21/2015.
 */
@Entity
@Table(name = "Color")
public class Color implements IEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Integer id;

    @NotNull
    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy="colors")
    private Set<Product> products = new HashSet<>();

    public Color() {

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

        if (obj == null || (obj instanceof Color))
            return false;

        Color color = (Color)obj;
        return (this.name.equals((color).getName()) && (this.id.equals(color.getId())));
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}

