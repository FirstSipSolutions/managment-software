package org.keyin;

import org.keyin.customlogger.CustomLogger;
import org.keyin.serviceplans.ServiceDAO;
import org.keyin.serviceplans.ServicePlan;
import org.keyin.serviceplans.ServicePlanService;
import org.keyin.user.User;
import org.keyin.user.UserDAO;
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
                    logInAsUser(scanner, userService, servicePlanService, ticketService, logger);
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

    private static void logInAsUser(Scanner scanner, UserService userService, ServicePlanService servicePlanService, TicketService ticketService, CustomLogger logger) {
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
                        showAdminMenu(scanner, user, userService, servicePlanService, ticketService);
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
                    viewOpenTickets(ticketService);
                    break;
                case 3:
                    updateTicketStatus(scanner, ticketService);

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
    private static void showAdminMenu(Scanner scanner, User user, UserService userService, ServicePlanService servicePlanService, TicketService ticketService) {
        int choice;

        do {
            System.out.println("\n ==================");
            System.out.println(" === Admin Menu ===");
            System.out.println(" ==================");
            System.out.println("\n  Choose an option");
            System.out.println("\n1. Add a Service Plan");
            System.out.println("2. View all Service Plans");
            System.out.println("3. View all users");
            System.out.println("4. View all tickets");
            System.out.println("5. Delete user");
            System.out.println("6. View total stock value");
            System.out.println("7. View total revenue");
            System.out.println("9. Logout");
            System.out.println();
            System.out.print("Enter your choice: ");

            // Validate input
            while (!scanner.hasNextInt()) {
                System.out.println("\nInvalid input! Please enter a number.");
            }

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addServicePlan(scanner, servicePlanService);
                    break;
                case 2:
                    try {
                        List<ServicePlan> plans = servicePlanService.getAllServicePlans();
                        for (ServicePlan plan : plans) {
                            System.out.println("\n" + plan);
                        }
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                        List<User> users = userService.getAllUsers();
                        for (User userlist : users) {
                            System.out.println("\n" + userlist);
                        }
                    break;
                case 4:
                    System.out.println("TODO: Add View all tickets method");
                    break;
                case 5:
                        deleteUser(scanner, userService);
                    break;
                case 6:
                    System.out.println("TODO: Add View total stock value method");
                    break;
                case 7:
                    System.out.println("TODO: Add View total revenue method");
                    break;
                case 9:
                    System.out.println("\nlogging out, leaving admin menu...");
                    break;
                default:
                    System.out.println("\nInvalid choice! Please select a valid option.");
            }
        } while (choice != 9);
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

    private static void deleteUser(Scanner scanner, UserService userService){
        System.out.print("Enter user id: ");
        int userId = scanner.nextInt();
        try{
            userService.deleteUser(userId);
            System.out.println("User deleted, Id: " + userId);
        } catch(SQLException e){
            System.out.println(e.getMessage());
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

// view open ti cket method


    private static void viewOpenTickets(TicketService ticketService) {
        try {
            List<Ticket> tickets = ticketService.getOpenTickets();

            if (tickets.isEmpty()) {

                System.out.println("No open tickets.");
                return;
            }
            System.out.println("Open Tickets");
            System.out.println("==========");

            for (Ticket t : tickets) {
                System.out.println("ID: " + t.getTicket_id() + " : " + t.getTitle() + "  Priority: " + t.getPriority() + "  Submitted by: " + t.getSubmittedBy());
            }
        } catch (SQLException e) {
            System.out.println(nError retrieving open tickets: " + e.getMessage());
        }
    }






}
