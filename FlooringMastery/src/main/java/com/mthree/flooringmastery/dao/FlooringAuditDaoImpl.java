package com.mthree.flooringmastery.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class FlooringAuditDaoImpl implements FlooringAuditDao {

    public String AUDIT_FILE;

    public FlooringAuditDaoImpl() {
        this.AUDIT_FILE = "audit.txt";
    }

    public FlooringAuditDaoImpl(String AUDIT_FILE) { //allows testing with new file
        this.AUDIT_FILE = AUDIT_FILE;
    }

    @Override
    public void writeAuditEntry(String entry) {
        PrintWriter out = null;

        try {
            out = new PrintWriter(new FileWriter(AUDIT_FILE, true));
        } catch (IOException e) {
            System.out.println("audit file does not exist");
        }

        LocalDateTime timestamp = LocalDateTime.now();
        out.println(timestamp.toString() + " : " + entry);
        out.flush();
    }

}
