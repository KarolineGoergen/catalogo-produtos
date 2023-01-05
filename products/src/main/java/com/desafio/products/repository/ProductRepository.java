package com.desafio.products.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.desafio.products.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

    @Query(value = "SELECT * FROM product WHERE price >= :min_price AND price <= :max_price AND name LIKE :q OR description LIKE :q", nativeQuery = true)
    List<Product> findByPriceAndNameAndDescriptionList(String min_price, String max_price, String q);

    @Query(value = "SELECT * FROM product WHERE price >= :min_price AND price <= :max_price", nativeQuery = true)
    List<Product> findPrice(String min_price, String max_price);

    @Query(value = "SELECT MAX(price) FROM product", nativeQuery = true)
    String findMaxPrice();
}
