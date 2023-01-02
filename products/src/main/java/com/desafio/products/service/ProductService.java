package com.desafio.products.service;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.products.model.Product;
import com.desafio.products.model.dto.ProductDTO;
import com.desafio.products.repository.ProductRepository;

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

    public void delete(Integer id){
        productRepository.deleteById(id);
    }

    public Optional<Product> findById(Integer id){
        Optional<Product> product = productRepository.findById(id);
        return product;
    }

    public Product update(Integer id, @Valid ProductDTO productDTO){
        productDTO.setId(id);
        Product oldProduct = new Product(productDTO);
        return productRepository.save(oldProduct);
    }

    public List<Product> findByPrice(double min_price, double max_price){
        return productRepository.findByPrice(min_price, max_price);
       
    }

}
