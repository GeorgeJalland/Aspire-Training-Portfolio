package com.mthree.flooringmastery.dao;

import com.mthree.flooringmastery.model.Product;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FlooringProductDaoImpl implements FlooringProductDao {

    String PRODUCT_FILE;
    String DELIMITER = "::";
    List<Product> productList;

    public FlooringProductDaoImpl() {
        this.PRODUCT_FILE = "Data/Products.txt";
    }

    public FlooringProductDaoImpl(String PRODUCT_FILE) {
        this.PRODUCT_FILE = PRODUCT_FILE;
    }

    @Override
    public List<Product> getProducts() {
        productList = new ArrayList<>();
        loadProducts();
        return productList;
    }

    private void loadProducts() {
        Scanner scanner;
        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(PRODUCT_FILE)));
            String currentLine;
            Product currentProduct;
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                currentLine = scanner.nextLine();
                currentProduct = unmarshallData(currentLine);
                productList.add(currentProduct);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }

    private Product unmarshallData(String productAsText) {
        String[] properties = productAsText.split(DELIMITER);
        //DONT FORGET TO SET DATE IN LOAD METHOD
        String productType = properties[0];
        BigDecimal productCost = new BigDecimal(properties[1]);
        BigDecimal productLaborCost = new BigDecimal(properties[2]);

        return new Product(productType, productCost, productLaborCost);

    }

}
