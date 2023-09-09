package com.example.labweek01.models;

public class Log {
    private String id;
    private Account accountID;
    private String loginTime;
    private String notes;

    public Log(String id, Account accountID, String loginTime, String notes) {
        this.id = id;
        this.accountID = accountID;
        this.loginTime = loginTime;
        this.notes = notes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Account getAccountID() {
        return accountID;
    }

    public void setAccountID(Account accountID) {
        this.accountID = accountID;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
