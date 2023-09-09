package com.example.labweek01.repository;

import java.sql.*;

public class GrantRepository {
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

    public String findRoleByAccountId(String accountId) {
        String role = null;
        String sql = "SELECT role_id FROM grant_access WHERE account_id = ?";
        Connection connection = getConnection();
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, accountId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    role = rs.getString("role_id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return role;
    }

}
