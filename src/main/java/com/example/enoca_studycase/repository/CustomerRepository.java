package com.example.enoca_studycase.repository;

import com.example.enoca_studycase.entity.Customer;
import com.example.enoca_studycase.model.CustomerResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByNameContaining(String keyword);
}
