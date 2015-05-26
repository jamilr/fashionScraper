package org.fscraper.data.sql.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by jr on 3/21/2015.
 */

@Entity
@Table(name = "Store")
public class Store implements IEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "shortName")
    private String shortName;

    @Column(name = "longName")
    private String longName;

    @Column(name = "logourl")
    private String logoURL;

    @OneToMany(mappedBy = "store", fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<StoreCategory> storeCategorySet = new HashSet<>();

    public Store() {

    }

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Set<StoreCategory> getStoreCategorySet() {
        return storeCategorySet;
    }

    public void setStoreCategorySet(Set<StoreCategory> storeCategorySet) {
        this.storeCategorySet = storeCategorySet;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == null || (!(obj instanceof Store)))
            return false;

        Store store = (Store)obj;

        return (this.shortName.equals((store).getShortName())
                && (this.longName.equals(store.getLongName()))
                && (this.id.equals(store.getId())));
    }

    public String getLogoURL() {
        return logoURL;
    }

    public void setLogoURL(String logoURL) {
        this.logoURL = logoURL;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }
}
