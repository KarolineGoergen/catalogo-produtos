package com.desafio.products.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.desafio.products.model.Product;
import com.desafio.products.model.dto.ProductDTO;
import com.desafio.products.service.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDTO> create(@Valid @RequestBody ProductDTO productDTO){
        Product newProduct = productService.create(productDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newProduct.getId()).toUri();
        return ResponseEntity.created(uri).body(new ProductDTO(newProduct));
    }
    
    @GetMapping
    public ResponseEntity<List<ProductDTO>> findAll(){
        List<Product> list = productService.findAll();
		List<ProductDTO> listDTO = list.stream().map(obj -> new ProductDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(listDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> findById(@RequestParam(value="id", required=true) Integer id){
        Optional<Product> product = productService.findById(id);

        if(product.isPresent()){
            return ResponseEntity.ok(new ProductDTO(product.get()));
        }
        
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> delete(@RequestParam(value="id", required=true) Integer id){

        Optional<Product> product = productService.findById(id);

        if(product.isPresent()){
            productService.delete(id);
            return ResponseEntity.ok().build();
        }
        
        return ResponseEntity.notFound().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> update(@RequestParam(value="id", required=true) Integer id, @Valid @RequestBody ProductDTO productDTO){

        Optional<Product> product = productService.findById(id);

        if(product.isPresent()){
            productService.update(id, productDTO);
            return ResponseEntity.ok(new ProductDTO(product.get()));
        }

        return ResponseEntity.notFound().build();
        
    }
    
    @GetMapping(value = "/search")
    public ResponseEntity<List<ProductDTO>> findByFilter(@RequestParam(value="min_price", required=false)String min_price, 
    @RequestParam(value="max_price", required=false)String max_price, @RequestParam(value="q", required=false)String q){

        List<Product> list = productService.findByFilter(min_price, max_price, q);
        List<ProductDTO> listDTO = list.stream().map(obj -> new ProductDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(listDTO);

    }


}
