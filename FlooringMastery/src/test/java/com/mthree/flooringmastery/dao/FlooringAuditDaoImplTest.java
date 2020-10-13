package com.mthree.flooringmastery.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FlooringAuditDaoImplTest {

    FlooringAuditDao auditTestDao;
    String testFile = "TestFiles/Audit/testaudit.txt";

    @BeforeEach
    public void setUp() throws IOException {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        // Use the FileWriter to quickly blank the file
        new FileWriter(testFile);
        auditTestDao = ctx.getBean("testAuditDao", FlooringAuditDaoImpl.class);
    }

    @Test
    public void testWritingToAudit() {
        //Arrange
        String testString = "Testing audit file";

        //Act
        auditTestDao.writeAuditEntry(testString);

        //Assert
        Scanner scanner;
        String stringFromFile = "";
        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(testFile)));
            stringFromFile = scanner.nextLine();
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        assertTrue(stringFromFile.contains(testString), "Test audit has been written to file");
    }

}
