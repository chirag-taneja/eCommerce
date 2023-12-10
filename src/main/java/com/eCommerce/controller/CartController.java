package com.eCommerce.controller;

import com.eCommerce.entity.Cart;
import com.eCommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consumer")
public class CartController {


    CartService cartService;


    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/cart")
    public ResponseEntity getUserCart(@RequestHeader("Authorization") String token)
    {
        List<Cart> userCart = cartService.getUserCart(token);
        return new ResponseEntity(userCart, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/cart")
    public Cart postProduct(@RequestHeader("Authorization") String token, @RequestParam int productId)
    {
        return cartService.postProduct(token.substring(7), productId);
    }
}
