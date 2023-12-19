package com.example.layeredarchitecture.dao.custom.Impl;

import com.example.layeredarchitecture.dao.custom.OrdersDAO;
import com.example.layeredarchitecture.dao.sqlUtil;
import com.example.layeredarchitecture.db.DBConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class OrdersDAOImpl implements OrdersDAO {
    private static Connection connection;

    static {
        try {
            connection = DBConnection.getDbConnection().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public String generateOrderId() throws SQLException, ClassNotFoundException {
      /*  Connection connection = DBConnection.getDbConnection().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("SELECT oid FROM `Orders` ORDER BY oid DESC LIMIT 1;");*/

        ResultSet rst= sqlUtil.execute("SELECT oid FROM `Orders` ORDER BY oid DESC LIMIT 1;");
        return rst.next() ? String.format("OID-%03d", (Integer.parseInt(rst.getString("oid").replace("OID-", "")) + 1)) : "OID-001";

    }

    //transaction part
    @Override
    public boolean insertValueToOrders(String oid,LocalDate date, String Cid) throws SQLException, ClassNotFoundException {

        connection.setAutoCommit(false);
        PreparedStatement  stm = connection.prepareStatement("INSERT INTO `Orders` (oid, date, customerID) VALUES (?,?,?)");
        stm.setString(1, oid);
        stm.setDate(2, Date.valueOf(date));
        stm.setString(3, Cid);

        if (stm.executeUpdate() != 1) {
            connection.rollback();
            connection.setAutoCommit(true);
            return false;
        }
        return true;

    }
    @Override
    public void getOrderId(String orderId) throws SQLException, ClassNotFoundException {
      /* Connection  connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement stm = connection.prepareStatement("SELECT oid FROM `Orders` WHERE oid=?");
        stm.setString(1, orderId);
        stm.executeQuery().next();
        */

        sqlUtil.execute("SELECT oid FROM `Orders` WHERE oid=?",orderId);
    }

    @Override
    public ArrayList<OrdersDAO> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(OrdersDAO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(OrdersDAO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean exit(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ResultSet generateNewId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public OrdersDAO search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<OrdersDAO> loadAll() throws SQLException, ClassNotFoundException {
        return null;
    }

}
