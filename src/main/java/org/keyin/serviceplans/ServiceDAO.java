package org.keyin.serviceplans;

import org.keyin.customlogger.CustomLogger;
import org.keyin.database.DatabaseConnection;
import org.keyin.tickets.Ticket;
import org.keyin.user.User;

import java.security.Provider;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// ServicePLanDAO is responsible for all database operations related to service plans.
public class ServiceDAO {
    CustomLogger logger = new CustomLogger();

    /**
     * Example method for adding a service plan to the database.
     * This method demonstrates how to use a prepared statement to insert a service plan record.
     * It should take a service plan object as a parameter and insert its details into the database.
     * <p>
     * Uncomment and update the method to use the actual service plan object and its fields.
     */
    public void addServicePlan(ServicePlan servicePlan) {
        String sql = "INSERT INTO service_plans (plan_type, plan_description, plan_price, date_purchased, user_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            pstmt.setString(1, servicePlan.getPlanType());
            pstmt.setString(2, servicePlan.getPlanDescription());
            pstmt.setFloat(3, servicePlan.getPlanPrice());
            pstmt.setDate(4, Date.valueOf(servicePlan.getDatePurchased()));
            pstmt.setInt(5, servicePlan.getUserId());
            pstmt.executeUpdate();

            logger.logInfo("Service plan inserted into database successfully.");

        } catch (SQLException e) {

            logger.logError("Could not insert values into the database." + e.getMessage());
        }
    }

    public List<ServicePlan> getAllServicePlans() {
        List<ServicePlan> plans = new ArrayList<>();

        String sql = "SELECT * FROM service_plans";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql))
        {

            ResultSet result = pstmt.executeQuery();

            while(result.next()){
               String planType = result.getString("plan_type");
               String planDescription = result.getString("plan_description");
               Float planPrice = result.getFloat("plan_price");
               LocalDate datePurchased = result.getDate("date_purchased").toLocalDate();
               int userId = result.getInt("user_id");

                plans.add(new ServicePlan(planType, planDescription, planPrice, datePurchased, userId));
            }
            logger.logInfo("Data added to list successfully.");
            } catch (SQLException e) {
                logger.logError("Could not retrieve values from the database." + e.getMessage());
            }
        return plans;
    }

    public void deleteService(int planId) {
        String sql = "DELETE FROM service_plans WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            pstmt.setInt(1, planId);
            int rowsDeleted = pstmt.executeUpdate();

            if (rowsDeleted > 0) {

                logger.logInfo("Service deleted, ID: " + planId);

            } else {

                logger.logError("Delete failed. No service found with ID: " + planId);

            }

        } catch (SQLException e) {

            logger.logError("SQL error deleting service, ID: " + planId + ", " + e.getMessage());

        }
    }

    public void updateService(ServicePlan servicePlan) {
        String sql = "UPDATE service_plans SET plan_type = ?, plan_description = ?, plan_price = ? WHERE id = ?";

        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, servicePlan.getPlanType());
            pstmt.setString(2, servicePlan.getPlanDescription());
            pstmt.setFloat(3, servicePlan.getPlanPrice());

            pstmt.setInt(4, servicePlan.getPlanId());
            int rowsUpdated = pstmt.executeUpdate();


            if (rowsUpdated > 0) {

                logger.logInfo("Service information updated, ID: " + servicePlan.getPlanId());

            } else {

                logger.logError("Update failed. No service found with ID: " + servicePlan.getPlanId());
            }
        }
        catch (SQLException e){

            logger.logError("SQL, error updating user id " + servicePlan.getPlanId() + "," + e.getMessage());
        }
    }

    // added getTOatal revenue here - as per final touches missed this
    // will doc changes


    public double getTotalRevenue() throws SQLException {
        String sql = "SELECT SUM(plan_price) AS total FROM service_plans";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);

             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getDouble("total");
            }
        }


        return 0;
    }
}
