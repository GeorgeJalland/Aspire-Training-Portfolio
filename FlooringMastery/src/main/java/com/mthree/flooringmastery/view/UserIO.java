package com.mthree.flooringmastery.view;

public interface UserIO {

    void print(String message);

    void getEnter(String prompt);

    String readString(String prompt);

    String readString(String prompt, String yes, String no);

    String readName(String prompt);

    String readDateAsString(String prompt, boolean future);

    int readInt(String prompt);

    int readInt(String prompt, int min, int max);

    double readDouble(String prompt);

    double readDouble(String prompt, double min, double max);

    float readFloat(String prompt);

    float readFloat(String prompt, float min, float max);

    long readLong(String prompt);

    long readLong(String prompt, long min, long max);

}
