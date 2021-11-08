package br.com.destack360.br.com.destack360.controller;


import br.com.destack360.br.com.destack360.entity.Product;
import br.com.destack360.br.com.destack360.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/products")
    public String saveProduct(@RequestBody Product product) throws ExecutionException, InterruptedException {

        return productService.saveProduct(product);
    }


    @GetMapping("/products/{name}")
    public Product getProductsDetailsByName(@PathVariable String name) throws ExecutionException, InterruptedException {
        return productService.getProductsDetailsByName(name);
    }

    @PutMapping("/products")
    public String updateProducts(@RequestBody Product product) throws ExecutionException, InterruptedException {

        return productService.updateProducts(product);
    }

    @GetMapping("/products")
    public List<Product> getProductsAllDetails() throws ExecutionException, InterruptedException {
        return productService.getProductsAllDetails();
    }

}
