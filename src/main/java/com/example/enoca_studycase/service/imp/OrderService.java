package com.example.enoca_studycase.service.imp;

import com.example.enoca_studycase.entity.Customer;
import com.example.enoca_studycase.entity.Order;
import com.example.enoca_studycase.model.CustomerResponse;
import com.example.enoca_studycase.model.OrderRequest;
import com.example.enoca_studycase.repository.CustomerRepository;
import com.example.enoca_studycase.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class OrderService implements com.example.enoca_studycase.service.OrderService {
    private final OrderRepository orderRepository;
    private final CustomerService customerService;

    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository, CustomerService customerService) {
        this.orderRepository = orderRepository;
        this.customerService = customerService;
    }

    @Override
    public List<Order> findAllOrder() {
        List<Order> all = null;
        try {
            all = orderRepository.findAll();
        } catch (Exception e) {
            log.info("Orders could not be listed!!");
        }
        return all;
    }

    @Override
    public Order findById(Long id) {
        Optional<Order> Order = orderRepository.findById(id);
        return Order.orElse(null);
    }

    public List<Order> findByCustomerId(Long id) {
        Customer customer = customerService.findById(id);
        List<Order> ordersByCustomer = orderRepository.findByCustomer(customer);
        return ordersByCustomer;
    }

    @Override
    public Order saveOrder(OrderRequest request) {
        Order newOrder = new Order();
        Customer customer = customerService.findById(request.getCustomerId());

        if (ObjectUtils.isEmpty(customer))
            throw new RuntimeException("The customer doesnt exist!");
        if (ObjectUtils.isEmpty(request.getDate()))
            newOrder.setDate(Date.from(OffsetDateTime.now().toInstant()));
        else newOrder.setDate(request.getDate());

        newOrder.setCustomer(customer);
        newOrder.setTotalPrice(request.getTotalPrice());

        return orderRepository.save(newOrder);
    }

    @Override
    public Order updateOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public CustomerResponse deleteOrder(Long id) {
        CustomerResponse response = new CustomerResponse();
        orderRepository.deleteById(id);
        response.setCustomer_id(id);
        response.setMessage("Order deleted successfully");
        return response;
    }


    @Override
    public List<Order> getOrdersAfterDate(Date date) {
        List<Order> allWithDateTimeBefore = orderRepository.findAllWithDateTimeBefore(date);
        return allWithDateTimeBefore;
    }
}
