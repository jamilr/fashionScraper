package org.fscraper.data.sql.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Jamil Rzayev March 2015
 */

@Entity
@Table(name = "Size")
public class Size implements IEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Integer id;

    @Column(name = "value")
    private String value;

    @ManyToOne
    @JoinColumn(name = "store_id",      referencedColumnName = "id", nullable = false)
    private Store store;

    @ManyToOne
    @JoinColumn(name = "category_id",   referencedColumnName = "id", nullable = false)
    private Category category;

    @ManyToMany(mappedBy="sizes")
    private Set<Product> products = new HashSet<>();

    public Size(){

    }


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object sizeObj) {

        if (!(sizeObj instanceof Size))
                return false;

        if (!this.getCategory().equals(((Size) sizeObj).getCategory()))
            return false;

        if (!this.getStore().equals(((Size) sizeObj).getStore()))
            return false;

        if (!this.getValue().equals(((Size) sizeObj).getValue()))
            return false;

        return true;
    }
}
