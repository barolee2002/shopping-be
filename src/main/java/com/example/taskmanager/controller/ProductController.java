package com.example.taskmanager.controller;

import com.example.taskmanager.dto.BasePage;
import com.example.taskmanager.dto.ProductDto;
import com.example.taskmanager.dto.Response;
import com.example.taskmanager.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    @PostMapping("/creating")
    public Response<ProductDto> save(@RequestBody ProductDto dto) {
        return new Response<>(HttpStatus.CREATED.value(),productService.save(dto));
    }
    @GetMapping("/get-all")
    public Response<BasePage<ProductDto>> getAll (
            @RequestParam(required = false, defaultValue = "") String searchString,
            @RequestParam( required = false, defaultValue = "1") Integer page,
            @RequestParam( required = false, defaultValue = "10") Integer pageSize

    ) {
        return  new Response<>(HttpStatus.OK.value(), productService.getAll(searchString, page, pageSize));
    }
    @GetMapping("/{productId}")
    public Response<ProductDto> getDetail (@PathVariable Long productId) {
        return new Response<>(HttpStatus.OK.value(), productService.getById(productId));
    }
    @GetMapping("/same-product/{category}")
    public Response<List<ProductDto>> getSameProduct (@PathVariable String category) {
        return new Response<>(HttpStatus.OK.value(), productService.getBycategory(category));
    }

}
