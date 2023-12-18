package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.CustomerDTO;

import java.sql.*;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {
  @Override
    public ArrayList<CustomerDTO> getAll() throws SQLException, ClassNotFoundException {
       /*Connection connection = DBConnection.getDbConnection().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = "SELECT * FROM Customer");*/

      ResultSet rst = sqlUtil.execute("SELECT * FROM Customer");
      ArrayList<CustomerDTO> allCustomer = new ArrayList<>();

      while (rst.next()) {
          CustomerDTO customerDTO = new CustomerDTO(
                  rst.getString("id"),
                  rst.getString("name"),
                  rst.getString("address"));
          allCustomer.add(customerDTO);
      }
      return allCustomer;

    }
    @Override
    public boolean save(CustomerDTO dto) throws SQLException, ClassNotFoundException {

       /* Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO Customer (id,name, address) VALUES (?,?,?)");
        pstm.setString(1, dto.getId());
        pstm.setString(2,dto.getName());
        pstm.setString(3, dto.getAddress());

        boolean isSaved=pstm.executeUpdate() > 0;
        return isSaved;*/

        return sqlUtil.execute("INSERT INTO Customer (id,name, address) VALUES (?,?,?)",dto.getId(),dto.getName(),dto.getAddress());
    }
    @Override

    public boolean update(CustomerDTO dto) throws SQLException, ClassNotFoundException {
       /* Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("UPDATE Customer SET name=?, address=? WHERE id=?");
        pstm.setString(1, dto.getName());
        pstm.setString(2, dto.getAddress());
        pstm.setString(3, dto.getId());
        pstm.executeUpdate();*/

  return sqlUtil.execute("UPDATE Customer SET name=?, address=? WHERE id=?",dto.getName(),dto.getAddress(),dto.getId());

    }
    @Override
    public boolean exit(String id) throws SQLException, ClassNotFoundException {
     /* Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT id FROM Customer WHERE id=?");
        pstm.setString(1,id );
        return pstm.executeQuery().next();*/

     ResultSet rst= sqlUtil.execute("SELECT id FROM Customer WHERE id=?",id);
     return rst.next();

    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
      /* Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("DELETE FROM Customer WHERE id=?");
        pstm.setString(1, id);
        boolean isDeleted= pstm.executeUpdate()>0;
       // pstm.executeUpdate();
        return  isDeleted;*/

       return  sqlUtil.execute("DELETE FROM Customer WHERE id=?",id);
    }
    @Override
    public ResultSet generateNewId() throws SQLException, ClassNotFoundException {
     /* Connection connection = DBConnection.getDbConnection().getConnection();
        ResultSet rst = connection.createStatement().executeQuery("SELECT id FROM Customer ORDER BY id DESC LIMIT 1;");
        return rst;*/

       return  sqlUtil.execute("SELECT id FROM Customer ORDER BY id DESC LIMIT 1;");
    }
 @Override
  public ArrayList <CustomerDTO> loadAll() throws SQLException, ClassNotFoundException {
    /* Connection connection = DBConnection.getDbConnection().getConnection();
      Statement stm = connection.createStatement();
     ResultSet rst = stm.executeQuery("SELECT * FROM Customer");*/

     ResultSet rst=sqlUtil.execute("SELECT * FROM Customer");
       ArrayList<CustomerDTO>allCIds=new ArrayList<>();
      while (rst.next()){
          CustomerDTO customerDTO=new CustomerDTO(
                  rst.getString(1),
                  rst.getString(2),
                  rst.getString(3)
          );
           allCIds.add(customerDTO);
      }
      return allCIds;


  }
  @Override
  public CustomerDTO search(String id) throws SQLException, ClassNotFoundException {
    /* Connection connection = DBConnection.getDbConnection().getConnection();
      PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Customer WHERE id=?");
      pstm.setString(1, id);
      ResultSet rst = pstm.executeQuery();
      rst.next();
      return new CustomerDTO(
              rst.getString(1),
              rst.getString(2),
              rst.getString(3)
      );*/

      ResultSet rst=sqlUtil.execute("SELECT * FROM Customer WHERE id=?", id);
      rst.next();
      return new CustomerDTO(id + "", rst.getString("name"), rst.getString("address"));
  }

  }

