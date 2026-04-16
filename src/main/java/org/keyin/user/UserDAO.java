package org.keyin.user;

import org.keyin.customlogger.CustomLogger;
import org.keyin.database.DatabaseConnection;
import org.keyin.serviceplans.ServicePlan;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    CustomLogger logger = new CustomLogger();
    User users = new User();
    // remapped this to username from user_name

    public User getUserByUsername(String user_name) throws SQLException {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user_name);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getInt("id"),
                            rs.getString("username"),
                            rs.getString("password_hash"),
                            rs.getString("email"),
                            rs.getString("phone_number"),
                            rs.getString("address"),
                            rs.getString("user_role")
                    );
                }
            }
        }
        return null;
    }


    // added this to compile the help desk program
    // was just missing the addUser


    public void addUser(User user) throws SQLException {
        String sql = "INSERT INTO users (username, password_hash, email, phone_number, address, user_role) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUser_name());
            pstmt.setString(2, user.getUser_password());
            pstmt.setString(3, user.getUser_email());
            pstmt.setString(4, user.getUser_phone());
            pstmt.setString(5, user.getUser_address());
            pstmt.setString(6, user.getUser_role());
            pstmt.executeUpdate();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        String sql = "SELECT * FROM users";
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet result = pstmt.executeQuery();
            while(result.next()){
                int userId = result.getInt("user_id");
                String username = result.getString("username");
                String email = result.getString("email");
                String phoneNumber = result.getString("phone_number");
                String address = result.getString("address");
                String role = result.getString("role");
                users.add(new User(userId, username, email, phoneNumber, address, role));
            }
            logger.logInfo("Data added to user list successfully.");
        } catch (SQLException e) {
            logger.logError("Could not retrieve values from the database." + e.getMessage());
        }
        return users;
    }
}
