package com.pattern.di;

public class CustomerService {
    private CustomerRepository customerRepository;

    // Constructor Injection
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void getCustomerInfo(int id) {
        String info = customerRepository.findCustomerById(id);
        System.out.println("Fetched: " + info);
    }
}
