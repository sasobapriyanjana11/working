package com.example.layeredarchitecture.bo;

import com.example.layeredarchitecture.model.ItemDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBO extends SuperBO {
     ArrayList<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException;
     boolean saveItem(ItemDTO dto) throws SQLException, ClassNotFoundException;
     boolean exitItem(String code) throws SQLException, ClassNotFoundException;
    boolean deleteItem(String code) throws SQLException, ClassNotFoundException;
     boolean updateItem(ItemDTO dto) throws SQLException, ClassNotFoundException;
     ResultSet generateNewId() throws SQLException, ClassNotFoundException;
     ItemDTO searchItem(String code) throws SQLException, ClassNotFoundException;
     ArrayList<ItemDTO> loadAllItem() throws SQLException, ClassNotFoundException;
}
