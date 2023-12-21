package com.example.layeredarchitecture.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CustomDto {
    private String id;
    private String name;
    private String orderId;
    private LocalDate orderDate;
 //   private String customerId;
 //   private String customerName;
    private BigDecimal orderTotal;

    public CustomDto( String id, String name, String orderId, LocalDate orderDate,   BigDecimal orderTotal) {
        this.id = id;
        this.name = name;
        this.orderId = orderId;
        this.orderDate = orderDate;
      //  this.customerId = customerId;
      //  this.customerName = customerName;
        this.orderTotal = orderTotal;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

   /* public String getCustomerId() {
        return customerId;
    }*/

   /* public void setCustomerId(String customerId) {
        this.customerId = customerId;

    }*/

   /* public String getCustomerName() {
        return customerName;
    }*/

   /* public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }*/

    public BigDecimal getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(BigDecimal orderTotal) {
        this.orderTotal = orderTotal;
    }

    @Override
    public String toString(){
        return "CustomDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", order Id='" + orderId + '\'' +
                ",date ="+orderDate+'\''+
                "Total ="+orderTotal+'\''+
                '}';
    }
}

