package com.eCommerce.controller;

import com.eCommerce.dto.ProductDto;
import com.eCommerce.entity.Product;
import com.eCommerce.secuity.JwtTokenProvider;
import com.eCommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/shop")
public class ProductController {


    JwtTokenProvider jwtTokenProvider;

    @Autowired
    public void setJwtTokenProvider(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product")
    public List<Product> getAllProduct()
    {
        return productService.getAllProduct();
    }

    @PostMapping("/product")
    public ResponseEntity saveProduct(@RequestHeader("Authorization") String token,@RequestBody ProductDto productDto)
    {

        String username = jwtTokenProvider.getUsername(token.substring(7));
        Product product = productService.saveProduct(productDto, username);

        return new ResponseEntity(product, HttpStatus.OK);

    }

    @GetMapping("/product/{name}")
    public ResponseEntity getByName(@PathVariable("name") String name)
    {
        Set<Product> byName = productService.findBYName(name);
        return new ResponseEntity(byName,HttpStatus.OK);
    }

    @GetMapping("/seller/sellerId")
    public Product getBySellerId(@RequestParam long sellerId)
    {
        return productService.getAllProductBySellerId(sellerId);
    }
}
