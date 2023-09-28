package com.example.enoca_studycase.service;

import com.example.enoca_studycase.entity.Customer;
import com.example.enoca_studycase.entity.Order;
import com.example.enoca_studycase.model.CustomerResponse;

import java.util.List;
import java.util.Map;

public interface CustomerService {
    List<Customer> findAllCustomer();
    Customer findById(Long id);
    Customer saveCustomer(Customer customer);
    Customer updateCustomer(Customer customer);
    CustomerResponse deleteCustomer(Long id);

    List<Customer> filterCustomerName(String keyword);
    List<Customer> getCustomersWithoutOrders();
}
