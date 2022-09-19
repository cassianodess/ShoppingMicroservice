package com.cassianodess.shoppingcart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cassianodess.shoppingcart.models.Cart;
import com.cassianodess.shoppingcart.models.Item;
import com.cassianodess.shoppingcart.repository.CartRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CartService {
    
    @Autowired
    private CartRepository repository;

    public List<Cart> list() {
        return this.repository.findAll();
    }


    public Cart getCartById(Long cartId) {
        return this.repository.findById(cartId).orElseThrow(() -> {
            log.error("Cart with id {} not found", cartId);
             throw new RuntimeException("id " + cartId + " not found");
         });
    }

    public Cart insertItemInCart(Long cartId, Item item) {

        Cart cart = this.repository.findById(cartId).orElseThrow(() -> {
           log.error("Cart with id {} not found", cartId);
            throw new RuntimeException("id " + cartId + " not found");
        });

        cart.getItems().add(item);

        this.repository.save(cart);

        log.info("Item inserted successfully");

        return cart;
    }

    public Cart removeItemFromCart(Long cartId, Item item) {
        Cart cart = this.repository.findById(cartId).orElseThrow(() -> {
            log.error("Cart with id {} not found", cartId);
             throw new RuntimeException("id " + cartId + " not found");
         });

         cart.getItems().remove(item);
         this.repository.save(cart);

         return cart;
    }

}
