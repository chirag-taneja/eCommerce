package com.eCommerce.service;

import com.eCommerce.entity.Cart;
import com.eCommerce.entity.CartProduct;
import com.eCommerce.entity.Product;
import com.eCommerce.entity.User;
import com.eCommerce.repo.CartProductRepo;
import com.eCommerce.repo.CartRepo;
import com.eCommerce.repo.ProductRepo;
import com.eCommerce.repo.UserRepo;
import com.eCommerce.secuity.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    ProductRepo productRepo;
    CartRepo cartRepo;
    UserRepo userRepo;
    JwtTokenProvider jwtTokenProvider;

    CartProductRepo cartProductRepo;


    @Autowired
    public void setProductRepo(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Autowired
    public void setCartProductRepo(CartProductRepo cartProductRepo) {
        this.cartProductRepo = cartProductRepo;
    }

    @Autowired
    public void setJwtTokenProvider(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Autowired
    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Autowired
    public CartService(CartRepo cartRepo) {
        this.cartRepo = cartRepo;
    }

    public List<Cart> getUserCart(String token) {

        String username = jwtTokenProvider.getUsername(token.substring(7));
        User user = userRepo.findByUserName(username).get();
        Cart cart = cartRepo.findByUserUserId(user.getUserId()).get();
        if (cart!=null)
        {
            Cart cart1=Cart.builder()
                    .totalAmount(0)
                    .user(user)
                    .build();
            return Collections.singletonList(cartRepo.save(cart1));
        }
        return cartRepo.findAllByUserUserId(user.getUserId());

    }

    public Cart postProduct(String token, int productId) {
        //getting user id with token
        String username = jwtTokenProvider.getUsername(token);
        User user = userRepo.findByUserName(username).get();
        long userId = user.getUserId();
        //check if product is already presnt than increase quanity
        Optional<CartProduct> cartProduct = cartProductRepo.findByProductProductIdAndCartUserUserId(Long.valueOf(productId), userId);

        if (cartProduct.isPresent()) {
            int quantity = cartProduct.get().getQuantity();
            quantity++;
            cartProduct.get().setQuantity(quantity);
            cartProductRepo.save(cartProduct.get());
        }

        //other vise add it

        else {
            //check if user have cart
            boolean b = cartRepo.existsByUserUserId(userId);
            if (b) {
                Optional<Product> byId = productRepo.findById((long) productId);
                if (!byId.isPresent())
                {
                    throw  new RuntimeException("Product Not Present");
                }
                Cart cart = cartRepo.findByUserUserId(userId).get();
                CartProduct cartProduct1=CartProduct.builder()
                        .cart(cart)
                        .quantity(1)
                        .product(byId.get())
                        .build();
                cartProductRepo.save(cartProduct1);
            } else {
                Optional<Product> byId = productRepo.findById((long) productId);
                if (!byId.isPresent())
                {
                    throw  new RuntimeException("Product Not Present");
                }
                Cart cart=Cart.builder()
                        .totalAmount(0)
                        .user(user)
                        .build();
                cartRepo.save(cart);
                CartProduct cartProduct1=CartProduct.builder()
                        .cart(cart)
                        .quantity(1)
                        .product(byId.get())
                        .build();
                cartProductRepo.save(cartProduct1);
            }
        }
        //get cart product and change price

        Cart cart1= cartRepo.findByUserUserId(userId).get();

        double toatalPrice=0;
        for (int i=0;i<cart1.getCartProducts().size();i++)
        {
            double temp=cart1.getCartProducts().get(i).getProduct().getPrice();
            if (cart1.getCartProducts().get(i).getQuantity()>1)
            {
                temp*=cart1.getCartProducts().get(i).getQuantity();
            }
            toatalPrice+=temp;
        }

        cart1.setTotalAmount(toatalPrice);
        return cartRepo.save(cart1);
    }
}
