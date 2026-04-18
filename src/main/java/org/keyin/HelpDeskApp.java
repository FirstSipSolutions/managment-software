package org.keyin;

import org.keyin.customlogger.CustomLogger;
import org.keyin.serviceplans.ServicePlan;
import org.keyin.serviceplans.ServicePlanService;
import org.keyin.user.User;
import org.keyin.user.UserService;
import org.keyin.tickets.TicketService;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;


import org.keyin.tickets.Ticket;

public class HelpDeskApp {
    public static void main(String[] args) throws SQLException {
        // Initialize services
        UserService userService = new UserService();
        ServicePlan servicePlan = new ServicePlan();
        ServicePlanService servicePlanService = new ServicePlanService();
        TicketService ticketService = new TicketService();
        User user = new User();

        // CustomLogger method to log information and errors to a text file.
        CustomLogger logger = new CustomLogger();

        // Scanner for user input
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            logger.logInfo("App started");
            System.out.println("\n === IT Help Desk Management System ===");
            System.out.println("\n1. Add a new user");
            System.out.println("2. Login as a user");
            System.out.println("9. Exit");
            System.out.print("\nEnter your choice: ");

            // Validate input
            while (!scanner.hasNextInt()) {
                System.out.println("\nInvalid input! Please enter a number.");
                scanner.next();
            }

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addNewUser(scanner, userService);
                    break;
                case 2:
                    logInAsUser(scanner, userService, servicePlanService, ticketService, logger, servicePlan);
                    break;
                case 9:
                    System.out.println("\nExiting the program...");
                    logger.logInfo("Program closed");
                    break;
                default:
                    System.out.println("\nInvalid choice! Please select a valid option.");
                    logger.logError("User entered invalid option.");
            }
        } while (choice != 9);

        scanner.close();
    }

    private static void logInAsUser(Scanner scanner, UserService userService, ServicePlanService servicePlanService, TicketService ticketService, CustomLogger logger, ServicePlan servicePlan) {
        System.out.print("\nEnter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        try {
            User user = userService.loginForUser(username, password);
            if (user != null) {
                System.out.println("\nLogin Successful! Welcome " + user.getUser_name());
                switch (user.getUser_role().toLowerCase()) {
                    case "admin":
                        showAdminMenu(scanner, user, userService, servicePlanService, ticketService, servicePlan);
                        break;
                    case "technician":
                        showTechnicianMenu(scanner, user, userService, ticketService, servicePlanService, logger);
                        break;
                    case "employee":
                        showEmployeeMenu(scanner, user, userService, servicePlanService, ticketService);
                        break;
                    default:

                        break;
                }
            } else {
                System.out.println("Login Failed! Invalid credentials.");
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while logging in.");
            e.printStackTrace();
        }
    }

    // Employee menu
    private static void showEmployeeMenu(Scanner scanner, User user, UserService userService, ServicePlanService servicePlanService, TicketService ticketService) {
        int choice;

        do {
            System.out.println("\n =====================");
            System.out.println(" === Employee Menu ===");
            System.out.println(" =====================");
            System.out.println("\n   Choose an option");
            System.out.println("\n1. View Service Plans");
            System.out.println("2. Submit a ticket");
            System.out.println("3. View my ticket");
            System.out.println("9. Logout");
            System.out.print("\nEnter your choice: ");

            // Validate input
            while (!scanner.hasNextInt()) {
                System.out.println("\nInvalid input! Please enter a number.");
                scanner.next();
            }

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    try {
                        List<ServicePlan> plans = servicePlanService.getAllServicePlans();
                        System.out.println("\nService Plans");
                        System.out.println("-------------");
                        for (ServicePlan plan : plans) {
                            System.out.println("Plan type: " + plan.getPlanType() + ". " + "Description: " + plan.getPlanDescription() + ". " + "$" + plan.getPlanPrice());
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 2:
                    submitTicket(scanner, user, ticketService);
                    break;
                case 3:
                    viewMyTickets(user, ticketService);
                    break;
                case 9:
                    System.out.println("\nLogging out, leaving employee menu...");
                    break;
                default:
                    System.out.println("\nInvalid choice! Please select a valid option.");
            }
        } while (choice != 9);

    }


    // Technician menu
    private static void showTechnicianMenu(Scanner scanner, User user, UserService userService, TicketService ticketService, ServicePlanService servicePlanService, CustomLogger logger) {
        int choice;

        do {
            System.out.println("\n ======================");
            System.out.println(" === Technician Menu ===");
            System.out.println(" =======================");
            System.out.println("\nChoose an option:");
            System.out.println("\n1. View all Service Plans");
            System.out.println("2. View open tickets");
            System.out.println("3. Claim a ticket");
            System.out.println("9. Logout");
            System.out.print("\nEnter your choice: ");

            // Validate input
            while (!scanner.hasNextInt()) {
                System.out.println("\nInvalid input! Please enter a number.");
                scanner.next();
            }

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    try {
                        List<ServicePlan> plans = servicePlanService.getAllServicePlans();
                        for (ServicePlan plan : plans) {
                            System.out.println("\n" + plan);
                        }
                    } catch (SQLException e) {
                        logger.logInfo("No service plans to display" + e.getMessage());
                    }
                    break;
                case 2:
                    System.out.println("TODO: Add View open tickets method");
                    break;
                case 3:
                    System.out.println("TODO: Add Claim a ticket method");
                    break;
                case 9:
                    System.out.println("\nLogging out, leaving technician menu...");
                    break;
                default:
                    System.out.println("\nInvalid choice! Please select a valid option.");
            }
        } while (choice != 9);
    }

    // Admin menu with minimal implementation
    private static void showAdminMenu(Scanner scanner, User user, UserService userService, ServicePlanService servicePlanService, TicketService ticketService, ServicePlan servicePlan) {
        int choice;

        do {
            System.out.println("\n ==================");
            System.out.println(" === Admin Menu ===");
            System.out.println(" ==================");
            System.out.println("\n  Choose an option");
            System.out.println("\n1. Add a Service Plan");
            System.out.println("2. Update Service Plan");
            System.out.println("3. Delete Service Plan");
            System.out.println("4. View all Service Plans");
            System.out.println("5. Add user");
            System.out.println("6. Update user");
            System.out.println("7. Delete user");
            System.out.println("8. View all users");
            System.out.println("9. Add a ticket");
            System.out.println("10. Update a ticket");
            System.out.println("11. Delete a ticket");
            System.out.println("12. View all tickets");
            System.out.println("13. Add a product");
            System.out.println("14. Update a product");
            System.out.println("15. Delete a product");
            System.out.println("16. View all products");
            System.out.println("17. View total stock value");
            System.out.println("18. View total revenue");
            System.out.println("20. Logout");
            System.out.println();
            System.out.print("Enter your choice: ");

            // Validate input
            while (!scanner.hasNextInt()) {
                System.out.println("\nInvalid input! Please enter a number.");
            }

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addServicePlan(scanner, servicePlanService);
                    break;
                case 2:
                    updateServicePlan(scanner, servicePlanService);
                    break;
                case 3:
                    deleteServicePlan(scanner, servicePlanService);
                    break;
                case 4:
                    try {
                        List<ServicePlan> plans = servicePlanService.getAllServicePlans();
                        for (ServicePlan plan : plans) {
                            System.out.println("\n" + plan);
                        }
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    addNewUser(scanner, userService);
                    break;
                case 6:
                    updateUser(scanner, userService);
                    break;
                case 7:
                    deleteUser(scanner, userService);
                    break;
                case 8:
                    List<User> users = userService.getAllUsers();
                    for (User userlist : users) {
                        System.out.println("\n" + userlist);
                    }
                    break;
                case 9:
                    System.out.println("TODO: Add a ticket");
                    break;
                case 10:
                    System.out.println("TODO: Update a ticked");
                    break;
                case 11:
                    System.out.println("TODO: Delete a ticked");
                    break;
                case 12:
                    System.out.println("TODO: View all tickets");
                    break;
                case 13:
                    System.out.println("TODO: Add product");
                    break;
                case 14:
                    System.out.println("TODO: Update a product");
                    break;
                case 15:
                    System.out.println("TODO: Delete a product");
                    break;
                case 16:
                    System.out.println("TODO: View all products");
                    break;
                case 17:
                    System.out.println("TODO: View total stock value");
                    break;
                case 18:
                    System.out.println("TODO: View total revenue");
                case 20:
                    System.out.println("\nlogging out, leaving admin menu...");
                    break;
                default:
                    System.out.println("\nInvalid choice! Please select a valid option.");
            }
        } while (choice != 20);
    }

    // User methods start here
    // Minimal implementation of adding a new user
    private static void addNewUser(Scanner scanner, UserService userService) {
        System.out.print("\nEnter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter phone: ");
        String phone = scanner.nextLine();
        System.out.print("Enter address: ");
        String address = scanner.nextLine();
        System.out.print("Enter role (ADMIN/TECHNICIAN/EMPLOYEE): ");
        String role = scanner.nextLine();

        User user = new User(username, password, email, phone, address, role);
        try {
            userService.addUser(user);
            System.out.println("\nUser added successfully!");
        } catch (SQLException e) {
            System.out.println("\nError adding user: " + e.getMessage());
        }
    }

    private static void deleteUser(Scanner scanner, UserService userService) {
        System.out.print("Enter user id: ");
        int userId = scanner.nextInt();
        try {
            userService.deleteUser(userId);
            System.out.println("User deleted, Id: " + userId);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    private static void updateUser(Scanner scanner, UserService userService) {
        System.out.print("\nEnter the ID of the user you want to update: ");
        int userId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter new username: ");
        String username = scanner.nextLine();
        System.out.print("Enter new password: ");
        String password = scanner.nextLine();
        System.out.print("Enter new email: ");
        String email = scanner.nextLine();
        System.out.print("Enter new phone: ");
        String phone = scanner.nextLine();
        System.out.print("Enter new address: ");
        String address = scanner.nextLine();
        System.out.print("Enter new role (ADMIN/TECHNICIAN/EMPLOYEE): ");
        String role = scanner.nextLine();

        User updatedUser = new User(username, password, email, phone, address, role);

        updatedUser.setUser_id(userId);

        try {
            userService.updateUser(updatedUser);
            System.out.println("\nUpdate request completed for User ID: " + userId);
        } catch (SQLException e) {
            System.out.println("\nError updating user: " + e.getMessage());
        }
    }

    // Service methods start here
    private static void addServicePlan(Scanner scanner, ServicePlanService servicePlanService) {

        System.out.println("\nEnter plan type: ");
        String planType = scanner.nextLine();
        System.out.println("Enter plan description: ");
        String planDescription = scanner.nextLine();
        System.out.println("Enter plan price: ");
        Float planPrice = scanner.nextFloat();
        LocalDate datePurchased = LocalDate.now();
        System.out.println("Enter user ID: ");
        int userId = scanner.nextInt();
        scanner.nextLine();

        ServicePlan servicePlan = new ServicePlan(planType, planDescription, planPrice, datePurchased, userId);
        try {
            servicePlanService.addServicePlan(servicePlan);
            System.out.println("Plan added successfully!");
        } catch (SQLException e) {
            System.out.println("Error adding plan: " + e.getMessage());
        }

    }

    private static void deleteServicePlan(Scanner scanner, ServicePlanService servicePlanService){

        System.out.print("Enter plan id: ");
        int planId = scanner.nextInt();

        try {
            servicePlanService.deleteService(planId);
            System.out.println("Service plan deleted, Id: " + planId);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void updateServicePlan(Scanner scanner, ServicePlanService servicePlanService) {
        System.out.print("\nEnter the ID of the service plan you want to update: ");
        int planId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter new plan type: ");
        String planType = scanner.nextLine();
        System.out.print("Enter new description: ");
        String description = scanner.nextLine();
        System.out.print("Enter new plan price: ");
        Float planPrice = scanner.nextFloat();
        scanner.nextLine();


        ServicePlan updatedServicePlan = new ServicePlan(planId, planType, description, planPrice);

        try {
            servicePlanService.updateService(updatedServicePlan);
            System.out.println("\nUpdate request completed for Service plan ID: " + planId);
        } catch (SQLException e) {
            System.out.println("\nError updating service plan: " + e.getMessage());
        }
    }
        // added submitTIcket portion here
        // ticket submission needs a handler and method
        // this is adding the ticket service Subticket method

        private static void submitTicket(Scanner scanner, User user, TicketService ticketService ) {

            System.out.print("\nEnter ticket title: ");
            String title = scanner.nextLine();


            System.out.print("Enter description: ");
            String description = scanner.nextLine();

            System.out.print("Enter category (Hardware/Software/Network/Account Access): ");
            String category = scanner.nextLine();
            System.out.print("Enter priority (Low/Medium/High/Critical): ");
            String priority = scanner.nextLine();


            Ticket ticket = new Ticket(title, description, category, priority, "Open", user.getUser_id());

            try {


                ticketService.subTicket(ticket);
                System.out.println(" Ticket submitted successfully!");
            } catch (SQLException e) {
                System.out.println(" Error submitting ticket: " + e.getMessage());


            }


        }
                private static void viewMyTickets(User user, TicketService ticketService) {


        try {
            List<Ticket> tickets = ticketService.getMyTicket(user.getUser_id());
            if (tickets.isEmpty()) {
                System.out.println(" No tickets found.");
                return;
            }


            System.out.println("\nYour Tickets");
            System.out.println(" ============ ");
            for (Ticket t : tickets) {
                System.out.println("ID: " + t.getTicket_id() + " : " + t.getTitle() + " : Status: " + t.getStatus() + " Priority: " + t.getPriority());
            }




        } catch (SQLException e) {
            System.out.println("Error retrieving tickets: " + e.getMessage());
        }
    }









}
