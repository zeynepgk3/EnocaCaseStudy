package com.example.enoca_studycase.service.imp;

import com.example.enoca_studycase.entity.Customer;
import com.example.enoca_studycase.entity.Order;
import com.example.enoca_studycase.model.CustomerResponse;
import com.example.enoca_studycase.repository.CustomerRepository;
import com.example.enoca_studycase.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CustomerService implements com.example.enoca_studycase.service.CustomerService {
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;

    public CustomerService(CustomerRepository customerRepository, OrderRepository orderRepository) {
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Customer> findAllCustomer() {
        List<Customer> all = null;
        try {
            all = customerRepository.findAll();
        } catch (Exception e) {
            log.info("Customers could not be listed!!");
        }
        return all;
    }

    @Override
    public Customer findById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.orElse(null);
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public CustomerResponse deleteCustomer(Long id) {
        CustomerResponse response = new CustomerResponse();
        customerRepository.deleteById(id);
        response.setCustomer_id(id);
        response.setMessage("Customer deleted successfully");
        return response;
    }

    @Override
    public List<Customer> filterCustomerName(String keyword) {
        List<Customer> customers = customerRepository.findByNameContaining(keyword);
        return customers;
    }

    @Override
    public List<Customer> getCustomersWithoutOrders() {
        List<Customer> customers = customerRepository.findAll();
        List<Customer> customersWithoutOrders = new ArrayList<>();

        for (Customer customer : customers) {
            List<Order> orders = orderRepository.findByCustomer(customer);
            if (orders.isEmpty()) {
                customersWithoutOrders.add(customer);
            }
        }
        return customersWithoutOrders;
    }
}
