package com.example.labweek01.controllers;

import com.example.labweek01.models.Account;
import com.example.labweek01.models.Grant;
import com.example.labweek01.repository.AccountRepository;
import com.example.labweek01.repository.GrantRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "UpdateServlet", value = "/UpdateServlet")
public class UpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accountID=req.getParameter("id");
        String name=req.getParameter("name");
        String email=req.getParameter("email");
        String phone=req.getParameter("phone");
        String password=req.getParameter("password");
        String id=req.getParameter("id");
        boolean status=req.getParameter("status").equals("Hoạt động")?true:false;
        String role=req.getParameter("role");

        AccountRepository accountRepository=new AccountRepository();
        Account account= accountRepository.getByAccountId(accountID);
        account.setName(name);
        account.setEmail(email);
        account.setPhone(phone);
        account.setPassword(password);
        account.setStatus(status);;
        accountRepository.updateObject(account);
        Grant gramt=new Grant();
        gramt.setAccount(account);
        gramt.setRole(accountRepository.getRoleByID(role));
        gramt.setGrant(true);
        GrantRepository grantRepository=new GrantRepository();
        if(grantRepository.findObject(gramt,Grant.class)==null){
            grantRepository.addObject(gramt);
        }else {
            Grant grant=grantRepository.findObject(gramt,Grant.class);
            grant.setGrant(true);
            grantRepository.updateObject(grant);
        }

        resp.sendRedirect("AccountServlet");



    }
}
