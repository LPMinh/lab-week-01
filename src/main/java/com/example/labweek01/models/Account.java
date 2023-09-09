package com.example.labweek01.models;

public class Account {
    private String accountID;
    private String name;
    private String password;
    private String email;
    private String phone;
    private boolean status;

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Account(String accountID, String name, String password, String email, String phone, boolean status) {
        this.accountID = accountID;
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.status = status;
    }

    public Account() {
    }
}
