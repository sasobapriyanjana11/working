package com.example.layeredarchitecture.dao.custom.Impl;

import com.example.layeredarchitecture.dao.custom.QueryDAO;
import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.CustomDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class QueryDAOImpl implements QueryDAO {

    @Override
    public ArrayList<CustomDto> customerOrderDetails(CustomDto customDTO) throws SQLException, ClassNotFoundException {
      Connection connection=DBConnection.getDbConnection().getConnection();
      String sql="SELECT c.name,c.id,\n" +
              "o.oid,\n" +
              "o.date,\n" +
              "od.qty * od.unitPrice as orderTotal\n" +
              " FROM Customer c\n" +
              "  JOIN\n" +
              "   Orders o ON c.id=o.customerID\n" +
              " JOIN\n" +
              "  OrderDetails od ON o.oid=od.oid \n" +
              "  JOIN\n" +
              "   Item i on i.code=od.itemCode;";

        ArrayList<CustomDto> customDtos=new ArrayList<>();
        PreparedStatement pstm=connection.prepareStatement(sql);
        ResultSet rst= pstm.executeQuery();
        while (rst.next()) {
             CustomDto all=  new CustomDto(
               rst.getString(1),
               rst.getString(2) ,
               rst.getString(3),
                rst.getDate(4).toLocalDate(),
                rst.getBigDecimal(5)

        );

          customDtos.add(all);

        }

       return customDtos;
    }


}
