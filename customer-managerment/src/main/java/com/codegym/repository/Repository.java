package com.codegym.repository;

import com.codegym.model.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface Repository<C> {

    List<Customer> findAll();

    Customer findById(Long id);

    void save(Customer customer);

    void remove(Long id);



}
