package com.mthree.flooringmastery.dao;

import com.mthree.flooringmastery.model.Product;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class FlooringProductDaoStubImpl implements FlooringProductDao {

    @Override
    public List<Product> getProducts() {
        Product steel = new Product("Steel", new BigDecimal("3.25"), new BigDecimal("2.00"));
        Product iron = new Product("Iron", new BigDecimal("0.75"), new BigDecimal("3.10"));
        Product glass = new Product("Glass", new BigDecimal("4.00"), new BigDecimal("5.00"));
        List<Product> expectedProducts = new ArrayList<>();
        expectedProducts.add(steel);
        expectedProducts.add(iron);
        expectedProducts.add(glass);
        return expectedProducts;
    }

}
