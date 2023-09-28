package com.example.enoca_studycase.controller;

import com.example.enoca_studycase.common.constant.Constant;
import com.example.enoca_studycase.entity.Order;
import com.example.enoca_studycase.model.CustomerResponse;
import com.example.enoca_studycase.model.OrderRequest;
import com.example.enoca_studycase.model.Response;
import com.example.enoca_studycase.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController extends BaseController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<Order> findAllOrders(){
        List<Order> allOrders = orderService.findAllOrder();

        if (!ObjectUtils.isEmpty(allOrders)) {
            return allOrders;
        }
        return null;
    }


    @GetMapping("/{id}")
    public Order findById(@PathVariable("id") Long id){
        return orderService.findById(id);
    }

    @PostMapping
    public ResponseEntity<Response> saveOrder(@RequestBody OrderRequest request){
        try {
            log.info("Save order method has started!");
            return new ResponseEntity<>(respond(orderService.saveOrder(request),
                    Constant.SUCCEED_CODE, "Saving order succeeded!"), HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error occurred: " + e);
            return new ResponseEntity<>(respond(Constant.TECH_EXP_CODE,
                    "Saving order failed!"),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<Response> updateOrder(@RequestBody Order order){
        try {
            log.info("Update order has started!");
            return new ResponseEntity<>(respond(orderService.updateOrder(order),
                    Constant.SUCCEED_CODE, "Updating order succeeded!"), HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error occurred: " + e);
            return new ResponseEntity<>(respond(Constant.TECH_EXP_CODE,
                    "Updating order is failed!"), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<CustomerResponse>> deleteOrder(@PathVariable("id") Long id){
        try {
            log.info("Delete order has started!");
            return new ResponseEntity<>(respond(orderService.deleteOrder(id),
                    Constant.SUCCEED_CODE, "Deleting order succeeded!"), HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error occurred: " + e);
            return new ResponseEntity<>(respond(Constant.TECH_EXP_CODE,
                    "Deleting order is failed!"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<Response> getOrdersAfterDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        try {
            log.info("List order after the specific date has started!");
            return new ResponseEntity<>(respond(orderService
                    .getOrdersAfterDate(Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant())),
                    Constant.SUCCEED_CODE, "Listing order after the specific date is succeeded!"), HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error occurred: " + e);
            return new ResponseEntity<>(respond(Constant.TECH_EXP_CODE,
                    "Listing order after the specific date is failed!"), HttpStatus.BAD_REQUEST);
        }
    }
}
