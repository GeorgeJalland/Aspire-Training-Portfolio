package com.mthree.flooringmastery.dao;

import com.mthree.flooringmastery.model.State;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class FlooringStatesDaoStubImpl implements FlooringStatesDao {

    @Override
    public Map<String, State> getStates() {
        State arizona = new State("AZ", "Arizona", new BigDecimal("3.00"));
        State arkansas = new State("AR", "Arkansas", new BigDecimal("5.25"));
        State alabama = new State("AL", "Alabama", new BigDecimal("6.00"));
        Map<String, State> expectedStateMap = new HashMap<>();
        expectedStateMap.put(arizona.getStateAbbreviation(), arizona);
        expectedStateMap.put(arkansas.getStateAbbreviation(), arkansas);
        expectedStateMap.put(alabama.getStateAbbreviation(), alabama);
        return expectedStateMap;
    }

}
