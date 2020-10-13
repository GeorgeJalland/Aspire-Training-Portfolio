package com.mthree.flooringmastery.dao;

import com.mthree.flooringmastery.model.Product;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FlooringProductDaoImplTest {

    ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
    FlooringProductDao testProductDao = ctx.getBean("testProductDao", FlooringProductDaoImpl.class);

    @Test
    public void testGetProducts() {

        //Arrange, create products that are in test file.
        Product steel = new Product("Steel", new BigDecimal("3.25"), new BigDecimal("2.00"));
        Product iron = new Product("Iron", new BigDecimal("0.75"), new BigDecimal("3.10"));
        Product glass = new Product("Glass", new BigDecimal("4.00"), new BigDecimal("5.00"));
        List<Product> expectedProducts = new ArrayList<>();
        expectedProducts.add(steel);
        expectedProducts.add(iron);
        expectedProducts.add(glass);

        //Act
        List<Product> actualProducts = testProductDao.getProducts();

        //Assert
        assertEquals(expectedProducts, actualProducts, "Check products lists are equal");

    }

}
