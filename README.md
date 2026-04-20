

# IT Help Desk Management System

A console based IT help desk system built in Java with PostgreSQL.
Users can submit support tickets, subscribe to service plans, and manage hardware inventory based on their role.

Built by FirstSip Solutions as the Semester 3 Java final project at Keyin College. Team of 2 developers.

## Features
Role based access control for Admin, Technician, and Employee
BCrypt password hashing for secure login
Full CRUD operations on users, tickets, service plans, and hardware
PostgreSQL database integration
Custom logger that writes to applicationlogs.txt
Interface contracts enforcing role responsibilities
Unit tests on password utility

## Tech Stack
Java 23

PostgreSQL

Maven

jBCrypt

JUnit 5

## Getting Started

### Prerequisites
Java 23 or compatible JDK

PostgreSQL running on localhost port 5432

Maven installed

IntelliJ IDEA or VS Code

## Setup Instructions
1. Clone the repository from GitHub
2. Open the project in IntelliJ or VS Code
3. Install PostgreSQL and ensure it is running on port 5432
4. Create a database named ithelpdesksystem
5. Run scripts.sql to create tables and seed data
6. Update DatabaseConnection.java with your local database credentials
7. Run HelpDeskApp.java main method

## Documentation
User Guide: docs/user-guide.md
Technical Guide: docs/technical-guide.md
ERD: docs/erd.md
Class Diagram: docs/class-diagram.md
AI Usage: docs/ai-usage.md

## Default Seed Users
Password for all seed users is Password1!

admin01 / Password1! (Admin)
tech01 / Password1! (Technician)
employee01 / Password1! (Employee)

## AI Usage
AI was used throughout the build as a learning and productivity tool. It helped explain unfamiliar concepts like interfaces, BCrypt, and the DAO pattern, reviewed code for bugs, and drafted documentation. <br>

All code was reviewed and understood before being committed. Full breakdown in docs/ai-usage.md.

## Team
Built by a team of 2 

