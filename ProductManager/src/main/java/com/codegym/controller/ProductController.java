package com.codegym.controller;

import com.codegym.model.Product;
import com.codegym.service.ProductService;
import com.codegym.service.ProductServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProductController {

    private ProductService productService =new ProductServiceImpl();

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("products",productService.findAll());
        return "index";
    }

    @GetMapping("/product/create")
    public String create(Model model){
        model.addAttribute("products",new Product());
        return "create";
    }

    @PostMapping("/product/save")
    public String save(Product product,RedirectAttributes redirect){
        product.setId((int)(Math.random()*1000));
        productService.save(product);
        redirect.addFlashAttribute("success", "Saved product successfully");
        return "redirect:/";
    }

    @GetMapping("/product/{id}/edit")
    public String edit(@PathVariable int id,Model model){
        model.addAttribute("products",productService.findById(id));
        return "edit";
    }

    @PostMapping("/product/update")
    public String update(Product product, RedirectAttributes redirect){
        productService.update(product.getId(),product);
        redirect.addFlashAttribute("success","Modify product successfully");
        return "redirect:/";
    }

    @GetMapping("/product/{id}/delete")
    public String delete(@PathVariable int id, Model model){
        model.addAttribute("products",productService.findById(id));
        return "delete";
    }

    @PostMapping("/product/delete")
    public String delete(Product product, RedirectAttributes redirect) {
        productService.remove(product.getId());
        redirect.addFlashAttribute("success", "Remove product successfully");
        return "redirect:/";
    }

    @GetMapping("/product/{id}/edit")
    public String view(@PathVariable int id, Model model){
        model.addAttribute("products",productService.findById(id));
        return "view";
    }

}
