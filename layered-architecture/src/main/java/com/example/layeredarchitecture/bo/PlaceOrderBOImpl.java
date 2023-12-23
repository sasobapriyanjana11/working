package com.example.layeredarchitecture.bo;

import com.example.layeredarchitecture.dao.DAOFactory;
import com.example.layeredarchitecture.dao.custom.*;
import com.example.layeredarchitecture.dao.custom.Impl.*;
import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.CustomDto;
import com.example.layeredarchitecture.model.ItemDTO;
import com.example.layeredarchitecture.model.OrderDetailDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PlaceOrderBOImpl implements PlaceOrderBO{
    CustomDto customDto;
     // CustomerDAO customerDao=new CustomerDAOImpl();
    // ItemDAO itemDao=new ItemDAOImpl();
    //OrdersDAO ordersDao=new OrdersDAOImpl();
    // QueryDAO queryDAO=new QueryDAOImpl();
    // OrderDetailsDAO orderDetailsDAO=new OrderDetailsDAOImpl();

    CustomerDAO customerDAO= (CustomerDAO) DAOFactory.getDaoFactory().getType(DAOFactory.getDaoType.CUSTOMER);
   ItemDAO itemDAO= (ItemDAO) DAOFactory.getDaoFactory().getType(DAOFactory.getDaoType.ITEM);

    OrdersDAO ordersDAO= (OrdersDAO)  DAOFactory.getDaoFactory().getType(DAOFactory.getDaoType.ORDERS);

    OrderDetailsDAO orderDetailsDAO= (OrderDetailsDAO) DAOFactory.getDaoFactory().getType(DAOFactory.getDaoType.ORDER_DETAILS);
    QueryDAO queryDAO= (QueryDAO) DAOFactory.getDaoFactory().getType(DAOFactory.getDaoType.QUERYDAO);


    @Override
    public boolean saveOrder(String orderId, LocalDate orderDate, String customerId, List<OrderDetailDTO> orderDetails) throws SQLException {
        /*Transaction*/
        Connection connection = null;
        boolean isOrderSaved;
        boolean isOrderDetailSaved = false;
        boolean isUpdated = false;
        try {
            connection = DBConnection.getDbConnection().getConnection();
            PreparedStatement stm = connection.prepareStatement("SELECT oid FROM `Orders` WHERE oid=?");
            stm.setString(1, orderId);

            /*if order id already exist*/
            if (stm.executeQuery().next()) {}

            ordersDAO.getOrderId(orderId);
            isOrderSaved = ordersDAO.insertValueToOrders(orderId, orderDate, customerId);

            connection.setAutoCommit(false);

           /* stm = connection.prepareStatement("INSERT INTO `Orders` (oid, date, customerID) VALUES (?,?,?)");
            stm.setString(1, orderId);
            stm.setDate(2, Date.valueOf(orderDate));
            stm.setString(3, customerId);

            //  int isInsertCDetails=ordersDAO.insertValueToOrders(orderDTO.getOrderId(),orderDTO.getOrderDate(),orderDTO.getCustomerId());

            //if (stm.executeUpdate() != 1)
            if (stm.executeUpdate() != 1) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }*/

            // stm = connection.prepareStatement("INSERT INTO OrderDetails (oid, itemCode, unitPrice, qty) VALUES (?,?,?,?)");

            for (OrderDetailDTO detail : orderDetails) {
              /*  stm.setString(1, orderId);
                stm.setString(2, detail.getItemCode());
                stm.setBigDecimal(3, detail.getUnitPrice());
                stm.setInt(4, detail.getQty());
                if (stm.executeUpdate() != 1){
                connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }
            */
                isOrderDetailSaved = orderDetailsDAO.insertValueToOrderDetail(orderId, detail);


//                //Search & Update Item

                ItemDTO item = findItem(detail.getItemCode());
                item.setQtyOnHand(item.getQtyOnHand() - detail.getQty());

                // ItemDTO item = findItem(detail.getItemCode());
                // item.setQtyOnHand(item.getQtyOnHand() - detail.getQty());

                isUpdated = itemDAO.update(item);



              /* PreparedStatement pstm = connection.prepareStatement("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?");
                pstm.setString(1, item.getDescription());
                pstm.setBigDecimal(2, item.getUnitPrice());
                pstm.setInt(3, item.getQtyOnHand());
                pstm.setString(4, item.getCode());

               // if(!(pstm.executeUpdate() > 0))

                if (!isItemUpdated) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;

                connection.commit();
                connection.setAutoCommit(true);
                return true;
             }*/
                System.out.println(isOrderDetailSaved);

                ////print custom dto/////

               ArrayList<CustomDto> join= queryDAO.customerOrderDetails(customDto);
                for (CustomDto dto : join) {
                    System.out.println(dto);
                }

                //////////////////////////

                if (isOrderSaved && isOrderDetailSaved && isUpdated) {
                    DBConnection.getDbConnection().getConnection().commit();
                    DBConnection.getDbConnection().getConnection().setAutoCommit(true);
                    return true;

                }
            }
        } catch(SQLException throwables){
            throwables.printStackTrace();
        } catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String generateOrderId() throws SQLException, ClassNotFoundException {
        return ordersDAO.generateOrderId();
    }

    @Override
    public boolean insertValueToOrders(String oid, LocalDate date, String Cid) throws SQLException, ClassNotFoundException {
        return ordersDAO.insertValueToOrders(oid,date,Cid);
    }

    @Override
    public void getOrderId(String orderId) throws SQLException, ClassNotFoundException {
       ordersDAO.generateOrderId();
    }

    @Override
    public ArrayList<OrdersDAO> getAll() throws SQLException, ClassNotFoundException {
        return ordersDAO.getAll();
    }

    @Override
    public boolean save(OrdersDAO dto) throws SQLException, ClassNotFoundException {
        return ordersDAO.save(dto);
    }

    @Override
    public boolean update(OrdersDAO dto) throws SQLException, ClassNotFoundException {
        return ordersDAO.update(dto);
    }

    @Override
    public boolean exit(String id) throws SQLException, ClassNotFoundException {
        return ordersDAO.exit(id);
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return ordersDAO.exit(id);
    }

    @Override
    public ResultSet generateNewId() throws SQLException, ClassNotFoundException {
        return ordersDAO.generateNewId();
    }

    @Override
    public OrdersDAO search(String id) throws SQLException, ClassNotFoundException {
        return ordersDAO.search(id);
    }

    @Override
    public ArrayList<OrdersDAO> loadAll() throws SQLException, ClassNotFoundException {
        return ordersDAO.loadAll();
    }

    @Override
    public boolean insertValueToOrderDetail(String orderId, OrderDetailDTO dto) throws SQLException, ClassNotFoundException {
        return orderDetailsDAO.insertValueToOrderDetail(orderId, dto);
    }

    public ItemDTO findItem(String code) {
        try {
          /*  Connection connection = DBConnection.getDbConnection().getConnection();
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Item WHERE code=?");
            pstm.setString(1, code);
            ResultSet rst = pstm.executeQuery();
            rst.next();
              return new ItemDTO(code, rst.getString("description"), rst.getBigDecimal("unitPrice"), rst.getInt("qtyOnHand"));*/

            ItemDTO itemDTO=  itemDAO.search(code);
            return new ItemDTO(code,itemDTO.getDescription(),itemDTO.getUnitPrice(),itemDTO.getQtyOnHand());

        } catch (SQLException e) {
            throw new RuntimeException("Failed to find the Item " + code, e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    }



