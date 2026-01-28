/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sangr.supermarket.service;

import com.sangr.supermarket.dto.ProductDTO;
import java.util.List;

/**
 *
 * @author sangr
 */
public interface IProduct {
    List<ProductDTO> getProducts (); //Get
    ProductDTO getProduct(Long id); //Get
    ProductDTO createProductDTO (ProductDTO productDTO); //Create
    ProductDTO updateProductDTO (ProductDTO productDTO, Long id); //Update
    void deleteProductDTO(Long id); //Delete
}
