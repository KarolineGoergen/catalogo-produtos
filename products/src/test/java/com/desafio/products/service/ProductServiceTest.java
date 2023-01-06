package com.desafio.products.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.desafio.products.model.Product;
import com.desafio.products.model.dto.ProductDTO;
import com.desafio.products.repository.ProductRepository;

import java.util.ArrayList;
import java.util.Optional;

public class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    private ProductService productService;

    @BeforeEach
    void initialize() {
        MockitoAnnotations.openMocks(this);
        this.productService = new ProductService(this.productRepository);
    }

    @Test
    void testCreate() {
        ProductDTO productDTO = this.mockProductDTO();

        Mockito.when(this.productRepository.save(this.mockProduct())).thenReturn(this.mockProduct());

        Product product = this.productService.create(productDTO);

        assertEquals("Cabo USB", product.getName());
    }

    @Test
    void testFindById() {

        Mockito.when(this.productRepository.findById(1)).thenReturn(Optional.of(this.mockProduct()));

        Optional<Product> product = this.productService.findById(1);

        assertTrue(product.isPresent());


    }

    @Test
    void testUpdate(){

        ProductDTO productDTO = this.mockProductDTO();

        Product productMock = mockProduct();

        productMock.setId(1);

        Mockito.when(this.productRepository.save(productMock)).thenReturn(this.mockProduct());

        Product product = this.productService.update(1, productDTO);

        assertEquals("Type C", product.getDescription());

    }

    @Test
    void testFindAll() {
        ArrayList<Product> products = new ArrayList<>();

        products.add(this.mockProduct());
        products.add(this.mockProduct());
        products.add(this.mockProduct());

        Mockito.when(this.productRepository.findAll()).thenReturn(products);

        assertEquals(3, this.productService.findAll().size());
    }

    @Test
    void testDelete() {

        this.productService.delete(1);

        Mockito.verify(this.productRepository, Mockito.times(1)).deleteById(1);
    }

    private ProductDTO mockProductDTO() {
        return ProductDTO.builder()
                .name("Cabo USB")
                .description("Type C")
                .price(45.00)
                .build();
    }

    ;

    private Product mockProduct() {
        return Product.builder()
                .name("Cabo USB")
                .description("Type C")
                .price(45.00)
                .build();
    }

    ;
}
