package com.guizbert.thymeleaf.controller;


import com.guizbert.thymeleaf.model.Product;
import com.guizbert.thymeleaf.model.ProductType;
import com.guizbert.thymeleaf.repository.ProductRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ProductController {


    private final ProductRepository productRepository;


    @GetMapping("/")
    public String index (Model model)
    {
        model.addAttribute("products", productRepository.findAll());
        return "index";
    }

    @GetMapping("/product/new")
    public String newProduct(Model model)
    {
        model.addAttribute("product",new Product());
        model.addAttribute("types", ProductType.values());

        return "newProduct";
    }


    @PostMapping("/product")
    public String saveProduct(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("types", ProductType.values());
            return "newProduct";
        }
        productRepository.save(product);
        return "redirect:/";
    }

    @GetMapping("/product/edit/{id}")
    public String editProduct(@PathVariable Long id, Model model)
    {
        Product p = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("wrong id"));
        model.addAttribute("product", p);
        model.addAttribute("types", ProductType.values());

        return "editProduct";
    }

    @PostMapping("/product/{id}")
    public String updateProduct(@PathVariable Long id, @Valid @ModelAttribute("product") Product product, BindingResult bindingResult, Model model)
    {
        if (bindingResult.hasErrors()) {
            model.addAttribute("types", ProductType.values());
            return "newProduct";
        }
        productRepository.save(product);
        return "redirect:/";
    }

    @GetMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable Long id, Model model)
    {
        productRepository.deleteById(id);
        return "redirect:/";
    }
}
