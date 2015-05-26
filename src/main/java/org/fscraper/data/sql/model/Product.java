package org.fscraper.data.sql.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;

/**
 * Jamil Rzayev March 2015
 */

@Entity
@Table(name = "Product")
public class Product implements IEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Integer id;

    @NotNull
    @Column(name = "url")
    private String url;

    @NotNull
    @Column(name = "title")
    private String title;

    @NotNull
    @Column(name = "description")
    private String description;

    @NotNull
    @ManyToOne()
    @JoinColumn(name = "brand_id", referencedColumnName = "id", nullable = false)
    private Brand brand;

    @NotNull
    @ManyToOne()
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    private Category category;

    @NotNull
    @ManyToOne()
    @JoinColumn(name = "store_id", referencedColumnName = "id", nullable = false)
    private Store store;

    @ManyToOne()
    @JoinColumn(name = "season_id", referencedColumnName = "id", nullable = true)
    private Season season;

    @ManyToOne()
    @JoinColumn(name = "style_id", referencedColumnName = "id", nullable = true)
    private Style style;

    @NotNull
    @Column(name = "gender")
    @Enumerated(EnumType.ORDINAL)
    private GenderEnum gender;

    @NotNull
    @Column(name = "price")
    private BigDecimal price;

    @NotNull
    @Column(name = "sku")
    private String sku;

    @NotNull
    @Column(name = "liked")
    private Boolean liked;

    @NotNull
    @Column(name = "hasOffer")
    private Boolean hasOffer;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<ProductImage> productImages = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name="ProductColor",
            joinColumns={@JoinColumn(name="product_id")},
            inverseJoinColumns={@JoinColumn(name="color_id")})
    private Set<Color> colors = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name="ProductSize",
            joinColumns={@JoinColumn(name="product_id")},
            inverseJoinColumns={@JoinColumn(name="size_id")})
    private Set<Size> sizes = new HashSet<>();

    public Product(){

    }

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Set<ProductImage> getProductImages() {
        return productImages;
    }

    public void setProductImages(Set<ProductImage> productImages) {
        this.productImages.clear();
        this.productImages = productImages;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Set<Color> getColors() {
        return colors;
    }

    public void setColors(Set<Color> colorList) {
        this.colors.clear();
        this.colors = colorList;
    }

    public Set<Size> getSizes() {
        return sizes;
    }

    public void setSizes(Set<Size> sizeList) {
        this.sizes.clear();
        this.sizes = sizeList;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public void addSize(Size size) {
        this.sizes.add(size);
    }

    public void addColor(Color color) {
        this.colors.add(color);
    }

    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }

    public Boolean getLiked() {
        return liked;
    }

    public void setLiked(Boolean liked) {
        this.liked = liked;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Boolean getHasOffer() {
        return hasOffer;
    }

    public void setHasOffer(Boolean hasOffer) {
        this.hasOffer = hasOffer;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

}
