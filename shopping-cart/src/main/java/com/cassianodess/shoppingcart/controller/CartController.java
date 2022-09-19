package com.cassianodess.shoppingcart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cassianodess.shoppingcart.models.Cart;
import com.cassianodess.shoppingcart.models.Item;
import com.cassianodess.shoppingcart.service.CartService;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    @Autowired
    private CartService service;

    @PostMapping("/{id}")
    public ResponseEntity<Cart> create(@PathVariable Long id, @RequestBody Item item){
        return ResponseEntity.ok(this.service.insertItemInCart(id, item));
    }
    
}
