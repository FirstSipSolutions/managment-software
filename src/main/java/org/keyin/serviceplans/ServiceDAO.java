package org.keyin.serviceplans;

import org.keyin.customlogger.CustomLogger;
import org.keyin.database.DatabaseConnection;

import java.security.Provider;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// ServicePLanDAO is responsible for all database operations related to service plans.
public class ServiceDAO {
    CustomLogger logger = new CustomLogger();

    /**
     * Example method for adding a service plan to the database.
     * This method demonstrates how to use a prepared statement to insert a service plan record.
     * It should take a service plan object as a parameter and insert its details into the database.
     *
     * Uncomment and update the method to use the actual service plan object and its fields.
     */
    public void addServicePlan(ServicePlan servicePlan) {
        String sql = "INSERT INTO service_plans (plan_type, plan_description, plan_price, date_purchased, user_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, servicePlan.getPlanType());
            pstmt.setString(2, servicePlan.getPlanDescription());
            pstmt.setFloat(3, servicePlan.getPlanPrice());
            pstmt.setDate(4, Date.valueOf(servicePlan.getDatePurchased()));
            pstmt.setInt(5, servicePlan.getUserId());
            pstmt.executeUpdate();
            logger.logInfo("Data inserted into database successfully");
        } catch(SQLException e){
            logger.logError("Could not insert values into the database");
            e.printStackTrace();
        }
    }
}
