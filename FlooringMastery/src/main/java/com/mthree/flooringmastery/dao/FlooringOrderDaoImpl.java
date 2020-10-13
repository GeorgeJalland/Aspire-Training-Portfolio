package com.mthree.flooringmastery.dao;

import com.mthree.flooringmastery.model.Order;
import com.mthree.flooringmastery.model.Product;
import com.mthree.flooringmastery.model.State;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class FlooringOrderDaoImpl implements FlooringOrderDao {

    String DELIMITER = "::";
    String lastOrderNumberFile;
    String orderFileTemplate;
    Map<Integer, Order> orderMap = new HashMap<>();
    FlooringProductDao productDao;
    FlooringStatesDao statesDao;

    //Two constructors required for testing, so test files can be injected.
    public FlooringOrderDaoImpl(FlooringProductDao productDao, FlooringStatesDao statesDao) {
        this.productDao = productDao;
        this.statesDao = statesDao;
        this.orderFileTemplate = "Orders/Orders_";
        this.lastOrderNumberFile = "Orders/lastOrderNumber.txt";
    }

    public FlooringOrderDaoImpl(String orderFileTemplate, String lastOrderNumberFile, FlooringProductDao productDao, FlooringStatesDao statesDao) {
        this.orderFileTemplate = orderFileTemplate;
        this.lastOrderNumberFile = lastOrderNumberFile;
        this.productDao = productDao;
        this.statesDao = statesDao;
    }

    private int getLastOrderNumber() {
        Scanner scanner;
        int lastOrderNumber = 0;
        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(lastOrderNumberFile)));
            lastOrderNumber = scanner.nextInt();
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("No order number file found to read.");
        }
        return lastOrderNumber;
    }

    private void setLastOrderNumber(int lastOrderNumber) {
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(lastOrderNumberFile));
            out.println(lastOrderNumber);
            out.flush();
            out.close();
        } catch (IOException e) {
            System.out.println("No order number file found to write.");
        }
    }

    @Override
    public int createOrder(Order newOrder) {
        //READ LAST ORDER NUMBER FROM FILE
        //WRITE LAST ORDER NUMBER TO FILE
        loadOrders(newOrder.getDate());
        int newOrderNumber = getLastOrderNumber() + 1;
        newOrder.setOrderNumber(newOrderNumber);
        orderMap.put(newOrderNumber, newOrder);
        setLastOrderNumber(newOrderNumber);
        writeOrders(newOrder.getDate());

        return newOrderNumber;
    }

    @Override
    public Order getOrder(String orderDate, int orderNumber) {
        loadOrders(orderDate);
        Order order = orderMap.get(orderNumber);
        writeOrders(orderDate);
        return order; //return null if no order exists ****
    }

    @Override
    public void replaceOrder(String orderDate, Order orderToEdit, Order editedOrder) {
        loadOrders(orderDate);
        orderMap.replace(orderToEdit.getOrderNumber(), editedOrder);
        writeOrders(orderDate);
    }

    @Override
    public void removeOrder(String orderDate, int orderNumber) {
        boolean orderExists = loadOrders(orderDate);
        if (orderExists) {
            orderMap.remove(orderNumber);
            writeOrders(orderDate);
        }
    }

    @Override
    public List<Order> listOrders(String date) {
        boolean ordersExist = loadOrders(date);
        List<Order> listOfOrders = new ArrayList<>(orderMap.values());
        if (ordersExist) {
            writeOrders(date);
        }
        return listOfOrders;
    }

    private boolean loadOrders(String date) {
        boolean ordersExist = true;
        Scanner scanner;
        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(orderFileTemplate + date + ".txt")));
            String currentLine;
            Order currentOrder;
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                currentLine = scanner.nextLine();
                currentOrder = unmarshallData(currentLine);
                currentOrder.setDate(date);
                orderMap.put(currentOrder.getOrderNumber(), currentOrder);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            ordersExist = false;
        }
        return ordersExist;
    }

    private void writeOrders(String date) {
        PrintWriter out;
        File fileToWrite = new File(orderFileTemplate + date + ".txt");
        boolean mapEmpty = orderMap.isEmpty();
        if (mapEmpty) {
            fileToWrite.delete();
        } else {
            try {
                out = new PrintWriter(new FileWriter(fileToWrite));
                String orderAsString;
                String header = "OrderNumber::CustomerName::State::TaxRate::ProductType::"
                        + "Area::CostPerSquareFoot::LaborCostPerSquareFoot::MaterialCost::LaborCost::Tax::Total";
                out.println(header);
                for (Order currentOrder : orderMap.values()) {
                    orderAsString = marshallData(currentOrder);
                    out.println(orderAsString);
                    out.flush();
                }
                orderMap = new HashMap<>();
                out.close();
            } catch (IOException e) {
                System.out.println("No order file found to write.");
            }
        }
    }

    private String marshallData(Order order) { //Read Item from map and output as String for file
        String orderAsString = order.getOrderNumber() + DELIMITER;
        orderAsString += order.getName() + DELIMITER;
        orderAsString += order.getState().getStateAbbreviation() + DELIMITER;
        orderAsString += order.getState().getTaxRate().toString() + DELIMITER;
        orderAsString += order.getProduct().getProductType() + DELIMITER;
        orderAsString += order.getArea().toString() + DELIMITER;
        orderAsString += order.getProduct().getCostPerSquareFoot().toString() + DELIMITER;
        orderAsString += order.getProduct().getLaborCostPerSquareFoot().toString() + DELIMITER;
        orderAsString += order.getMaterialCost().toString() + DELIMITER;
        orderAsString += order.getLaborCost().toString() + DELIMITER;
        orderAsString += order.getTax().toString() + DELIMITER;
        orderAsString += order.getTotal().toString();

        return orderAsString;
    }

    private Order unmarshallData(String orderAsText) { //Read a line of data and output as Item
        Map<String, Product> productMap = new HashMap<>();
        productDao.getProducts().stream().forEach((p) -> productMap.put(p.getProductType(), p));
        //***Above is an example of a lambda and stream***
        Map<String, State> stateMap = statesDao.getStates();

        String[] properties = orderAsText.split(DELIMITER);
        int orderNumber = Integer.parseInt(properties[0]);
        String customerName = properties[1];
        State state = stateMap.get(properties[2]);
        Product product = productMap.get(properties[4]);
        Double area = Double.parseDouble(properties[5]);

        Order loadedOrder = new Order(customerName, state, product, area);
        loadedOrder.setOrderNumber(orderNumber);
        return loadedOrder;

    }

}
