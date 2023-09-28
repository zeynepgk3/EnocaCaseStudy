package com.example.enoca_studycase.repository;

import com.example.enoca_studycase.entity.Customer;
import com.example.enoca_studycase.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select a from Order a where a.date >= :date")
    List<Order> findAllWithDateTimeBefore(
            @Param("date") Date date);

    List<Order> findByCustomer(Customer customer);
}
