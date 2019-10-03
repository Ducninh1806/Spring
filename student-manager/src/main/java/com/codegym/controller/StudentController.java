package com.codegym.controller;

import com.codegym.model.Student;
import com.codegym.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/create-student")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("/student/create");
        modelAndView.addObject("student",new Student());
        return modelAndView;
    }

    @PostMapping("/create-student")
    public ModelAndView saveCustomer(@ModelAttribute("student") Student student){
        studentService.save(student);

        ModelAndView modelAndView = new ModelAndView("/student/create");
        modelAndView.addObject("student", new Student());
        modelAndView.addObject("message", "New student created successfully");
        return modelAndView;
    }

    @GetMapping("/student")
    public ModelAndView listStudent(){
        List<Student> students = studentService.findAll();
        ModelAndView modelAndView = new ModelAndView("/student/list");
        modelAndView.addObject("students", students);
        return modelAndView;
    }

    @GetMapping("/edit-student/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){
        Student student = studentService.findById(id);
        if(student != null) {
            ModelAndView modelAndView = new ModelAndView("/student/edit");
            modelAndView.addObject("student", student);
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/edit-student")
    public ModelAndView updateStudent(@ModelAttribute("student") Student student){
        studentService.save(student);
        ModelAndView modelAndView = new ModelAndView("/student/edit");
        modelAndView.addObject("student", student);
        modelAndView.addObject("message", "Student updated successfully");
        return modelAndView;
    }

    @GetMapping("/delete-student/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        Student student = studentService.findById(id);
        if(student != null) {
            ModelAndView modelAndView = new ModelAndView("/student/delete");
            modelAndView.addObject("student", student);
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/delete-student")
    public String deleteStudent(@ModelAttribute("student") Student student){
        studentService.remove(student.getId());
        return "redirect:student";
    }
}
