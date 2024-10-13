package com.example.demo.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "shopping_carts")
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    private Double totalAmount;

    @Transient
    private int itemsQuantity;

    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(unique = true)
    private String tokenSession;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Collection<CartItem> items;

    public Double getTotalAmount(){
        return items.stream().mapToDouble(i->i.getLibro().getPrezzo()).sum();
    }
    public int getItemsQuantity(){
        return this.items.size();
    }
}
