package com.example.enoca_studycase.controller;

import com.example.enoca_studycase.common.constant.Constant;
import com.example.enoca_studycase.entity.Customer;
import com.example.enoca_studycase.model.CustomerResponse;
import com.example.enoca_studycase.model.Response;
import com.example.enoca_studycase.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@Slf4j
public class CustomerController extends BaseController{

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<Response<List<Customer>>> findAllCustomers(){

        try {
            log.info("Retrieve all customers method has started!");
            return new ResponseEntity<>(respond(customerService.findAllCustomer(),
                    Constant.SUCCEED_CODE, "Retrieve all customers succeeded!"), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error occurred: " + e);
            return new ResponseEntity<>(respond(Constant.TECH_EXP_CODE,
                    "Retrieve all customers  failed!"), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Response> findById(@PathVariable("id") Long id){
        try {
            log.info("Find customer method has started!");
            return new ResponseEntity<>(respond(customerService.findById(id),
                    Constant.SUCCEED_CODE, "Finding customer succeeded!"), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error occurred: " + e);
            return new ResponseEntity<>(respond(Constant.TECH_EXP_CODE,
                    "Finding customer failed!"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<Response<Customer>> saveCustomer(@RequestBody Customer customer){
        try {
            log.info("Create customer method has started! Payload: {}", customer); //serialize payload to json
            return new ResponseEntity<>(respond(customerService.saveCustomer(customer),
                    Constant.SUCCEED_CODE, "Create customer succeeded!"), HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error occurred: " + e);
            return new ResponseEntity<>(respond(Constant.TECH_EXP_CODE,
                    "Creating customer failed!"),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<Response<Customer>> updateCustomer(@RequestBody Customer customer){
        try {
            log.info("Update customer method has started!");
            return new ResponseEntity<>(respond(customerService.updateCustomer(customer),
                    Constant.SUCCEED_CODE, "Updating customer succeeded!"), HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error occurred: " + e);
            return new ResponseEntity<>(respond(Constant.TECH_EXP_CODE,
                    "Updating customer failed!"), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<CustomerResponse>> deleteCustomer(@PathVariable("id") Long id){
        try {
            log.info("Delete customer method has started!");
            return new ResponseEntity<>(respond(customerService.deleteCustomer(id),
                    Constant.SUCCEED_CODE, "Deleting customer succeeded!"), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error occurred: " + e);
            return new ResponseEntity<>(respond(Constant.TECH_EXP_CODE,
                    "Deleting customer failed!"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<Response> filterCustomerName(@PathVariable String keyword){
        try {
            log.info("Filter customers via names method has started!");
            return new ResponseEntity<>(respond(customerService.filterCustomerName(keyword),
                    Constant.SUCCEED_CODE, "Filtering customers via names succeeded!"), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error occurred: " + e);
            return new ResponseEntity<>(respond(Constant.TECH_EXP_CODE,
                    "Filtering customers via names failed!"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/without-orders")
    public ResponseEntity<Response> getCustomersWithoutOrders(){
        try {
            log.info("Filter customers without orders method has started!");
            return new ResponseEntity<>(respond(customerService.getCustomersWithoutOrders(),
                    Constant.SUCCEED_CODE, "Filtering customers without orders succeeded!"), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error occurred: " + e);
            return new ResponseEntity<>(respond(Constant.TECH_EXP_CODE,
                    "Filtering customers without orders failed!"), HttpStatus.BAD_REQUEST);
        }
    }
}
