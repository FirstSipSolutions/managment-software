package org.keyin.serviceplans;

// ServicePLanDAO is responsible for all database operations related to service plans.
public class ServiceDAO {


    /**
     * Example method for adding a membership to the database.
     * This method demonstrates how to use a prepared statement to insert a membership record.
     * It should take a Membership object as a parameter and insert its details into the database.
     *
     * Uncomment and update the method to use the actual Membership object and its fields.
     */
//    public void addMemberShip() throws SQLException {
//        String sql = "INSERT INTO service_plans (plan_type, plan_price, plan_description, date_purchased, user_id) VALUES (?, ?, ?, ?, ?)";
//        try (Connection conn = DatabaseConnection.getConnection();
//             PreparedStatement pstmt = conn.prepareStatement(sql)) {
//            pstmt.setString(1, ServicePlan.getPlanType());
//            pstmt.setInt(2, ServicePlan.getPlan_price());
//            pstmt.setDate(4, Date.valueOf(ServicePlan.getDatePurchased()));
//            pstmt.setInt(5, ServicePlan.getUser_id());
//            pstmt.executeUpdate();
//        }
//    }
}
