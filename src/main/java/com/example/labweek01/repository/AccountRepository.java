package com.example.labweek01.repository;

import com.example.labweek01.models.Account;
import com.example.labweek01.models.Role;
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
        System.out.println(id);
        System.out.println(pass);
        String sql = "SELECT * FROM account WHERE account_id = :id AND password = :pass";
        Account account = session.createNativeQuery(sql, Account.class)
                .setParameter("id", id)
                .setParameter("pass", pass)
                .getSingleResult();
        tr.commit();
        return account;
    }catch (Exception e) {
        e.printStackTrace();
        return  null;
    }

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

    public List<Account> getAccountByRoleID(String roleid){
        System.out.println("role id la"+ roleid);
        Transaction tr=null;
        try(Session session=sesssionFactory.openSession()){
            tr=session.beginTransaction();
            String sql = "SELECT a.account_id, a.full_name,a.password,a.email,a.phone,a.`status`\n" +
                    "FROM account a\n" +
                    "JOIN grant_access ga ON a.account_id = ga.account_id\n" +
                    "JOIN role r ON ga.role_id = r.role_id\n" +
                    "WHERE r.role_id = :id ;";
            List<Account> account = session.createNativeQuery(sql, Account.class).setParameter("id",roleid).getResultList();

            tr.commit();
            return account;
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            tr.rollback();
        }
        return null;
    }

    public Account getByAccountId(String id) {
        Transaction tr=null;
        try(Session session=sesssionFactory.openSession()){
            tr=session.beginTransaction();
            Account account=session.get(Account.class,id);
            tr.commit();
            return account;
        }catch (Exception e) {

        }
        return null;
    }

    public Role getRoleByID(String role) {
        Transaction tr=null;
        try(Session session=sesssionFactory.openSession()){
            tr=session.beginTransaction();
            Role role1=session.get(Role.class,role);
            tr.commit();
            return role1;
        }catch (Exception e) {

        }
        return null;
    }
}
