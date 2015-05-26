package org.fscraper.data.sql.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Jamil Rzayev March 2015
 */

@Entity
@Table(name = "StoreCategory")
public class StoreCategory implements IEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "store_id", referencedColumnName = "id",  nullable = false)
    private Store store;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    private Category category;

    @NotNull
    @Column(name = "gender")
    @Enumerated(EnumType.ORDINAL)
    private GenderEnum gender;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    public StoreCategory() {

    }

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
