package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.ItemDTO;

import java.sql.*;
import java.util.ArrayList;

public interface ItemDAO extends CrudDAO<ItemDTO>{
    /* ArrayList<ItemDTO> loadAllItems() throws SQLException, ClassNotFoundException ;

     boolean saveItem(ItemDTO dto) throws SQLException, ClassNotFoundException ;

     boolean exitItem(String code) throws SQLException, ClassNotFoundException ;

     boolean deleteItem(String code) throws SQLException, ClassNotFoundException ;

    boolean updateItem(ItemDTO dto) throws SQLException, ClassNotFoundException ;

     ResultSet generateId() throws SQLException, ClassNotFoundException ;

     ItemDTO searchItem(String code) throws SQLException, ClassNotFoundException;

    ArrayList<ItemDTO> loadAllItemCodes() throws SQLException, ClassNotFoundException;*/

}
