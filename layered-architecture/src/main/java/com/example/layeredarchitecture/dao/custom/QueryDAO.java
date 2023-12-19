package com.example.layeredarchitecture.dao.custom;

import com.example.layeredarchitecture.model.CustomDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface QueryDAO {
    ArrayList<CustomDto> customerOrderDetails(CustomDto customDTO) throws SQLException, ClassNotFoundException;
}
