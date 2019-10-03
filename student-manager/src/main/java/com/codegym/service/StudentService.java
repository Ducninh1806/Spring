package com.codegym.service;

import com.codegym.model.Student;

import java.util.List;

public interface StudentService {
    Iterable<Student> findAll();

    Student findById(Long id);

    void save(Student student);

    void remove(Long id);
}
