package com.codegym.repository;

import com.codegym.model.Student;

import java.util.List;

public interface Repository<S> {

    List<Student> findAll();

    Student findById (Long id);

    void save (Student model);

    void remove(Long id);
}
