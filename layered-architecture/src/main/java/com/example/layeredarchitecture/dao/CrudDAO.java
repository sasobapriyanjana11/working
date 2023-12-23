package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.model.CustomerDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T> extends SuperDAO {
    ArrayList<T> getAll() throws SQLException, ClassNotFoundException;

    boolean save(T dto) throws SQLException, ClassNotFoundException;


    boolean update(T dto) throws SQLException, ClassNotFoundException;


    boolean exit(String id) throws SQLException, ClassNotFoundException;


    boolean delete(String id) throws SQLException, ClassNotFoundException;


    ResultSet generateNewId() throws SQLException, ClassNotFoundException;
    //Changed

    T search(String id) throws SQLException, ClassNotFoundException;

    public ArrayList <T> loadAll() throws SQLException, ClassNotFoundException;
}
