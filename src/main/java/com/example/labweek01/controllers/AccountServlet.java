package com.example.labweek01.controllers;

import com.example.labweek01.models.Account;
import com.example.labweek01.repository.AccountRepository;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(urlPatterns = "/AccountServlet")
public class AccountServlet extends HttpServlet {
    private AccountRepository accountRepository;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account account= (Account) req.getAttribute("acc");
        accountRepository=new AccountRepository();
       if(accountRepository.addObject(account)){
           JsonObject respondJson=new JsonObject();
           respondJson.addProperty("message","ok");

           resp.sendRedirect("dashboard.html");
       }else{
           JsonObject respondJson=new JsonObject();
           respondJson.addProperty("message","ok");
           resp.sendRedirect("dashboard.html");
       }
    }
}
