package com.hackatron.integration;

import java.io.Serializable;

/**
 * Created by premik91 on 15/10/16.
 */
public class Entry implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    // Required:
    private double amount;
    private Currency currency;
    private String date;
    private String account;
    private String category;
    private String type;

    // Optional:
//    private Transaction transaction;

    public Entry(Bill bill) {
        this.amount = -bill.getAmount();
        this.currency = new Currency("EUR");
        this.date = bill.getDate();
        this.account = "2521119"; //card
        this.category = "26289180"; //other, expense
    }

    public double getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}

class Currency implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String code;

    Currency(String code) {
        this.code = code;
    }
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

class Category implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String name;
    private String type;
    private String id;

    Category(String id, String name, String type) {
        this.name = name;
        this.id = id;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
}

// Could we use zoi??
class Transaction implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String id;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

