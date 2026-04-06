package org.keyin.gymproduct;

public class GymProductService {
    // Injecting the DAO for GymProduct gives us access to the database operations
    // related to GymProduct entities.
    GymProductDAO gymProductDAO = new GymProductDAO();

}
