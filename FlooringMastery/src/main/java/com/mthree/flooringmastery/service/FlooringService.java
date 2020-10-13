package com.mthree.flooringmastery.service;

import com.mthree.flooringmastery.model.Order;
import com.mthree.flooringmastery.model.Product;
import com.mthree.flooringmastery.model.State;
import java.util.List;
import java.util.Map;

public interface FlooringService {

    public void createOrder(Order newOrder);

    public Order getOrder(String orderDate, int orderNumber) throws OrderDoesNotExistException;

    public void replaceOrder(String orderDate, Order orderToEdit, Order editedOrder);

    public void removeOrder(String orderDate, int orderNumber);

    public void exportAll();

    public List<Product> getProducts();

    public Map<String, State> getStates();

    public List<Order> listOrders(String date) throws OrderDoesNotExistException; //Potentially change return type

}
