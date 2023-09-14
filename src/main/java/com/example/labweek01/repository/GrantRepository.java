package com.example.labweek01.repository;

import com.example.labweek01.models.Account;
import com.example.labweek01.models.Grant;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.*;
import java.util.List;

public class GrantRepository  extends  GenericCRUD<Grant>{
    private static final String JDBC_URL = "jdbc:mariadb://localhost:3306/mydb";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "sapassword";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            // Đăng ký MariaDB Driver
            Class.forName("org.mariadb.jdbc.Driver");

            // Tạo kết nối đến cơ sở dữ liệu
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    public List<Grant> getListGrantByID(String id){
        Transaction tr=null;
        try(Session session=sesssionFactory.openSession()){
            tr=session.beginTransaction();

            String sql = "SELECT * FROM grant_access WHERE account_id=:id ;";
            List<Grant> list = session.createNativeQuery(sql, Grant.class)
                    .setParameter("id", id).getResultList();

            tr.commit();
            return list;
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            tr.rollback();
        }
        return null;
    }
    public boolean isRole(String role,String id) {
       List<Grant> grants= getListGrantByID(id);
        for ( Grant grant:
             grants) {
            if(grant.getRole().getRoleID().equalsIgnoreCase(role)){
                return true;
            }
        }
        return false;
    }

}
