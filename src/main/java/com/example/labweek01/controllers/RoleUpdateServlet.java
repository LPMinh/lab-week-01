package com.example.labweek01.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import com.example.labweek01.models.Account;
import com.example.labweek01.repository.AccountRepository;
import com.example.labweek01.repository.GrantRepository;
import com.example.labweek01.repository.RoleRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "RoleUpdateServlet", value = "/RoleUpdateServlet")
public class RoleUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve parameters from the request
        String userId = request.getParameter("userId");
        String[] rolesArray = request.getParameterValues("roles");

        // Convert rolesArray to a list (if not null)
        List<String> roles = (rolesArray != null) ? Arrays.asList(rolesArray) : null;

        // Call the function to update user roles
        GrantRepository grantRepository=new GrantRepository();
        grantRepository.addRole(roles.get(0),userId);



        // Respond to the client
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.println("Roles updated successfully");
    }

    // Dummy method to simulate updating user roles (replace this with your actual logic)
    private boolean updateUserRoles(String userId, List<String> roles) {
        // Implement your logic to update roles in the database or wherever
        // For this example, let's assume the update is always successful
        return true;
    }
}