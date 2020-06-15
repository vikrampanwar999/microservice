package com.igoujime.ecom.checkout.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Checkout {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @NotNull
    private BigDecimal amount;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10)")
    private CheckoutStatus checkoutStatus;

    @ManyToOne
    @NotNull
    private Cart cart;

    public Checkout() {
        this.checkoutStatus = CheckoutStatus.NPAID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public CheckoutStatus getCheckoutStatus() {
        return checkoutStatus;
    }

    public void setCheckoutStatus(CheckoutStatus checkoutStatus) {
        this.checkoutStatus = checkoutStatus;
    }
}
