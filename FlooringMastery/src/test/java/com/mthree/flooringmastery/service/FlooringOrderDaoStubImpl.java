package com.mthree.flooringmastery.service;

import com.mthree.flooringmastery.dao.FlooringOrderDao;
import com.mthree.flooringmastery.model.Order;
import java.util.ArrayList;
import java.util.List;

public class FlooringOrderDaoStubImpl implements FlooringOrderDao {

    @Override
    public int createOrder(Order newOrder) {
        //Do nothing, pass through method
        return 0;
    }

    @Override
    public Order getOrder(String orderDate, int orderNumber) {
        return null;
    }

    @Override
    public void replaceOrder(String orderDate, Order orderToEdit, Order editedOrder) {
        //Do nothing, pass through method
    }

    @Override
    public void removeOrder(String orderDate, int orderNumber) {
        //Do nothing, pass through method
    }

    @Override
    public List<Order> listOrders(String date) {
        List<Order> emptyList = new ArrayList<>();
        return emptyList;
    }

}
