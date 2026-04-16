package org.keyin;

import org.keyin.customlogger.CustomLogger;
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
        System.out.print("Enter username: ");
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
                        showEmployeeMenu(scanner, user, userService, servicePlanService);
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
    private static void showEmployeeMenu(Scanner scanner, User user, UserService userService, ServicePlanService servicePlanService) {
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
                    System.out.println("TODO: Add Submit a ticket method");
                    break;
                case 3:
                    System.out.println("TODO: Add View my ticket");
                    break;
                case 9:
                    System.out.println("Logging out, leaving employee menu...");
                    break;
                default:
                    System.out.println("Invalid choice! Please select a valid option.");
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
                    System.out.println("Logging out, leaving technician menu...");
                    break;
                default:
                    System.out.println("Invalid choice! Please select a valid option.");
            }
        } while (choice != 9);
    }

    // Admin menu with minimal implementation
    private static void showAdminMenu(Scanner scanner, User user, UserService userService, ServicePlanService servicePlanService, TicketService ticketService) {
        int choice;

        do {
            System.out.println("\n==================");
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
            System.out.print("\n Enter your choice: ");

            // Validate input
            while (!scanner.hasNextInt()) {
                System.out.println("\nInvalid input! Please enter a number.");
                scanner.next();
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
                    System.out.println("TODO: Add Delete user method");
                    break;
                case 6:
                    System.out.println("TODO: Add View total stock value method");
                    break;
                case 7:
                    System.out.println("TODO: Add View total revenue method");
                    break;
                case 9:
                    System.out.println("logging out, leaving admin menu...");
                    break;
                default:
                    System.out.println("Invalid choice! Please select a valid option.");
            }
        } while (choice != 9);
    }

    // Minimal implementation of adding a new user
    private static void addNewUser(Scanner scanner, UserService userService) {
        System.out.print("Enter username: ");
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
            System.out.println("User added successfully!");
        } catch (SQLException e) {
            System.out.println("Error adding user: " + e.getMessage());
        }
    }

    private static void addServicePlan(Scanner scanner, ServicePlanService servicePlanService) {
        System.out.println("Enter plan type: ");
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
}
