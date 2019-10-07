package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.model.Province;
import com.codegym.service.CustomerService;
import com.codegym.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class CustormerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProvinceService provinceService;

    @ModelAttribute("province")
    public Iterable<Province> provinces(){
        return provinceService.findAll();
    }


    @GetMapping("/customer")
    public ModelAndView showListForm(@RequestParam("s") Optional<String>s, Pageable pageable){
        Page<Customer> customer ;
        if (s.isPresent()){
            customer=customerService.findAllByFirstNameContaining(s.get(),pageable);
        }else {
            customer=customerService.findAll(pageable);
        }
        ModelAndView modelAndView= new ModelAndView("/customer/list");
        modelAndView.addObject("customer", customer);
        return modelAndView;
    }

    @GetMapping("/create-customer")
    public ModelAndView showCreatForm(){
        ModelAndView modelAndView= new ModelAndView("/customer/create");
        modelAndView.addObject("customer",new Customer());
        return modelAndView;
    }

    @PostMapping("/create-customer")
    public ModelAndView saveCreate(@ModelAttribute("customer") Customer customer){
        customerService.save(customer);
        ModelAndView modelAndView = new ModelAndView("/customer/create");
        modelAndView.addObject("customer",customer);
        return modelAndView;
    }

    @GetMapping("/edit-customer/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){
        Customer customer=customerService.findById(id);
        if (customer!= null){
            ModelAndView modelAndView = new ModelAndView("/customer/edit");
            modelAndView.addObject("customer",customer);
            return modelAndView;
        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/edit-customer")
    public ModelAndView saveEdit(@ModelAttribute("customer") Customer customer){
        customerService.save(customer);
        ModelAndView modelAndView= new ModelAndView("/customer/edit");
        modelAndView.addObject("customer",customer);
        return modelAndView;
    }

    @GetMapping("/delete-customer/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        Customer customer= customerService.findById(id);
        ModelAndView modelAndView= new ModelAndView("/customer/delete");
        modelAndView.addObject("customer",customer);
        return modelAndView;
    }

    @PostMapping("/delete-customer")
    public String saveDelete(@ModelAttribute("customer") Customer customer){
        customerService.remove(customer.getId());
        return "redirect:customer";
    }


}
