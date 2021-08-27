package com.nisum.poc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nisum.poc.dto.ProductDto;
import com.nisum.poc.service.impl.ProductServiceImpl;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
@Validated
public class ProductController {

	@Autowired
    private ProductServiceImpl productService;

    @GetMapping
    public ResponseEntity<Flux<ProductDto>> getProducts() {
        return new ResponseEntity<>(productService.getProducts(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mono<ProductDto>> getProduct(@PathVariable String id) {
        return new ResponseEntity<>(productService.getProduct(id), HttpStatus.OK);
    }

    @GetMapping("/product-range")
    public ResponseEntity<Flux<ProductDto>> getProductBetweenRange(@RequestParam("min") double min, @RequestParam("max") double max) {
        return new ResponseEntity<>(productService.getProductInRange(min, max), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Mono<ProductDto>> saveProduct(@RequestBody Mono<ProductDto> productDtoMono) {
        return new ResponseEntity<>(productService.saveProduct(productDtoMono), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Mono<ProductDto>> updateProduct(@RequestBody  Mono<ProductDto> productDtoMono, @PathVariable String id) {
        return new ResponseEntity<>(productService.updateProduct(productDtoMono, id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Mono<Void>> deleteProduct(@PathVariable String id) {
        return new ResponseEntity<>(productService.deleteProduct(id), HttpStatus.OK);
    }
}
