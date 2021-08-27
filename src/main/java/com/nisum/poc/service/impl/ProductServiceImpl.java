package com.nisum.poc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;

import com.nisum.poc.dto.ProductDto;
import com.nisum.poc.mapper.ProductMapper;
import com.nisum.poc.repository.ProductRepository;
import com.nisum.poc.service.ProductService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	ProductMapper productMapper;

	@Override
	public Flux<ProductDto> getProducts() {
		return productRepository.findAll().map(p -> productMapper.toProductDto(p)).map(p -> (ProductDto)p);
	}

	@Override
	public Mono<ProductDto> getProduct(String id) {
		return productRepository.findById(id).map(p -> productMapper.toProductDto(p)).map(p -> (ProductDto)p);
	}

	@Override
	public Flux<ProductDto> getProductInRange(double min, double max) {
		return productRepository.findByPriceBetween(Range.closed(min, max)).map(p -> productMapper.toProductDto(p)).map(p -> (ProductDto)p);
	}

	@Override
	public Mono<ProductDto> saveProduct(Mono<ProductDto> productDtoMono) {
		return productDtoMono.map(p -> productMapper.toProduct(p)).flatMap(productRepository::save).map(p -> productMapper.toProductDto(p));
	}

	@Override
	public Mono<ProductDto> updateProduct(Mono<ProductDto> productDtoMono, String id) {
		return productRepository.findById(id).flatMap(product -> productDtoMono.map(p -> productMapper.toProduct(p)))
				.doOnNext(e -> e.setId(id)).flatMap(productRepository::save).map(p -> productMapper.toProductDto(p));
	}

	@Override
	public Mono<Void> deleteProduct(String id) {
		return productRepository.deleteById(id);
	}
}
