package com.mthree.flooringmastery.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Order {

    private int orderNumber;
    private String name;
    private State state;
    private Product product;
    private BigDecimal area;
    private String date;
    private final BigDecimal materialCost;
    private final BigDecimal laborCost;
    private final BigDecimal tax;
    private final BigDecimal total;

    public Order(String name, State state, Product product, Double area) {
        this.name = name;
        this.state = state;
        this.product = product;
        this.area = new BigDecimal(area.toString());
        this.materialCost = this.area.multiply(product.getCostPerSquareFoot());
        this.laborCost = this.area.multiply(product.getLaborCostPerSquareFoot());
        this.tax = (this.materialCost.add(this.laborCost))
                .multiply(state.getTaxRate().divide(new BigDecimal("100")));
        this.total = this.materialCost.add(this.laborCost).add(this.tax);
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setArea(Double area) {
        this.area = new BigDecimal(area.toString());
    }

    public BigDecimal getMaterialCost() {
        return materialCost;
    }

    public BigDecimal getLaborCost() {
        return laborCost;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public String getName() {
        return name;
    }

    public State getState() {
        return state;
    }

    public Product getProduct() {
        return product;
    }

    public BigDecimal getArea() {
        return area;
    }

    public String getDate() {
        return date;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + this.orderNumber;
        hash = 67 * hash + Objects.hashCode(this.name);
        hash = 67 * hash + Objects.hashCode(this.state);
        hash = 67 * hash + Objects.hashCode(this.product);
        hash = 67 * hash + Objects.hashCode(this.area);
        hash = 67 * hash + Objects.hashCode(this.date);
        hash = 67 * hash + Objects.hashCode(this.materialCost);
        hash = 67 * hash + Objects.hashCode(this.laborCost);
        hash = 67 * hash + Objects.hashCode(this.tax);
        hash = 67 * hash + Objects.hashCode(this.total);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Order other = (Order) obj;
        if (this.orderNumber != other.orderNumber) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.state, other.state)) {
            return false;
        }
        if (!Objects.equals(this.product, other.product)) {
            return false;
        }
        if (!Objects.equals(this.area, other.area)) {
            return false;
        }
        if (!Objects.equals(this.materialCost, other.materialCost)) {
            return false;
        }
        if (!Objects.equals(this.laborCost, other.laborCost)) {
            return false;
        }
        if (!Objects.equals(this.tax, other.tax)) {
            return false;
        }
        if (!Objects.equals(this.total, other.total)) {
            return false;
        }
        return true;
    }

}
