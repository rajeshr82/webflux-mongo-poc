package com.nisum.poc.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.nisum.poc.dto.ProductDto;
import com.nisum.poc.entity.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
	ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
	
    ProductDto toProductDto(Product product);
    Product toProduct(ProductDto productDto);
}
