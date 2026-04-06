package org.keyin.memberships;

import org.keyin.customlogger.CustomLogger;
import org.keyin.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// MembershipDAO is responsible for all database operations related to memberships.
public class MembershipDAO {


    /**
     * Example method for adding a membership to the database.
     * This method demonstrates how to use a prepared statement to insert a membership record.
     * It should take a Membership object as a parameter and insert its details into the database.
     *
     * Uncomment and update the method to use the actual Membership object and its fields.
     */
//    public void addMemberShip() throws SQLException {
//        String sql = "INSERT INTO memberships (membershiptype, membership_price, membership_description, date_purchased, user_id) VALUES (?, ?, ?, ?, ?)";
//        try (Connection conn = DatabaseConnection.getConnection();
//             PreparedStatement pstmt = conn.prepareStatement(sql)) {
//            pstmt.setString(1, membership.getMembershipType());
//            pstmt.setInt(2, membership.getMembership_price());
//            pstmt.setDate(4, Date.valueOf(membership.getDatePurchased()));
//            pstmt.setInt(5, membership.getUser_id());
//            pstmt.executeUpdate();
//        }
//    }
}
