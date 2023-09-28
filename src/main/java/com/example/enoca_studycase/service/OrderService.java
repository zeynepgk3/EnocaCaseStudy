package com.example.enoca_studycase.service;

import com.example.enoca_studycase.entity.Customer;
import com.example.enoca_studycase.entity.Order;
import com.example.enoca_studycase.model.CustomerResponse;
import com.example.enoca_studycase.model.OrderRequest;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;

@Service
public interface OrderService {
    List<Order> findAllOrder();
    Order findById(Long id);
    Order saveOrder(OrderRequest request);
    Order updateOrder(Order customer);
    CustomerResponse deleteOrder(Long id);

    List<Order> getOrdersAfterDate(Date date);
}
