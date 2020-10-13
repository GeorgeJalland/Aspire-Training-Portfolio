package com.mthree.flooringmastery.dao;

import com.mthree.flooringmastery.model.State;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FlooringStatesDaoImpl implements FlooringStatesDao {

    String STATE_FILE;
    String DELIMITER = "::";
    Map<String, State> stateMap;

    public FlooringStatesDaoImpl() {
        this.STATE_FILE = "Data/Taxes.txt";
    }

    public FlooringStatesDaoImpl(String STATE_FILE) {
        this.STATE_FILE = STATE_FILE;
    }

    @Override
    public Map<String, State> getStates() {
        stateMap = new HashMap<>();
        loadStates();
        return stateMap;
    }

    private void loadStates() {
        Scanner scanner;
        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(STATE_FILE)));
            String currentLine;
            State currentState;
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                currentLine = scanner.nextLine();
                currentState = unmarshallData(currentLine);
                stateMap.put(currentState.getStateAbbreviation(), currentState);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }

    private State unmarshallData(String stateAsText) {
        String[] properties = stateAsText.split(DELIMITER);
        String stateAbbreviation = properties[0];
        String stateName = properties[1];
        BigDecimal taxRate = new BigDecimal(properties[2]);

        return new State(stateAbbreviation, stateName, taxRate);

    }

}
