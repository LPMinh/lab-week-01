package com.example.labweek01.test;

import com.example.labweek01.repository.MySessionFactory;

public class CreateTable {
    public static void main(String[] args) {
        new MySessionFactory().getSessionFactory();
    }
}
