package com.example.labweek01.controllers;

import com.example.labweek01.models.Account;
import com.example.labweek01.repository.AccountRepository;
import com.example.labweek01.repository.GrantRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/ControlServlet")

public class ControlServlet extends HttpServlet {
    AccountRepository accountRepository;
    GrantRepository grantRepository;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String password = req.getParameter("password");
        accountRepository = new AccountRepository();
        Account account = accountRepository.findAccountByIDAndPassword(id,password);
        PrintWriter writer = resp.getWriter();
        if (account == null) {
            writer.println("Khong tim thay");
        } else {
            grantRepository = new GrantRepository();
            if (grantRepository.isRole("user",account.getAccountID())) {
                resp.sendRedirect("dashboard.html");
            } else {

            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

}

