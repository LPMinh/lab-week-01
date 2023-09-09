package com.example.labweek01.models;

public class Log {
    private String id;
    private String accountID;
    private String loginTime;
    private String notes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
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

    public Log(String id, String accountID, String loginTime, String notes) {
        this.id = id;
        this.accountID = accountID;
        this.loginTime = loginTime;
        this.notes = notes;
    }

    public Log() {
    }
}
