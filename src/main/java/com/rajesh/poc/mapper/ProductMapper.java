package com.rajesh.poc.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.rajesh.poc.dto.ProductDto;
import com.rajesh.poc.entity.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
	ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
	
    ProductDto toProductDto(Product product);
    Product toProduct(ProductDto productDto);
}
