package org.keyin;

import org.keyin.customlogger.CustomLogger;
import org.keyin.serviceplans.ServicePlan;
import org.keyin.serviceplans.ServicePlanService;
import org.keyin.user.User;
import org.keyin.user.UserService;
import org.keyin.tickets.TicketService;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

public class HelpDeskApp {
    public static void main(String[] args) throws SQLException {
        // Initialize services
        UserService userService = new UserService();
        ServicePlanService servicePlanService = new ServicePlanService();
        TicketService ticketService = new TicketService();

        // CustomLogger Object called and test
        CustomLogger logger = new CustomLogger();
        logger.logInfo("App started");

        // Scanner for user input
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== Help Desk Management System ===");
            System.out.println("1. Add a new user");
            System.out.println("2. Login as a user");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            // Validate input
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.next();
            }

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addNewUser(scanner, userService);
                    break;
                case 2:
                    logInAsUser(scanner, userService, servicePlanService, ticketService);
                    break;
                case 3:
                    System.out.println("Exiting the program...");
                    break;
                default:
                    System.out.println("Invalid choice! Please select a valid option.");
            }
        } while (choice != 3);

        scanner.close();
    }

    private static void logInAsUser(Scanner scanner, UserService userService, ServicePlanService servicePlanService, TicketService ticketService) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        try {
            User user = userService.loginForUser(username, password);
            if (user != null) {
                System.out.println();
                System.out.println("Login Successful! Welcome " + user.getUser_name());
                switch (user.getUser_role().toLowerCase()) {
                    case "admin":
                        showAdminMenu(scanner, user, userService, servicePlanService, ticketService);
                        break;
                    case "technician":
                        showTechnicianMenu(scanner, user, userService, ticketService);
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
        }
        catch (SQLException e) {
            System.out.println("An error occurred while logging in.");
            e.printStackTrace();
        }
    }

    // Placeholder for employee menu
    private static void showEmployeeMenu(Scanner scanner, User user, UserService userService,ServicePlanService servicePlanService) {
        int choice;

        do {
            System.out.println();
            System.out.println("=== Employee Menu ===");
            System.out.println("Choose an option:");
            System.out.println("1. Purchase a Service Plan");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");

            // Validate input
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.next();
            }

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addServicePlan(scanner, servicePlanService);
                    break;
                case 9:
                    System.out.println("Leaving employee menu...");
                    break;
                default:
                    System.out.println("Invalid choice! Please select a valid option.");
            }
        } while (choice != 9);

    }


    // Placeholder for Technician menu
    private static void showTechnicianMenu(Scanner scanner, User user, UserService userService, TicketService ticketService) {
        System.out.println("Technician menu under construction.");
    }

    // Admin menu with minimal implementation
    private static void showAdminMenu(Scanner scanner, User user, UserService userService, ServicePlanService servicePlanService, TicketService ticketService) {
        System.out.println("Admin menu under construction.");
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

    private static void addServicePlan(Scanner scanner, ServicePlanService servicePlanService){
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
        try{
            servicePlanService.addServicePlan(servicePlan);
            System.out.println("Plan added successfully!");
        } catch(SQLException e){
            System.out.println("Error adding plan: " + e.getMessage());
        }
    }
        }
