package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    public ResponseEntity<List<Customer>>ListAllCustomer(){
        List<Customer> customers= customerService.findAll();
        if (customers.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } return new ResponseEntity<>(customers,HttpStatus.OK);
    }

    @RequestMapping(value = "/customer/{id}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") Long id){
        System.out.println("Fetching Customer with id" + id);
        Customer customer= customerService.findById(id);
        if (customer==null){
            System.out.println("Customer with id "+ id+ "not found");
            return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Customer>(customer,HttpStatus.OK);
    }

    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    public ResponseEntity<Void>createCustomer(@RequestBody Customer customer, UriComponentsBuilder ucBuilder){
        System.out.println("Create Customer "+customer.getLastName());
        customerService.save(customer);
        HttpHeaders headers= new HttpHeaders();
        headers.setLocation(ucBuilder.path("/customer/{id}").buildAndExpand(customer.getId()).toUri());
        return new ResponseEntity<Void>(headers,HttpStatus.CREATED);
    }

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Customer>updateCustomer(@PathVariable("id")long id,@ResponseBody Customer customer){
        System.out.println("updating Customer "+id);

        Customer currentCustomer = customerService.findById(id);
        if (currentCustomer == null){
            System.out.println("Customer with id "+ id+" not found");
            return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
        }
        currentCustomer.setFirstName(customer.getFirstName());
        currentCustomer.setLastName(customer.getLastName());
        currentCustomer.setId(customer.getId());

        customerService.save(currentCustomer);
        return new ResponseEntity<Customer>(currentCustomer,HttpStatus.OK);
    }

    @RequestMapping(value = "/customer/id", method = RequestMethod.DELETE)
    public ResponseEntity<Customer>deleteCustomer(@PathVariable("id") Long id){
        System.out.println("Fetching & Deleting Customer with id "+id);
        Customer customer=customerService.findById(id);
        if (customer==null){
            System.out.println("Unable to delete.Customer with id "+ id + " not found");
            return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
        }customerService.remove(id);
        return new ResponseEntity<Customer>(HttpStatus.NO_CONTENT);
    }

}
