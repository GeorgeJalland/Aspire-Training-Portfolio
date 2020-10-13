package com.mthree.flooringmastery.dao;

import com.mthree.flooringmastery.model.Order;
import com.mthree.flooringmastery.model.Product;
import com.mthree.flooringmastery.model.State;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FlooringOrderDaoImplTest {

    ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
    String testFileTemplate;
    String testLastOrderNumberFile;
    FlooringProductDao productDao = ctx.getBean("productDaoStub", FlooringProductDaoStubImpl.class);
    FlooringStatesDao stateDao = ctx.getBean("statesDaoStub", FlooringStatesDaoStubImpl.class);
    FlooringOrderDao testOrderDao = ctx.getBean("testOrderDao", FlooringOrderDaoImpl.class);

    @Test
    public void testCreateOrderGetOrder() {
        //Arrange
        Product steel = productDao.getProducts().get(0);
        State arizona = stateDao.getStates().get("AZ");
        Order expectedOrder = new Order("Testman", arizona, steel, 100.0);
        String testDate = "22032022";
        expectedOrder.setDate(testDate);

        //Act
        int expectedOrderNumber = testOrderDao.createOrder(expectedOrder);
        Order actualOrder = testOrderDao.getOrder(testDate, expectedOrderNumber);

        //Assert
        assertEquals(expectedOrder, actualOrder, "check order is as expected");
        assertNull(testOrderDao.getOrder("01/01/1999", expectedOrderNumber), "check no order exists for date");
        assertNull(testOrderDao.getOrder("22/03/2022", 0), "check no order exists for different order number");

    }

    @Test
    public void testReplaceOrder() {
        //Arrange
        Product steel = productDao.getProducts().get(0);
        State arizona = stateDao.getStates().get("AZ");
        Order originalOrder = new Order("Testman", arizona, steel, 100.0);
        String testDate = "01/01/2030";
        originalOrder.setDate(testDate);

        Product iron = productDao.getProducts().get(1);
        State alabama = stateDao.getStates().get("AL");
        Order expectedOrder = new Order("TesteaseMan", alabama, iron, 200.0);
        expectedOrder.setDate(testDate);

        //Act
        int originalOrderNum = testOrderDao.createOrder(originalOrder);
        expectedOrder.setOrderNumber(originalOrderNum);
        testOrderDao.replaceOrder(testDate, originalOrder, expectedOrder);
        Order actualOrder = testOrderDao.getOrder(testDate, originalOrderNum);

        //Assert
        assertEquals(expectedOrder, actualOrder, "Check order has been edited");
        assertNotSame(originalOrder, actualOrder, "Check order has been edited");

    }

    @Test
    public void testListRemoveOrders() {
        //Arrange
        Product steel = productDao.getProducts().get(0);
        State arizona = stateDao.getStates().get("AZ");
        Order expectedOrder1 = new Order("Testman", arizona, steel, 100.0);
        String testDate = "07077777";
        expectedOrder1.setDate(testDate);

        Product iron = productDao.getProducts().get(1);
        State alabama = stateDao.getStates().get("AL");
        Order expectedOrder2 = new Order("TesteaseMan", alabama, iron, 200.0);
        expectedOrder2.setDate(testDate);

        //Act
        testOrderDao.createOrder(expectedOrder1);
        testOrderDao.createOrder(expectedOrder2);
        List<Order> actualOrders = testOrderDao.listOrders(testDate);

        //Assert, general properties
        assertNotNull(actualOrders, "Check it isn't null");
        assertEquals(2, actualOrders.size());

        //Assert specifics
        assertTrue(actualOrders.contains(expectedOrder1), "Check list contains first order");
        assertTrue(actualOrders.contains(expectedOrder2), "Check list contains second order");

        //Act, remove an entry
        testOrderDao.removeOrder(testDate, expectedOrder1.getOrderNumber());
        actualOrders = testOrderDao.listOrders(testDate);

        //Assert general properties
        assertNotNull(actualOrders, "Check isn't null");
        assertEquals(1, actualOrders.size());

        //Assert specifics
        assertTrue(actualOrders.contains(expectedOrder2));
        assertFalse(actualOrders.contains(expectedOrder1));

        //Act, remove final entry
        testOrderDao.removeOrder(testDate, expectedOrder2.getOrderNumber());
        actualOrders = testOrderDao.listOrders(testDate);

        //Assert
        assertTrue(actualOrders.isEmpty(), "Check list is null");

    }
}
