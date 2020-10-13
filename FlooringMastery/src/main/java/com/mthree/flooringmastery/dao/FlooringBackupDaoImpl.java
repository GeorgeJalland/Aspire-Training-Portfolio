package com.mthree.flooringmastery.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FlooringBackupDaoImpl implements FlooringBackupDao {

    //Iterate through each file in director that contains orders, write all orders
    //to the backup file.
    String backupFile = "Backup/DataExport.txt";
    String orderDirectory = "Orders";
    File dir = new File(orderDirectory);
    File[] directoryListing = dir.listFiles();

    @Override
    public void exportAll() {
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(backupFile));
            String header = "OrderNumber::CustomerName::State::TaxRate::ProductType::"
                    + "Area::CostPerSquareFoot::LaborCostPerSquareFoot::MaterialCost::LaborCost::Tax::Total::Date";
            out.println(header);
            for (File orderFile : directoryListing) {
                String fileName = orderFile.getName();
                if (fileName.equals("lastOrderNumber.txt")) { //Ignores lastOrderNumber file.
                    continue;
                }
                Scanner scanner;
                String fileDate = getFileDate(fileName);
                try {
                    // Create Scanner for reading the file
                    scanner = new Scanner(
                            new BufferedReader(
                                    new FileReader(orderFile)));
                    scanner.nextLine(); //ignore first line of files
                    while (scanner.hasNextLine()) {
                        out.println(scanner.nextLine() + "::" + fileDate); //writes line from order file to backup file
                        out.flush();
                    }
                    scanner.close();
                } catch (FileNotFoundException e) {
                    //do something
                }
            }
            out.close();
        } catch (IOException e) {
            System.out.println("No backup file found");
        }

    }

    private String getFileDate(String fileName) {
        String fileDateUnformatted = fileName.substring(7, 15);
        String fileDate = fileDateUnformatted.substring(0, 2) + "/" + fileDateUnformatted.substring(2, 4)
                + "/" + fileDateUnformatted.substring(4, 8);
        return fileDate;
    }

}
