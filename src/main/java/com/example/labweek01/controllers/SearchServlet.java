package com.example.labweek01.controllers;

import com.example.labweek01.models.Account;
import com.example.labweek01.models.Grant;
import com.example.labweek01.models.Role;
import com.example.labweek01.repository.AccountRepository;
import com.example.labweek01.repository.GrantRepository;
import com.example.labweek01.repository.RoleRepository;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SearchServlet", value = "/SearchServlet")
public class SearchServlet extends HttpServlet {

    private AccountRepository accountRepository;

    public SearchServlet() {
        accountRepository = new AccountRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String filter=req.getParameter("filter");
        if(filter.equals("all")){
            List<Account> accounts=accountRepository.getAllAccount();
            req.setAttribute("accounts",accounts);
        }else if(filter.equals("admin")){
            List<Account> accounts=accountRepository.getAccountByRoleID("admin");
            req.setAttribute("accounts",accounts);
        }else if(filter.equals("user")){
            List<Account> accounts=accountRepository.getAccountByRoleID("user");
            req.setAttribute("accounts",accounts);

        }
        HttpSession httpSession=req.getSession();
        Account account=(Account) httpSession.getAttribute("info");
        req.setAttribute("info",account);
        req.getRequestDispatcher("dashboard.jsp").forward(req,resp);


    }


    @Override

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name=req.getParameter("name");
        String email=req.getParameter("email");
        String phone=req.getParameter("phone");
        String password=req.getParameter("password");
        String id=req.getParameter("id");
        boolean status=req.getParameter("status").equals("Hoạt động")?true:false;
        Account account=new Account(id,name,password,email,phone,status);
        accountRepository=new AccountRepository();
        accountRepository.addObject(account);
        String role=req.getParameter("role");
        RoleRepository roleRepository=new RoleRepository();
        Role role1=roleRepository.findRoleByID(role);
        GrantRepository grantRepository=new GrantRepository();
        Grant grant=new Grant(role1,account,true,"");
        grantRepository.addObject(grant);

        resp.sendRedirect("AccountServlet");

    }
}
