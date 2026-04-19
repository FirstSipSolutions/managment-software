# IT Help Desk System Technical Guide

## Architecture Overview
The application follows a three-layer architecture:

Console UI (HelpDeskApp) handles all user interaction through the Scanner class and routes input to the appropriate service.

Service Layer (UserService, TicketService, ServicePlanService, HardwareProductsService) contains business logic and wraps DAO calls.

DAO Layer (UserDAO, TicketDAO, ServiceDAO, HardwareProductsDAO) handles all database operations using PreparedStatements to prevent SQL injection.

Database: PostgreSQL stores users, tickets, service plans, and hardware inventory in four related tables.

## Class Design
The User class is the parent for all user roles. Employee, Technician, and Admin extend User with constructors passing to super.

Interfaces (CanSubmitTickets, CanManageTickets, CanManageSystem) define role contracts. Any class that implements an interface must provide the required methods.

Model classes (Ticket, ServicePlan, HardwareProducts) represent the data structure of each entity.

DAO classes handle SQL CRUD operations and map database rows to model objects.

Service classes sit between the UI and DAO layers to handle business logic like password hashing and validation.

PasswordUtil provides static methods for hashing and verifying passwords using BCrypt.

CustomLogger writes info and error messages to applicationlogs.txt with timestamps.

## Database Design
Four tables with foreign key relationships:

users: stores user credentials and role
service_plans: linked to users by user_id
tickets: linked to users twice, once for submitted_by and once for assigned_to
hardware_inventory: standalone inventory table

## Dependencies
Java 23 (or compatible)
PostgreSQL JDBC driver 42.7.5
jBCrypt 0.4 for password hashing
JUnit Jupiter 5.10.0 for unit tests

## Logging
All database operations log to applicationlogs.txt.
Info logs confirm successful operations (ticket added, user deleted, etc).
Error logs capture exceptions with their message for debugging.
Logger is instantiated at the top of each DAO class and reused across all methods.

## Running Tests
Right click on PasswordUtilTest and select Run.
Tests cover password hashing, correct password match, and wrong password rejection.