package com.example.labweek01.controllers;

import com.example.labweek01.models.Account;
import com.example.labweek01.models.Role;
import com.example.labweek01.repository.AccountRepository;
import com.example.labweek01.repository.GrantRepository;
import com.example.labweek01.repository.RoleRepository;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = "/ControlServlet")

public class ControlServlet extends HttpServlet {
    AccountRepository accountRepository;
    GrantRepository grantRepository;

    RoleRepository roleRepository;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action").toString();
        if (action.equalsIgnoreCase("login")) {
            String id=req.getParameter("id");
            String password = req.getParameter("password");
            accountRepository = new AccountRepository();
            Account account = accountRepository.findAccountByIDAndPassword(id, password);

            if (account == null) {
                PrintWriter writer = resp.getWriter();
                writer.println("Khong tim thay");
            } else {
                grantRepository = new GrantRepository();
                if (grantRepository.isRole("admin", account.getAccountID())) {
                    resp.sendRedirect("dashboard.html");
                    HttpSession session= req.getSession();
                    session.setAttribute("info",account);
                } else {

                }
            }
        }else if(action.equalsIgnoreCase("all")){
            List<Account> accounts=accountRepository.getAllAccount();
            Gson gson = new Gson();
            String json = gson.toJson(accounts);

            PrintWriter out = resp.getWriter();
            out.print(json);
            out.flush();
        }else if(action.equalsIgnoreCase("info")){
            HttpSession httpSession=req.getSession();
            Account account= (Account) httpSession.getAttribute("info");
            System.out.println(account);
            Gson gson=new Gson();
            String json=gson.toJson(account);
            PrintWriter out=resp.getWriter();
            out.print(json);
            out.flush();
        }else if(action.equalsIgnoreCase("getRole")){
            roleRepository=new RoleRepository();
            HttpSession session= req.getSession();;
            Account acc=(Account) session.getAttribute("info");
            List<Role> roles=roleRepository.findRolesByIDAcc(acc.getAccountID()).get();
            Gson gson=new Gson();
            String json=gson.toJson(roles);
            PrintWriter out=resp.getWriter();
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
        System.out.println(id);
        accountRepository=new AccountRepository();
        Account acc=new Account();
        acc.setAccountID(id);
        if(accountRepository.removeObject(acc)){
            resp.setStatus(HttpServletResponse.SC_ACCEPTED);

        }else{
            resp.sendRedirect("dashboard.html");
        }
    }
}

