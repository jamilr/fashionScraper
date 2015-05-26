package org.fscraper.data.sql.model;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Jamil Rzayev March 2015
 */

@Entity
@Table(name = "Offer")
public class Offer implements IEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "offer_id", referencedColumnName = "id", nullable = false)
    private Product offeredProduct;

    @Column(name = "price")
    private BigDecimal price;

    public Offer() {

    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Product getOfferedProduct() {
        return offeredProduct;
    }

    public void setOfferedProduct(Product offeredProduct) {
        this.offeredProduct = offeredProduct;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
