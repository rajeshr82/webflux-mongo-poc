package com.nisum.poc.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

	@NotNull(message = "productId should not be null")
    private Long productId;
	@NotNull(message = "name should not be null")
	private String name;
	@NotNull(message = "quantity should not be null")
	private int qty;
	@NotNull(message = "price should not be null")
    private double price;

}
