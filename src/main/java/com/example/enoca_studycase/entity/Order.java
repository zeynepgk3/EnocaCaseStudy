package com.example.enoca_studycase.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.OffsetDateTime;
import java.util.Date;

@Entity
@Data
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date date;

    private double totalPrice;

    @NotNull
    @ManyToOne
    private Customer customer;

}
