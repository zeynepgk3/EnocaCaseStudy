package com.example.enoca_studycase.model;

import lombok.Data;

import java.time.OffsetDateTime;
import java.util.Date;

@Data
public class OrderRequest {
    private Date date;
    private Long customerId;
    private Double totalPrice;
}
