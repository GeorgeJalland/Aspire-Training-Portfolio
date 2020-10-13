package com.mthree.flooringmastery.service;

import com.mthree.flooringmastery.dao.FlooringAuditDao;
import com.mthree.flooringmastery.dao.FlooringBackupDao;
import com.mthree.flooringmastery.dao.FlooringOrderDao;
import com.mthree.flooringmastery.dao.FlooringProductDao;
import com.mthree.flooringmastery.model.Order;
import com.mthree.flooringmastery.model.Product;
import com.mthree.flooringmastery.model.State;
import java.util.List;
import java.util.Map;
import com.mthree.flooringmastery.dao.FlooringStatesDao;

public class FlooringServiceImpl implements FlooringService {

    FlooringAuditDao auditDao; //Add to contructor and bean in application context
    FlooringBackupDao backupDao;
    FlooringOrderDao orderDao;
    FlooringProductDao productDao;
    FlooringStatesDao statesDao;

    public FlooringServiceImpl(FlooringAuditDao auditDao, FlooringBackupDao backupDao, FlooringOrderDao orderDao,
            FlooringProductDao productDao, FlooringStatesDao statesDao) {
        this.auditDao = auditDao;
        this.backupDao = backupDao;
        this.orderDao = orderDao;
        this.productDao = productDao;
        this.statesDao = statesDao;
    }

    @Override
    public void createOrder(Order newOrder) {
        int orderNumber = orderDao.createOrder(newOrder);
        auditDao.writeAuditEntry("Order number " + orderNumber + " created.");
    }

    @Override
    public Order getOrder(String orderDate, int orderNumber) throws OrderDoesNotExistException {
        Order order = orderDao.getOrder(orderDate, orderNumber);
        if (order == null) {
            throw new OrderDoesNotExistException("Order does not exist.");
        } else {
            return order;
        }
    }

    @Override
    public void replaceOrder(String orderDate, Order orderToEdit, Order editedOrder) {
        orderDao.replaceOrder(orderDate, orderToEdit, editedOrder);
        auditDao.writeAuditEntry("Order number " + editedOrder.getOrderNumber() + " edited.");
    }

    @Override
    public void removeOrder(String orderDate, int orderNumber) {
        orderDao.removeOrder(orderDate, orderNumber);
        auditDao.writeAuditEntry("Order number " + orderNumber + " removed.");
    }

    @Override
    public void exportAll() {
        backupDao.exportAll();
        auditDao.writeAuditEntry("All data exported to back up file.");
    }

    @Override
    public List<Product> getProducts() {
        return productDao.getProducts();
    }

    @Override
    public Map<String, State> getStates() {
        return statesDao.getStates();
    }

    @Override
    public List<Order> listOrders(String date) throws OrderDoesNotExistException {

        List<Order> orders = orderDao.listOrders(date);
        if (orders.isEmpty()) {
            throw new OrderDoesNotExistException("Order does not exist.");
        } else {
            return orders;
        }
    }

}
