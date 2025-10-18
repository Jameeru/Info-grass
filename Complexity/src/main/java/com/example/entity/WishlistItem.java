package com.example.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "wishlist_items")
public class WishlistItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wishlist_item_id")
    private long wishlistItemId;

    @ManyToOne
    @JoinColumn(name = "wishlist_id", nullable = false)
    private Wishlist wishlist;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    // Getters and setters
    public long getWishlistItemId() { return wishlistItemId; }
    public void setWishlistItemId(int wishlistItemId) { this.wishlistItemId = wishlistItemId; }
    public Wishlist getWishlist() { return wishlist; }
    public void setWishlist(Wishlist wishlist) { this.wishlist = wishlist; }
    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }
}