package com.mthree.flooringmastery.view;

import com.mthree.flooringmastery.model.Order;
import com.mthree.flooringmastery.model.Product;
import com.mthree.flooringmastery.model.State;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class FlooringView {

    UserIO io;

    public FlooringView(UserIO io) {
        this.io = io;
    }

    public void welcomeBanner() {
        io.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
        io.print("* ===Welcome to Flooring Mastery Order System===\n*");
    }

    public void mainMenuBanner() {
        io.print("* ===Main menu===");
    }

    public int getMenuChoice() {
        io.print("* 1. Display Orders");
        io.print("* 2. Add an Order");
        io.print("* 3. Edit an Order");
        io.print("* 4. Remove an Order");
        io.print("* 5. Export All Date");
        io.print("* 6. Quit");
        return io.readInt("* Please enter menu choice:", 1, 6);
    }

    public void displayOrdersBanner() {
        io.print("* ===Display Orders===");
    }

    public void getEnterToContiue() {
        io.getEnter("* Please hit enter to continue.");
    }

    public String getDateFromUser() {
        String date = io.readDateAsString("* Please enter the date of the order(dd/MM/yyyy):", false);
        return formatDateWithoutSlashes(date);
    }

    private String formatDateWithoutSlashes(String date) {
        return date.substring(0, 2) + date.substring(3, 5) + date.substring(6, 10);
    }

    private String formatDateWithSlashes(String date) {
        return date.substring(0, 2) + "/" + date.substring(2, 4) + "/" + date.substring(4, 8);
    }

    public void noOrdersForThisDate() {
        io.print("* @@@ No orders found for this date. @@@\n*");
    }

    public void addNewOrderBanner() {
        io.print("* ===Add New Order===");
    }

    public void displayOrder(Order order) {
        io.print("* ===Order summary===");
        if (order.getOrderNumber() != 0) {
            io.print("* Order number  -- " + order.getOrderNumber());
        }
        io.print("* Date  ---------- " + formatDateWithSlashes(order.getDate()));
        io.print("* Name  ---------- " + order.getName());
        io.print("* State  --------- " + order.getState().getStateName());
        io.print("* Product  ------- " + order.getProduct().getProductType());
        io.print("* Area ----------- " + order.getArea().toString());
        io.print("* Total cost ----- £" + order.getTotal().setScale(2, RoundingMode.HALF_UP) + "\n*");
    }

    public Order getNewOrder(List<Product> products, Map<String, State> states) {
        String date = getDateForOrder();
        String name = getNameForOrder();
        State state = getStateForOrder(states);
        Product product = getProductForOrder(products);
        Double area = getAreaForOrder();
        Order newOrder = new Order(name, state, product, area);
        newOrder.setDate(date);
        return newOrder;
    }

    //Below: Private methods for getting order properties
    private State getStateForOrder(Map<String, State> states) {
        boolean validState = false;
        State state = null;
        while (!validState) {
            String stateAbbrev = io.readString("* Please enter state postal code (eg TX for Texas)");
            if (states.keySet().contains(stateAbbrev)) {
                state = states.get(stateAbbrev);
                validState = true;
            } else {
                io.print("* We do not currently sell in this state.");
            }
        }
        return state;
    }

    private Product getProductForOrder(List<Product> products) {
        io.print("* Please choose from the products below: ");
        int counter = 1;
        for (Product product : products) { //possibly user stream
            io.print("* " + counter + " - " + product.getProductType());
            counter++;
        }
        int productIndex = io.readInt("* Choice: ");
        return products.get(productIndex - 1);

    }

    private String getDateForOrder() {
        String date = io.readDateAsString("* Please enter the date of the order(dd/MM/yyyy):", true);
        return formatDateWithoutSlashes(date);
    }

    private String getNameForOrder() {
        return io.readName("* Please enter customer name (cannot contain special characters except .,): "); //NEED TO FORMAT NAME!!!!
    }

    private Double getAreaForOrder() {
        return io.readDouble("* Please enter area (square feet, 100 minimum):", 100, 2147483647);
    }

    public boolean confirmOrder(String operation) {
        String answer = io.readString("* Are you sure you want to " + operation + " this order? (Y/N)", "Y", "N");
        return answer.equals("Y");
    }

    public void orderSuccessfullyCreatedBanner() {
        io.print("* ===Order successfully created===\n*");
    }

    public int getOrderNumber() {
        return io.readInt("* Please enter order number: ");
    }

    public Order editOrder(Order orderToEdit, List<Product> products, Map<String, State> states) {
        if (io.readString("* Edit order customer name (" + orderToEdit.getName() + ")? (Y/N)", "Y", "N").equals("Y")) {
            orderToEdit.setName(getNameForOrder());
        }
        if (io.readString("* Edit state (" + orderToEdit.getState().getStateAbbreviation() + ")? (Y/N)",
                 "Y", "N").equals("Y")) {
            orderToEdit.setState(getStateForOrder(states));
        }
        if (io.readString("* Edit order product (" + orderToEdit.getProduct().getProductType() + ")? (Y/N)",
                 "Y", "N").equals("Y")) {
            orderToEdit.setProduct(getProductForOrder(products));
        }
        if (io.readString("* Edit order area (100 minimum) (" + orderToEdit.getArea() + ")? (Y/N)", "Y", "N").equals("Y")) {
            orderToEdit.setArea(getAreaForOrder());
        }
        //This line is vital as it calls the constructor to recalculate costs.
        Order editedOrder = new Order(orderToEdit.getName(), orderToEdit.getState(),
                orderToEdit.getProduct(), orderToEdit.getArea().doubleValue());
        editedOrder.setDate(orderToEdit.getDate()); //Date remains the same
        editedOrder.setOrderNumber(orderToEdit.getOrderNumber()); //Order number remains the same
        return editedOrder;
    }

    public void orderSuccessfullyEditedBanner() {
        io.print("* ===Order Successfully Edited===\n*");
    }

    public void orderDoesNotExistBanner() {
        io.print("* @@@ Order Does Not Exist @@@\n*");
    }

    public void orderSuccessfullyRemove() {
        io.print("* ===Order Successfully Removed===\n*");
    }

    public void exportAllSuccessfulBanner() {
        io.print("* ===Export Successful===\n*");
    }

    public void listOrders(List<Order> orders) {
        for (Order order : orders) {
            displayOrder(order);
        }
    }

    public void showTodaysDate() { //example of using localDate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String todaysDate = LocalDate.now().format(formatter);
        io.print("* Today's Date: " + todaysDate);
    }

    public void exitBanner() {
        io.print("* ===Thank you for using Flooring Mastery Order System===");
        io.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
    }

}
