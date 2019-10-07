package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.model.Province;
import com.codegym.service.CustomerService;
import com.codegym.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProvinceController {

    @Autowired
    private ProvinceService provinceService;

    @Autowired
    private CustomerService customerService;

    @GetMapping("/view-province/{id}")
    public ModelAndView viewProvince(@PathVariable("id")Long id){
        Province province = provinceService.findById(id);
        if (province==null){
            return new ModelAndView("/error.404");
        }

        Iterable<Customer> customers= customerService.findAllProvince(province);

        ModelAndView modelAndView = new ModelAndView("/province/view");
        modelAndView.addObject("province",province);
        modelAndView.addObject("customer",customers);
        return modelAndView;
    }

    @GetMapping("/province")
    public ModelAndView showListForm(){
        Iterable<Province> provinces= provinceService.findAll();
        ModelAndView modelAndView= new ModelAndView("/province/list");
        modelAndView.addObject("province",provinces);
        return modelAndView;
    }

    @GetMapping("/create-province")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView= new ModelAndView("/province/create");
        modelAndView.addObject("province", new Province());
        return modelAndView;
    }

    @PostMapping("/create-province")
    public ModelAndView saveCreate(@ModelAttribute("province") Province province){
        provinceService.save(province);
        ModelAndView modelAndView= new ModelAndView("/province/create");
        modelAndView.addObject("/province",province);
        return modelAndView;
    }

    @GetMapping("/edit-province/{id}")
    public ModelAndView showEditForm (@PathVariable Long id){
        Province province = provinceService.findById(id);
        if (province!= null){
            ModelAndView modelAndView = new ModelAndView("/province/edit");
            modelAndView.addObject("province",province);
            return modelAndView;
        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/edit-province")
    public ModelAndView saveEdit(@ModelAttribute("province")Province province){
        provinceService.save(province);
        ModelAndView modelAndView = new ModelAndView("/province/edit");
        modelAndView.addObject("province",province);
        return modelAndView;
    }

    @GetMapping("/delete-province/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        Province province = provinceService.findById(id);
        if (province != null){
            ModelAndView modelAndView = new ModelAndView("/province/delete");
            modelAndView.addObject("province",province);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/delete-province")
    public String saveDelete(@ModelAttribute("province") Province province){
        provinceService.remove(province.getId());
        return "redirect:province";
    }

}
