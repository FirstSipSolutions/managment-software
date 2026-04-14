package org.keyin.tickets;

// importing built logger
// reusable and fast, this was one of our first steps in this project.

// more imports below it
// was missing this DB import and thorwing errors

import org.keyin.database.DatabaseConnection;

import org.keyin.customlogger.CustomLogger;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class TicketDAO {
    private CustomLogger logger = new CustomLogger();

    public void addTicket(Ticket ticker) throws SQLException {

    }

    // Returns all tickets — Admin use
    public List<Ticket> getAllTickets() throws SQLException {
        List<Ticket> tickets = new ArrayList<>();
        return tickets;


    }

    /*
    this returns the ticket used by employee
     */
    public List<Ticket> getTicketsByUser(int userId) throws SQLException {
        List<Ticket> tickets = new ArrayList<>();

        return tickets;

    }

    /*
here this constructor returns the oopen tickets
 this is used by the Technictian
 the next 2 constructors will be for the tech !
 */

    public List<Ticket> getOpenTickets() throws SQLException{
        List<Ticket> tickets = new ArrayList<>();

        return tickets;
    }

    public void updateTicketStatus(int TickerId, String newStatus) throws SQLException {

    }

        /*
        this will handle the tickets deleted by ID
        that will be ADMIN who uses this constructor
 */

    public void deleteTicket( int TicketId) throws SQLException{

    }
            /*
            here as the last constructor sits so far
             this will be to map DB rows to the ticket object
            */


    // update this was set to public casusing error so corrected to private


    private Ticket mapRow(ResultSet rs) throws SQLException{
        return new Ticket();
    }
}

