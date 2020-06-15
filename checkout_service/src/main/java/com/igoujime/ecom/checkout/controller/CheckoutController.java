package com.igoujime.ecom.checkout.controller;

import com.igoujime.ecom.checkout.entity.Checkout;
import com.igoujime.ecom.checkout.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {

    private final CheckoutService checkoutService;

    @Autowired
    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @GetMapping(value = "/")
    public List<Checkout> getAll() {
        return checkoutService.findAll();
    }

    @GetMapping(value = "/{id}")
    public Checkout getOne(@PathVariable Integer id) {
        return checkoutService.findOne(id);
    }

    @PostMapping(value = "/")
    public Checkout create(@RequestBody Checkout checkout) {
        return checkoutService.save(checkout);
    }

//    @PutMapping(value = "/{id}")
//    public Checkout update(@PathVariable Integer id, @RequestBody Checkout checkout) {
//        return checkoutService.update(id, checkout);
//    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Integer id) {
        checkoutService.delete(id);
    }

}
