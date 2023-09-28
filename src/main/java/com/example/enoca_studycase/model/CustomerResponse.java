package com.example.enoca_studycase.model;

import com.example.enoca_studycase.entity.Customer;
import com.example.enoca_studycase.entity.Order;
import lombok.Data;

import java.util.List;

@Data
public class CustomerResponse {
    Long customer_id;
    Customer customer;
    private List<Order> orders;

    String message;
}
