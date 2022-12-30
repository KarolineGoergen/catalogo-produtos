package com.desafio.products.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.products.model.Product;
import com.desafio.products.model.dto.ProductDTO;
import com.desafio.products.repository.ProductRepository;
import com.desafio.products.service.exceptions.ObjNotFound;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;

    public Product create(ProductDTO productDTO){
        Product newProduct = new Product(productDTO);
        return productRepository.save(newProduct);
    }

    public List<Product> findAll(){
        return productRepository.findAll();
    }


}
