package com.igoujime.ecom.cart.controller;

import com.igoujime.ecom.cart.entity.Cart;
import com.igoujime.ecom.cart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping(value = "/")
    public List<Cart> getAll() {
        return cartService.findAll();
    }

    @GetMapping(value = "/{id}")
    public Cart getOne(@PathVariable Integer id) {
        return cartService.findOne(id);
    }

    @PostMapping(value = "/")
    public Cart create(@RequestBody Cart cart) {
        return cartService.save(cart);
    }

//    @PutMapping(value = "/{id}")
//    public Cart update(@PathVariable Integer id, @RequestBody Cart cart) {
//        return cartService.update(id, cart);
//    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Integer id) {
        cartService.delete(id);
    }

}
