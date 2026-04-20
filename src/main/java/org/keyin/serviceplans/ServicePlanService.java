package org.keyin.serviceplans;

import org.keyin.customlogger.CustomLogger;
import org.keyin.user.User;

import java.sql.PreparedStatement;
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

    ServiceDAO serviceDAO = new ServiceDAO();

    public void addServicePlan(ServicePlan servicePlan) throws SQLException{

            serviceDAO.addServicePlan(servicePlan);

    }

    public List<ServicePlan> getAllServicePlans() throws SQLException {

        return serviceDAO.getAllServicePlans();

    }

    public void deleteService(int serviceId) throws SQLException {

        serviceDAO.deleteService(serviceId);

    }

    public void updateService(ServicePlan servicePlan) throws SQLException{

        serviceDAO.updateService(servicePlan);

    }

    public double getTotalRevenue() throws SQLException {
        return serviceDAO.getTotalRevenue();
    }


}
