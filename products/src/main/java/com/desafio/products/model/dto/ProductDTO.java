package com.desafio.products.model.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.desafio.products.model.Product;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private int id;

    @NotBlank(message = "O campo 'nome' não pode estar vazio!")
    private String name;

    @NotBlank(message = "O campo 'descrição' não pode estar vazio!")
    private String description;

    @NotNull(message = "O campo 'preço' não pode estar vazio!")
    @Min(value = 0, message = "O 'preço' não pode ser nagativo!")
    private double price;

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
    }
    
}
