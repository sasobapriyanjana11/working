package com.example.layeredarchitecture.dao.custom.Impl;

import com.example.layeredarchitecture.dao.custom.ItemDAO;
import com.example.layeredarchitecture.dao.sqlUtil;
import com.example.layeredarchitecture.model.ItemDTO;

import java.sql.*;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public ArrayList<ItemDTO>getAll() throws SQLException, ClassNotFoundException {

        /*Connection connection = DBConnection.getDbConnection().getConnection();
        Statement stm = connection.createStatement();*/
        //ResultSet rst = stm.executeQuery("SELECT * FROM Item");

        ResultSet rst= sqlUtil.execute("SELECT * FROM Item");
        ArrayList<ItemDTO> alItem=new ArrayList<>();
        while (rst.next()){
            ItemDTO itemDTO=new ItemDTO(
                    rst.getString("code"),
                    rst.getString("description"),
                    rst.getBigDecimal("unitPrice"),
                    rst.getInt("qtyOnHand")

            );
            alItem.add(itemDTO);
        }
        return alItem;
    }
    @Override
    public boolean save(ItemDTO dto) throws SQLException, ClassNotFoundException {

       /* Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO Item (code, description, unitPrice, qtyOnHand) VALUES (?,?,?,?)");
        pstm.setString(1, dto.getCode());
        pstm.setString(2, dto.getDescription());
        pstm.setBigDecimal(3, dto.getUnitPrice());
        pstm.setInt(4, dto.getQtyOnHand());
       boolean isSaved= pstm.executeUpdate()>0;
       return  isSaved;*/
        return sqlUtil.execute("INSERT INTO Item (code, description, unitPrice, qtyOnHand) VALUES (?,?,?,?)",dto.getCode(),dto.getDescription(),dto.getUnitPrice(),dto.getQtyOnHand());
    }
    @Override
    public boolean exit(String code) throws SQLException, ClassNotFoundException {

       /* Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT code FROM Item WHERE code=?");
        pstm.setString(1, code);
        return pstm.executeQuery().next();*/

        ResultSet rst=sqlUtil.execute("SELECT code FROM Item WHERE code=?",code);
        return rst.next();
    }
    @Override
    public boolean delete(String code) throws SQLException, ClassNotFoundException {

      /*  Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("DELETE FROM Item WHERE code=?");
        pstm.setString(1, code);
       boolean isUpdated= pstm.executeUpdate()>0;
       return isUpdated;*/

        return sqlUtil.execute("DELETE FROM Item WHERE code=?",code);
    }
 @Override
    public boolean update(ItemDTO dto) throws SQLException, ClassNotFoundException {

    /* Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?");
        pstm.setString(1, dto.getDescription());
        pstm.setBigDecimal(2, dto.getUnitPrice());
        pstm.setInt(3, dto.getQtyOnHand());
        pstm.setString(4, dto.getCode());
      boolean isUpdate=  pstm.executeUpdate()>0;
      return  isUpdate;*/

    return sqlUtil.execute("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?",dto.getDescription(),dto.getUnitPrice(),dto.getQtyOnHand(),dto.getCode());

    }
    @Override
    public ResultSet generateNewId() throws SQLException, ClassNotFoundException {

       /* Connection connection = DBConnection.getDbConnection().getConnection();
        ResultSet rst = connection.createStatement().executeQuery("SELECT code FROM Item ORDER BY code DESC LIMIT 1;");
        return rst;*/
        return sqlUtil.execute("SELECT code FROM Item ORDER BY code DESC LIMIT 1;");
    }

    public ItemDTO search(String code) throws SQLException, ClassNotFoundException {

       /* Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Item WHERE code=?");
        pstm.setString(1, code);
        ResultSet rst = pstm.executeQuery();*/

        ResultSet rst=sqlUtil.execute("SELECT * FROM Item WHERE code=?",code);
        rst.next();

       ItemDTO itemDTO=new ItemDTO(
           rst.getString(1),
           rst.getString(2),
           rst.getBigDecimal(4),
           rst.getInt(3)


       );

       return  itemDTO;
    }

   @Override
    public ArrayList<ItemDTO> loadAll() throws SQLException, ClassNotFoundException {

      /*  Connection connection = DBConnection.getDbConnection().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("SELECT * FROM Item");*/

       ResultSet rst=sqlUtil.execute("SELECT * FROM Item");
        ArrayList<ItemDTO> allItemCodes=new ArrayList<>();
        while (rst.next()){
            allItemCodes.add(new ItemDTO(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getBigDecimal(3),
                    rst.getInt(4)
            ));
        }
        return allItemCodes;
    }
}
