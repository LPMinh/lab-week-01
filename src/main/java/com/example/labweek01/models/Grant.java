package com.example.labweek01.models;

public class Grant {
    private String role_id;
    private String accountID;
    private boolean isGrant;
    private String note;

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getAccountID() {
        return accountID;
    }

    public Grant(String role_id, String accountID, boolean isGrant, String note) {
        this.role_id = role_id;
        this.accountID = accountID;
        this.isGrant = isGrant;
        this.note = note;
    }

    public Grant() {
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public boolean isGrant() {
        return isGrant;
    }

    public void setGrant(boolean grant) {
        isGrant = grant;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
