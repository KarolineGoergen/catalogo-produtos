package com.desafio.products.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.desafio.products.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

    @Query(value = "SELECT * FROM product WHERE price > ? AND price < ?", nativeQuery = true)
    List<Product> findByPrice(double min_price, double max_price);
}
