package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.dao.custom.Impl.*;
import com.example.layeredarchitecture.dao.custom.QueryDAO;

public class DAOFactory {
    private  static DAOFactory daoFactory;
    private DAOFactory(){

    }

    public static DAOFactory getDaoFactory() {
        return (daoFactory==null)?daoFactory=new DAOFactory():daoFactory;
    }
   public enum getDaoType{
        CUSTOMER,ITEM,ORDERS,ORDER_DETAILS,QUERYDAO
   }
   public SuperDAO getType(getDaoType getDaoType){
        switch (getDaoType){
            case ITEM:
                return new ItemDAOImpl();
            case ORDERS:
                return new OrdersDAOImpl();
            case CUSTOMER:
                return new CustomerDAOImpl();
            case ORDER_DETAILS:
                return new OrderDetailsDAOImpl();
            case QUERYDAO:
                return  new QueryDAOImpl();
            default:
                return null;
        }
   }

}
