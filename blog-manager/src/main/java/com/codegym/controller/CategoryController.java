package com.codegym.controller;

import com.codegym.model.Category;
import com.codegym.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.rmi.MarshalledObject;

@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category")
    public ModelAndView showListForm(){
        Iterable<Category> categories= categoryService.findAll();
        ModelAndView modelAndView= new ModelAndView("/category/list");
        modelAndView.addObject("category",categories);
        return modelAndView;
    }

    @GetMapping("/create-category")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView= new ModelAndView("/category/create");
        modelAndView.addObject("category",new Category());
        return modelAndView;
    }

    @PostMapping("/create-category")
    public ModelAndView saveCreate(@ModelAttribute("category")Category category){
        categoryService.save(category);
        ModelAndView modelAndView= new ModelAndView("/category/create");
        modelAndView.addObject("category",category);
        return modelAndView;
    }

    @GetMapping("/edit-category/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){
        Category category = categoryService.findById(id);
        ModelAndView modelAndView= new ModelAndView("/category/edit");
        modelAndView.addObject("category", category);
        return modelAndView;
    }

    @PostMapping("/edit-category")
    public ModelAndView saveEdit(@ModelAttribute("category")Category category){
        categoryService.save(category);
        ModelAndView modelAndView = new ModelAndView("/category/edit");
        modelAndView.addObject("category",category);
        return modelAndView;
    }

    @GetMapping("/delete-category/{id}")
    public ModelAndView showDeleteFrom(@PathVariable Long id){
        Category category = categoryService.findById(id);
        ModelAndView modelAndView= new ModelAndView("/category/delete");
        modelAndView.addObject("category",category);
        return modelAndView;
    }

    @PostMapping("/delete-category")
    public String deleteCategory(@ModelAttribute("category")Category category){
        categoryService.remove(category.getId());
        return "redirect:category";
    }



}
