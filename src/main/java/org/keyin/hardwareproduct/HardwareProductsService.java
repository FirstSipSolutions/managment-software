package org.keyin.hardwareproduct;

import java.sql.SQLException;

public class HardwareProductsService {



    // still not the best at getting these to work
    // this will give us access to the DB
    HardwareProductDAO hardwareProductsDAO = new HardwareProductDAO();


    // adding an item
    // admin user
    public void addItem(HardwareProducts item) throws SQLException {

        hardwareProductsDAO.addItem(item);
    }

    // role config for all roles


    public list <HardwareProducts> getAllItems() throws SQLException {

       return hardwareProductsDAO.getAllItems();
    }

    public double getTotalStockValue() throws SQLException {
        return hardwareProductsDAO.getTotalStockValue();
    }


    public void deleteItem(int itemId) throws SQLException {
        hardwareProductsDAO.deleteItem();
    }


}

