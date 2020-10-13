package com.mthree.flooringmastery.dao;

import com.mthree.flooringmastery.model.Order;
import java.util.List;

public interface FlooringOrderDao {

    public int createOrder(Order newOrder);

    public Order getOrder(String orderDate, int orderNumber);

    public void replaceOrder(String orderDate, Order orderToEdit, Order editedOrder);

    public void removeOrder(String orderDate, int orderNumber);

    public List<Order> listOrders(String date);

}
