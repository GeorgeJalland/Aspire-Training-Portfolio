package com.mthree.flooringmastery.controller;

import com.mthree.flooringmastery.model.Order;
import com.mthree.flooringmastery.service.FlooringService;
import com.mthree.flooringmastery.service.OrderDoesNotExistException;
import com.mthree.flooringmastery.view.FlooringView;

public class FlooringController {

    FlooringView view;
    FlooringService service;

    public FlooringController(FlooringView view, FlooringService service) {
        this.view = view;
        this.service = service;
    }

    public void run() {
        boolean exit = false;
        view.welcomeBanner();
        view.showTodaysDate();
        while (!exit) {
            view.mainMenuBanner();
            switch (view.getMenuChoice()) {
                case 1:
                    displayOrders();
                    break;
                case 2:
                    addOrder();
                    break;
                case 3:
                    editOrder();
                    break;
                case 4:
                    removeOrder();
                    break;
                case 5:
                    exportAll();
                    break;
                case 6:
                    exit = true;
            }
        }
        view.exitBanner();
    }

    private void displayOrders() {
        view.displayOrdersBanner();
        String orderDate = view.getDateFromUser();
        try { //***example of custom exception handling***
            view.listOrders(service.listOrders(orderDate));
        } catch (OrderDoesNotExistException e) {
            view.noOrdersForThisDate();
        }
        view.getEnterToContiue();
    }

    private void addOrder() {
        view.addNewOrderBanner();
        Order newOrder = view.getNewOrder(service.getProducts(), service.getStates());
        view.displayOrder(newOrder);
        if (view.confirmOrder("add")) { //confirm order (Y/N)
            service.createOrder(newOrder);
            view.orderSuccessfullyCreatedBanner();
        } //else return to main menu
        view.getEnterToContiue();
    }

    private void editOrder() {
        String orderDate = view.getDateFromUser();
        int orderNumber = view.getOrderNumber();
        try {
            Order orderToEdit = service.getOrder(orderDate, orderNumber);
            Order editedOrder = view.editOrder(orderToEdit, service.getProducts(), service.getStates());
            view.displayOrder(editedOrder);
            if (view.confirmOrder("edit")) {
                service.replaceOrder(orderDate, orderToEdit, editedOrder);
                view.orderSuccessfullyEditedBanner();
            }
        } catch (OrderDoesNotExistException e) {
            view.orderDoesNotExistBanner();
        }
        view.getEnterToContiue();
    }

    private void removeOrder() {
        String orderDate = view.getDateFromUser();
        int orderNumber = view.getOrderNumber();
        try {
            view.displayOrder(service.getOrder(orderDate, orderNumber));
            if (view.confirmOrder("remove")) {
                service.removeOrder(orderDate, orderNumber);
                view.orderSuccessfullyRemove();
            }
        } catch (OrderDoesNotExistException e) {
            view.orderDoesNotExistBanner();
        }
        view.getEnterToContiue();
    }

    private void exportAll() {
        service.exportAll();
        view.exportAllSuccessfulBanner();
        view.getEnterToContiue();
    }

}
