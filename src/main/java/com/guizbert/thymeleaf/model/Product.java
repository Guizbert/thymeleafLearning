package com.guizbert.thymeleaf.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.Year;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Enumerated(EnumType.STRING)
    @NotNull(message = "Product Type can not be null")
    private ProductType productType;


    @NotBlank(message = "Product type cannot be null")
    private String brand;

    @NotBlank(message = "Model cannot be null")
    private String model;

    @NotNull(message = "Price cannot be null")
    @Positive
    private Double price;
    @NotNull(message = "Year cannot be null")
    private Year year;




}
