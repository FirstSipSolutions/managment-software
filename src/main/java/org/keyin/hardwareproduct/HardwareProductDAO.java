package org.keyin.hardwareproduct;

import org.keyin.customlogger.CustomLogger;
import org.keyin.database.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HardwareProductsDAO {

    // here is the custom logger that is made for reusing
    private CustomLogger logger = new CustomLogger();

    // using ? as placeholders we can prevent any sort of SQL injection

    // then the logger above will read success or fail
    // this is ADMIN use only

    public void addItem(HardwareProducts item) throws SQLException {
        String sql = "INSERT INTO hardware_inventory (item_name, item_type, item_price, quantity_in_stock) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, item.getItemName());
            pstmt.setString(2, item.getItemType());
            pstmt.setDouble(3, item.getItemPrice());
            pstmt.setInt(4, item.getQty_inStock());
            pstmt.executeUpdate();


            logger.logInfo("Hardware item added: " + item.getItemName());
        } catch (SQLException sqlException) {
            logger.logError("Adding hardware item failed: " + sqlException.getMessage());
        }
    }







    // todo: Returns all hardware items -- ALL ROLES
    // todo: note that the rs = executeQuery will send select to the DB and return the result set.

    public List<HardwareProducts> getAllItems() throws SQLException {
        List<HardwareProducts> items = new ArrayList<>();
        String sql = "SELECT * FROM hardware_inventory";




        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            // while loop comes into play to loop through the array ( this will populate the array )
            while (rs.next()) {
                items.add(mapRow(rs));
            }
            logger.logInfo("getAllItems returned with: " + items.size() + " items");

        } catch (SQLException sqlException) {

            logger.logError("Failed to get all hardware items: " + sqlException.getMessage());
        }
        return items;
    }
}

