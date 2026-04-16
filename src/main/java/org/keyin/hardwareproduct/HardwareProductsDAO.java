


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


// todo: this will handle the total value of stock

// todo: this will be used by ADMIN for reporting

    public double getTotalStockValue() throws SQLException {
        String sql = "SELECT SUM(item_price * quantity_in_stock) AS total_value FROM hardware_inventory";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {


            if (rs.next()) {

                logger.logInfo("getTotalStockValue called");
                return rs.getDouble("total_value");
            }
        } catch (SQLException sqlException) {
            logger.logError("Failed to get total stock value: " + sqlException.getMessage());
        }
        return 0;
    }

// this will handle the item deleted by ID
// todo: this will be used by ADMIN

    public void deleteItem(int itemId) throws SQLException {
        String sql = "DELETE FROM hardware_inventory WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, itemId);
            pstmt.executeUpdate();

            logger.logInfo("Hardware item deleted: " + itemId);
        } catch (SQLException sqlException) {

            logger.logError("Failed to delete hardware item, item id you tried was: " + itemId + " " + sqlException.getMessage());
        }
    }

// todo: this maps DB rows to the HardwareProducts object

    private HardwareProducts mapRow(ResultSet rs) throws SQLException {
        return new HardwareProducts(

                rs.getInt("id"),
                rs.getString("item_name"),
                rs.getString("item_type"),
                rs.getDouble("item_price"),
                rs.getInt("quantity_in_stock")
        );
    }
}
