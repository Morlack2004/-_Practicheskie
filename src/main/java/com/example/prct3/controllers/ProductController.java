package com.example.prct3.controllers;

import com.example.prct3.models.Product;
import com.example.prct3.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ProductController {

    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/goToProductList")
    public String goToProductList(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "products/product-list"; // переход на страницу с продуктами
    }

    @GetMapping("/addproduct")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "products/add-product";
    }

    @PostMapping("/addproduct")
    public String addProduct(@Valid Product product, Model model) {
        productRepository.save(product);
        return "redirect:/goToProductList";
    }

    @GetMapping("/product/details/{id}")
    public String showProductDetails(@PathVariable("id") long id, Model model) {
        Product product = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
        model.addAttribute("product", product);
        return "products/product-details";
    }

    @GetMapping("/product/edit/{id}")
    public String showUpdateProductForm(@PathVariable("id") long id, Model model) {
        Product product = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
        model.addAttribute("product", product);
        return "products/update-product";
    }

    @PostMapping("/product/update/{id}")
    public String updateProduct(@PathVariable("id") long id, @Valid Product product, Model model) {
        product.setId(id);
        productRepository.save(product);
        return "redirect:/goToProductList";
    }

    @GetMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable("id") long id, Model model) {
        Product product = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
        productRepository.delete(product);
        return "redirect:/goToProductList";
    }
}
