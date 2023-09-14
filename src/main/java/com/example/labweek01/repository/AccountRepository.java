package com.example.labweek01.repository;

import com.example.labweek01.models.Account;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.*;
import java.util.List;

public class AccountRepository extends GenericCRUD<Account> {
//    private static final String JDBC_URL = "jdbc:mariadb://localhost:3306/mydb";
//    private static final String JDBC_USER = "root";
//    private static final String JDBC_PASSWORD = "12345678";
//
//    public static Connection getConnection() {
//        Connection connection = null;
//        try {
//            // Đăng ký MariaDB Driver
//            Class.forName("org.mariadb.jdbc.Driver");
//
//            // Tạo kết nối đến cơ sở dữ liệu
//            connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        }
//        return connection;
//    }
//
//    public Account findAccountByIdAndPassword(String accountID, String password) {
//        Account account = null;
//        String sql = "SELECT * FROM account WHERE account_id = ? AND password = ?";
//
//        try (Connection conn = getConnection();
//             PreparedStatement pstmt = conn.prepareStatement(sql)) {
//
//            pstmt.setString(1, accountID);
//            pstmt.setString(2, password);
//
//            try (ResultSet rs = pstmt.executeQuery()) {
//                if (rs.next()) {
//                    // Lấy dữ liệu từ ResultSet và tạo đối tượng Account
//                    String name = rs.getString("name");
//                    String email = rs.getString("email");
//                    String phone = rs.getString("phone");
//                    boolean status = rs.getBoolean("status");
//
//                    account = new Account(accountID, name, password, email, phone, status);
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return account;
//    }
public Account findAccountByIDAndPassword(String id,String pass) {
    Transaction tr=null;
    try(Session session=sesssionFactory.openSession()){
        tr=session.beginTransaction();

        String sql = "SELECT * FROM account WHERE account_id = :id AND password = :pass";
        Account account = session.createNativeQuery(sql, Account.class)
                .setParameter("id", id)
                .setParameter("pass", pass)
                .getSingleResult();
        tr.commit();
        return account;
    }catch (Exception e) {
        // TODO: handle exception
        e.printStackTrace();
        tr.rollback();
    }
    return null;
}


    public List<Account> getAllAccount() {
        Transaction tr=null;
        try(Session session=sesssionFactory.openSession()){
            tr=session.beginTransaction();

            String sql = "SELECT * FROM account ;";
            List<Account> account = session.createNativeQuery(sql, Account.class).getResultList();

            tr.commit();
            return account;
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            tr.rollback();
        }
        return null;
    }
}
