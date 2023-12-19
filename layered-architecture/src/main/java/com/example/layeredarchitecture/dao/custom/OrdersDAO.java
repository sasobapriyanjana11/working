package com.example.layeredarchitecture.dao.custom;

import com.example.layeredarchitecture.dao.CrudDAO;

import java.sql.SQLException;
import java.time.LocalDate;

public interface OrdersDAO extends CrudDAO<OrdersDAO> {
     String generateOrderId() throws SQLException, ClassNotFoundException;

     boolean insertValueToOrders(String id, LocalDate date, String Cid) throws SQLException, ClassNotFoundException;

     void getOrderId(String orderId) throws SQLException, ClassNotFoundException;


}
