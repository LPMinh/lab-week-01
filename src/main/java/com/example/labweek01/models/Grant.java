package com.example.labweek01.models;

public class Grant {
    private String role_id;
    private Account account;
    private boolean isGrant;
    private String note;

    public Grant(String role_id, Account account, boolean isGrant, String note) {
        this.role_id = role_id;
        this.account = account;
        this.isGrant = isGrant;
        this.note = note;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
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
