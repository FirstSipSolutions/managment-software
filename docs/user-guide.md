# IT Help Desk System User Guide

## About
The IT Help Desk System is a console application for managing IT support tickets, service plans, hardware inventory, and users. Built by FirstSip Solutions.

## Getting Started
1. Launch the application
2. From the main menu, choose either:
   Option 1 to register a new user
   Option 2 to log in with existing credentials
3. After login, you will see a menu based on your role

## User Roles
Employee: submits tickets, views their own tickets, views service plans
Technician: views open tickets, updates ticket status, claims tickets
Admin: manages users, service plans, hardware inventory, views reports

## Employee Guide

### Submit a Ticket
1. Log in as an Employee
2. Select option 2 (Submit a ticket)
3. Enter ticket title (e.g. "Cannot log into VPN")
4. Enter description
5. Select category: Hardware, Software, Network, or Account Access
6. Select priority: Low, Medium, High, or Critical
7. Ticket is created with status Open

### View Your Tickets
1. Select option 3 (View my ticket)
2. List displays with ID, title, status, and priority

## Technician Guide

### View Open Tickets
1. Log in as Technician
2. Select option 2 (View open tickets)
3. List shows all unresolved tickets

### Update Ticket Status
1. Select option 3 (Claim a ticket)
2. Enter ticket ID
3. Enter new status: Open, In Progress, Resolved, or Closed

## Admin Guide

### Manage Users
Option 5: Add user
Option 6: Update user
Option 7: Delete user
Option 8: View all users

### Manage Service Plans
Option 1: Add service plan
Option 2: Update plan
Option 3: Delete plan
Option 4: View all plans

### Reports
Option 17: Total stock value
Option 18: Total revenue

## Known Limitations
Console only, no web interface
Single-user session at a time
No email notifications
Passwords must be reset by an admin if forgotten