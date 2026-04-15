package org.keyin.serviceplans;

import org.keyin.customlogger.CustomLogger;

import java.sql.SQLException;
import java.util.List;

/**
 * Service class is responsible for handling all business logic related to service plans.
 * This class interacts with the ServiceDAO to perform database operations.
 * The DAO should be injected into this service to access its methods.
 * Example usage:
 *   ServiceDAO dao = new ServiceDAO();
 *   // Use dao within service methods
 */
public class ServicePlanService {

    CustomLogger logger = new CustomLogger();
    ServiceDAO dao = new ServiceDAO();

    public void addServicePlan(ServicePlan servicePlan) throws SQLException{
            dao.addServicePlan(servicePlan);
    }

    public List<ServicePlan> getAllServicePlans() throws SQLException {
        return dao.getAllServicePlans();
    }

}
