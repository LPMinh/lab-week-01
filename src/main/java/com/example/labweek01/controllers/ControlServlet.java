package com.example.labweek01.controllers;

import com.example.labweek01.models.Account;
import com.example.labweek01.repository.AccountRepository;
import com.example.labweek01.repository.GrantRepository;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = "/ControlServlet")

public class ControlServlet extends HttpServlet {
    AccountRepository accountRepository;
    GrantRepository grantRepository;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action").toString();
        if (action.equalsIgnoreCase("login")) {
            String id=req.getParameter("id");
            String password = req.getParameter("password");
            accountRepository = new AccountRepository();
            Account account = accountRepository.findAccountByIDAndPassword(id, password);
            PrintWriter writer = resp.getWriter();
            if (account == null) {
                writer.println("Khong tim thay");
            } else {
                grantRepository = new GrantRepository();
                if (grantRepository.isRole("user", account.getAccountID())) {
                    resp.sendRedirect("dashboard.html");
                } else {

                }
            }
        }else if(action.equalsIgnoreCase("all")){
            List<Account> accounts=accountRepository.getAllAccount();
            Gson gson = new Gson();
            String json = gson.toJson(accounts);
            System.out.println(json);
            PrintWriter out = resp.getWriter();
            out.print(json);
            out.flush();
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action").toString();
        System.out.println(action);
        if (action.equalsIgnoreCase("saveAccount")) {
            String id= req.getParameter("id");
            String name=req.getParameter("name");
            String email=req.getParameter("email");
            String pass=req.getParameter("password");
            String phone=req.getParameter("phone");
            boolean status=req.getParameter("status").equals("Hoạt động")?true:false;
            Account account=new Account();
            account.setAccountID(id);
            account.setName(name);
            account.setEmail(email);
            account.setPassword(pass);
            account.setPhone(phone);
            account.setStatus(status);
            req.setAttribute("acc",account);
            RequestDispatcher requestDispatcher=req.getRequestDispatcher("AccountServlet");
            requestDispatcher.forward(req,resp);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action").toString();
        String id=req.getParameter("id").toString();
        System.out.println(action);
        accountRepository=new AccountRepository();
        Account acc=new Account();
        acc.setAccountID(id);
        if(accountRepository.removeObject(acc)){
            resp.sendRedirect("dashboard.html");
        }
    }
}

