package com.hackatron.integration;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Created by premik91 on 12/10/16.
 */
public class Bill {

    private DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd");

    private boolean isPaid = true;
    private String zoi;
    private String issuer;
    private double amount;

    private String id;
    private String date;

    private boolean isInToshl = true;

    public Bill() {
    }

    public Bill(double amount) {
        this.amount = amount;
        this.date = DateTime.now().toString(dtf);
    }

    private Bill(double amount, boolean isInToshl, String issuer) {
        this.isInToshl = isInToshl;
        this.amount = amount;
        this.issuer = issuer;
        this.date = DateTime.now().toString(dtf);
    }

    Bill(double amount, String zoi, DateTime date) {
        this.zoi = zoi;
        this.amount = amount;
        this.date = date.toString(dtf);
    }

    public boolean getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(boolean paid) {
        this.isPaid = paid;
    }

    public String getZoi() {
        return zoi;
    }

    // Should be exactly 32 char long?
    public void setZoi(String zoi) {
        this.zoi = zoi;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setAmount(String amount) {
        this.amount = Double.valueOf(amount);
    }

    public double getAmount() {
        return amount;
    }

    public void setInToshl(boolean isInToshl) {
        this.isInToshl = isInToshl;
    }

    public boolean isInToshl() {
        return isInToshl;
    }

    public static Bill of(double amount, boolean isInToshl, String issuer) {
        return new Bill(amount, isInToshl, issuer);
    }
}
