/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sangr.supermarket.service;

import com.sangr.supermarket.dto.ProductDTO;
import com.sangr.supermarket.entity.Product;
import com.sangr.supermarket.mapper.Mapper;
import com.sangr.supermarket.repository.ProductRepository;
import com.sangr.supermarket.entity.Product;
import com.sangr.supermarket.exception.NotFoundException;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author sangr
 */
@Service
@Transactional
public class ProductService implements IProduct{
    
    @Autowired
    private ProductRepository repo;

    @Override
    public List<ProductDTO> getProducts() {
        return repo.findAll().stream().map(Mapper::toProductDTO).toList();
    }
    
    @Override
    public ProductDTO getProduct(Long id){
        Product prod = repo.findById(id).orElseThrow(() -> new NotFoundException("No se encontró la venta"));
        ProductDTO dto = Mapper.toProductDTO(prod);
        return dto;
    }

    @Override
    public ProductDTO createProductDTO(ProductDTO productDTO) {
        Product prod = Product.builder().name(productDTO.getName()).price(productDTO.getPrice()).category(productDTO.getCategory()).amount(productDTO.getAmount()).build();
        return Mapper.toProductDTO(repo.save(prod));
    }

    @Override
    public ProductDTO updateProductDTO(ProductDTO productDTO, Long id) {
        Product prod = repo.findById(id)
        .orElseThrow(()-> new NotFoundException("Producto no encontrado"));
        
        prod.setAmount(productDTO.getAmount());
        prod.setCategory(productDTO.getCategory());
        prod.setName(productDTO.getName());
        prod.setPrice(productDTO.getPrice());
        
        return Mapper.toProductDTO(repo.save(prod));
    }

    @Override
    public void deleteProductDTO(Long id) {
       Product prod = repo.findById(id)
       .orElseThrow(() -> new NotFoundException("Producto no encontrado para eliminar"));
       
       repo.deleteById(id);
    }
    
}
