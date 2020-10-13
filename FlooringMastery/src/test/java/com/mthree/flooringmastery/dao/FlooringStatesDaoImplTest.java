package com.mthree.flooringmastery.dao;

import com.mthree.flooringmastery.model.State;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FlooringStatesDaoImplTest {

    ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
    FlooringStatesDao testStateDao = ctx.getBean("testStatesDao", FlooringStatesDaoImpl.class);

    @Test
    public void testGetStates() {
        //Arrange, these are the states associated with file entries
        State arizona = new State("AZ", "Arizona", new BigDecimal("3.00"));
        State arkansas = new State("AR", "Arkansas", new BigDecimal("5.25"));
        State alabama = new State("AL", "Alabama", new BigDecimal("6.00"));
        Map<String, State> expectedStateMap = new HashMap<>();
        expectedStateMap.put(arizona.getStateAbbreviation(), arizona);
        expectedStateMap.put(arkansas.getStateAbbreviation(), arkansas);
        expectedStateMap.put(alabama.getStateAbbreviation(), alabama);

        //Act
        Map<String, State> actualStateMap = testStateDao.getStates();

        //Arrange
        assertEquals(expectedStateMap, actualStateMap, "Check expected map is returned");

    }

}
