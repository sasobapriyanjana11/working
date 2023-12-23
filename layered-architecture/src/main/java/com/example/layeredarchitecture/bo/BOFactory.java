package com.example.layeredarchitecture.bo;

import com.example.layeredarchitecture.dao.custom.Impl.OrderDetailsDAOImpl;
import com.example.layeredarchitecture.dao.custom.Impl.OrdersDAOImpl;
import com.example.layeredarchitecture.dao.custom.Impl.QueryDAOImpl;

public class BOFactory implements SuperBO{
    private static BOFactory boFactory;
    private BOFactory(){

    }
    public static BOFactory getBoFactory(){
        return (boFactory==null)?boFactory=new BOFactory():boFactory;

    }
    public enum getFactory1{
        CUSTOMER,ITEM,PLACE_ORDERS
    }
    public SuperBO getType(getFactory1 getFactory1){
        switch (getFactory1){
            case CUSTOMER:return new CustomerBOImpl();
            case ITEM:return new ItemBOImpl();
            case PLACE_ORDERS:return new PlaceOrderBOImpl();
            default: return null;
        }

    }
}
