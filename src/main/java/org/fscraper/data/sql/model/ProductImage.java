package org.fscraper.data.sql.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by jr on 3/21/2015.
 */
@Entity
@Table(name = "ProductImage")
public class ProductImage implements IEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    @Column(name = "url")
    private String url;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id",  referencedColumnName = "id", nullable = false)
    private Product product;

    public ProductImage(){

    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

}
