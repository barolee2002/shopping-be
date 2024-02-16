package com.example.taskmanager.service.impl;

import com.example.taskmanager.dto.BasePage;
import com.example.taskmanager.dto.ProductDto;
import com.example.taskmanager.dto.RateDto;
import com.example.taskmanager.entity.Product;
import com.example.taskmanager.entity.Rate;
import com.example.taskmanager.repository.ProductRepository;
import com.example.taskmanager.repository.RateRepository;
import com.example.taskmanager.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final RateRepository rateRepository;
    private final ModelMapper mapper = new ModelMapper();
    @Override
    public ProductDto save(ProductDto dto) {
        Product product = mapper.map(dto, Product.class);
        product = productRepository.save(product);
        Rate rate = mapper.map(dto.getRating(), Rate.class);
        rate.setProductId(product.getId());
        rate = rateRepository.save(rate);
        ProductDto productDto = mapper.map(product,ProductDto.class);
        productDto.setRating(mapper.map(rate, RateDto.class));
        return productDto;
    }
    @Override
    public ProductDto getById(Long productId) {
        Product product = productRepository.findById(productId).get();
        Rate rate = rateRepository.findByProductId(productId);
        ProductDto productDto = mapper.map(product,ProductDto.class);
        productDto.setRating(mapper.map(rate, RateDto.class));
        return productDto;
    }
    @Override
    public BasePage<ProductDto> getAll(String searchString, int page, int pageSize) {
        List<ProductDto> response = new ArrayList<ProductDto>();
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<Product> tasks = productRepository.getAllProduct(searchString, pageable);
        BasePage<ProductDto> dataPage = new BasePage<>();
        dataPage.setTotalElements(tasks.getTotalElements());
        dataPage.setTotalPages(tasks.getTotalPages());
        dataPage.setElements(tasks.getNumberOfElements());
        dataPage.setData(tasks.stream().map(product -> {
            return getById(product.getId());
        }).collect(Collectors.toList()));
        return dataPage;
    }
    @Override
    public List<ProductDto> getBycategory(String category) {
        List<Product> products = productRepository.findByCategory(category);
        List<ProductDto> dtos = new ArrayList<ProductDto>();
        dtos = products.stream().map(product -> {
            return getById(product.getId());
        }).toList();
        return dtos;
    }
}
