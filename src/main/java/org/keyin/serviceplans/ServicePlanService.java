package org.keyin.serviceplans;

/**
 * Service class is responsible for handling all business logic related to service plans.
 * This class interacts with the ServiceDAO to perform database operations.
 * The DAO should be injected into this service to access its methods.
 * Example usage:
 *   ServiceDAO dao = new ServiceDAO();
 *   // Use dao within service methods
 */
public class ServicePlanService {

    ServiceDAO dao = new ServiceDAO();



}
