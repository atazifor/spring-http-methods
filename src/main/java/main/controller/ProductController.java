package main.controller;

import main.model.Product;
import main.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public String viewProducts(Model page) {
        page.addAttribute("products", productService.findAll());
        return "products.html";
    }
    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public String addProduct(@RequestParam String name, @RequestParam double price, Model page) {
        Product p = new Product();
        p.setName(name);
        p.setPrice(price);
        productService.addProduct(p);
        page.addAttribute("products",productService.findAll());
        return "products.html";
    }
}
