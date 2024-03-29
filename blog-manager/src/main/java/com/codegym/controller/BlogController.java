package com.codegym.controller;

import com.codegym.model.Blog;
import com.codegym.model.Category;
import com.codegym.service.BlogService;
import com.codegym.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class BlogController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private CategoryService categoryService;

    @ModelAttribute("category")
    public Iterable<Category> category(){
        return categoryService.findAll();
    }

    @GetMapping("/blog")
    public ModelAndView showListForm(@RequestParam("s") Optional<String>s, Pageable pageable){
        Page<Blog> blogs;
        if (s.isPresent()){
            blogs=blogService.findAllByTitleContaining(s.get(),pageable);
        }else {
            blogs=blogService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/blog/list");
        modelAndView.addObject("blog",blogs);
        return modelAndView;
    }

    @GetMapping("/create-blog")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView= new ModelAndView("/blog/create");
        modelAndView.addObject("blog",new Blog());
        return modelAndView;
    }

    @PostMapping("/create-blog")
    public ModelAndView saveCreate(@ModelAttribute("blog")Blog blog){
        blogService.save(blog);
            ModelAndView modelAndView= new ModelAndView("/blog/create");
            modelAndView.addObject("blog",new Blog());
            return modelAndView;
    }

    @GetMapping("/edit-blog/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){
        Blog blog= blogService.findById(id);
        if (blog != null){
            ModelAndView modelAndView= new ModelAndView("/blog/edit");
            modelAndView.addObject("blog", blog);
            return modelAndView;
        }else {
            ModelAndView modelAndView= new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/edit-blog")
    public ModelAndView saveEdit(@ModelAttribute("blog")Blog blog){
        blogService.save(blog);
        ModelAndView modelAndView= new ModelAndView("/blog/edit");
        modelAndView.addObject("blog",blog);
        return modelAndView;
    }

    @GetMapping("/delete-blog/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        Blog blog= blogService.findById(id);
        if (blog != null){
            ModelAndView modelAndView= new ModelAndView("/blog/delete");
            modelAndView.addObject("blog",blog);
            return modelAndView;
        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/delete-blog")
    public String deleteBlog(@ModelAttribute("blog")Blog blog){
        blogService.remove(blog.getId());
        return "redirect:blog";
    }

    @GetMapping("/")
    public String home(){
        return "index";
    }
}
