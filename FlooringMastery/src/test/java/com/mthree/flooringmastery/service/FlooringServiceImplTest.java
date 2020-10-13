package com.mthree.flooringmastery.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FlooringServiceImplTest {

    ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
    FlooringService testService = ctx.getBean("testService", FlooringServiceImpl.class); //new FlooringServiceImpl(auditDao,backupDao,orderDao,productDao,statesDao);

    public FlooringServiceImplTest() {
    }

    @Test
    public void testExceptionInGetOrder() {
        try {
            testService.getOrder("01/01/2025", 0);
            fail("Test should throw exception, not hit this.");
        } catch (OrderDoesNotExistException e) {
            //Test passes
        }
    }

    @Test
    public void testExceptionInListOrders() {
        try {
            testService.listOrders("01/01/2111");
            fail("Test should throw exception before this line.");
        } catch (OrderDoesNotExistException e) {
            //Test passes
        }
    }

}
