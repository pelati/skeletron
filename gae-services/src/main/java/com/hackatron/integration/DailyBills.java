package com.hackatron.integration;

import java.util.ArrayList;

/**
 * Created by premik91 on 15/10/16.
 */
public class DailyBills {
    private String day;
    //private float sum;
    //private int count;
    //private String currency;
    private ArrayList<Bill> entries;

    public void setDay(String day) {
        this.day = day;
    }

    public String getDay() {
        return day;
    }

    public void setEntries(ArrayList<Bill> entries) {
        this.entries = entries;
    }

    public ArrayList<Bill> getEntries() {
        return entries;
    }
}
