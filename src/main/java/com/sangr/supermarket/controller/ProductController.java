/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sangr.supermarket.controller;

import com.sangr.supermarket.dto.ProductDTO;
import com.sangr.supermarket.exception.NotFoundException;
import com.sangr.supermarket.service.IProduct;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author sangr
 */
@RestController
@RequestMapping ("/api/products")
public class ProductController {
    
    @Autowired
    private IProduct prodSer;
    
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getProducts(){
        List<ProductDTO> products = prodSer.getProducts();
        return ResponseEntity.ok(products);
    }
    
    @GetMapping ("/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable Long id){
        ProductDTO product = prodSer.getProduct(id);
        return ResponseEntity.ok(product);
    }
    
    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO){
        ProductDTO created = prodSer.createProductDTO(productDTO);
        return ResponseEntity.created(URI.create("/api/products/" + created.getId())).body(created);
    }
    
    @PutMapping("{id}")
    public ResponseEntity<ProductDTO> updateProduct (ProductDTO productDTO, @PathVariable Long id){
        ProductDTO product = prodSer.createProductDTO(productDTO);
        return ResponseEntity.ok(product);
    }
    
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        prodSer.deleteProductDTO(id);
        return ResponseEntity.noContent().build();
    }
}
