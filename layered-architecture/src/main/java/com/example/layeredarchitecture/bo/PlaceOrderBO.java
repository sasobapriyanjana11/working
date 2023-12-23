package com.example.layeredarchitecture.bo;

import com.example.layeredarchitecture.dao.custom.CustomerDAO;
import com.example.layeredarchitecture.dao.custom.Impl.CustomerDAOImpl;
import com.example.layeredarchitecture.dao.custom.Impl.ItemDAOImpl;
import com.example.layeredarchitecture.dao.custom.Impl.OrdersDAOImpl;
import com.example.layeredarchitecture.dao.custom.ItemDAO;
import com.example.layeredarchitecture.dao.custom.OrdersDAO;
import com.example.layeredarchitecture.dao.sqlUtil;
import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.OrderDetailDTO;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface PlaceOrderBO extends SuperBO{

    public boolean saveOrder(String orderId, LocalDate orderDate, String customerId, List<OrderDetailDTO> orderDetails) throws SQLException ;

       public String generateOrderId() throws SQLException, ClassNotFoundException ;


        //transaction part

        public boolean insertValueToOrders(String oid, LocalDate date, String Cid) throws SQLException, ClassNotFoundException ;



        public void getOrderId(String orderId) throws SQLException, ClassNotFoundException ;


        public ArrayList<OrdersDAO> getAll() throws SQLException, ClassNotFoundException ;



        public boolean save(OrdersDAO dto) throws SQLException, ClassNotFoundException ;



        public boolean update(OrdersDAO dto) throws SQLException, ClassNotFoundException ;



        public boolean exit(String id) throws SQLException, ClassNotFoundException ;



        public boolean delete(String id) throws SQLException, ClassNotFoundException ;



        public ResultSet generateNewId() throws SQLException, ClassNotFoundException ;



        public OrdersDAO search(String id) throws SQLException, ClassNotFoundException ;



        public ArrayList<OrdersDAO> loadAll() throws SQLException, ClassNotFoundException ;


        public boolean insertValueToOrderDetail(String orderId, OrderDetailDTO dto) throws SQLException, ClassNotFoundException;
}
