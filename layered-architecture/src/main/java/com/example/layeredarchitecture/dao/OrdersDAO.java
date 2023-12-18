package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.model.ItemDTO;
import com.example.layeredarchitecture.model.OrderDTO;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface OrdersDAO extends CrudDAO<OrdersDAO>{
     String generateOrderId() throws SQLException, ClassNotFoundException;

     boolean insertValueToOrders(String id, LocalDate date, String Cid) throws SQLException, ClassNotFoundException;

     void getOrderId(String orderId) throws SQLException, ClassNotFoundException;


}
