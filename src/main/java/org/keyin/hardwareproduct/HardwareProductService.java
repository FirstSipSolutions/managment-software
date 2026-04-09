package org.keyin.hardwareproduct;

public class HardwareProductService {
    // Injecting the DAO for HardwareProduct gives us access to the database operations
    // related to HardwareProduct entities.
    HardwareProductDAO hardwareProductDAO = new HardwareProductDAO();

}

