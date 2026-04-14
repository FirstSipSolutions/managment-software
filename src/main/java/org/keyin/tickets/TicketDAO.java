package org.keyin.tickets;

// importing built logger
// reusable and fast, this was one of our first steps in this project.

// more imports below it
// was missing this DB import and thorwing errors

import org.keyin.database.DatabaseConnection;

import org.keyin.customlogger.CustomLogger;

import javax.xml.crypto.Data;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class TicketDAO {

    // here is the custom logger that is made for reusing

    private CustomLogger logger = new CustomLogger();



    // using ? as placeholders we can prevent any sort of SQL injection
    // each ? gets filled in with a setInt this happens in order
    // then the logger above will read success or fail

    public void addTicket(Ticket ticket) throws SQLException {

         String sql = "INSERT INTO tickets(title, description, category, priority, status, submitted_by, date_opened)  VALUES(?,?,?,?,?,?,?)";

                    // todo: this could be issued as lambda, industry would accept it as such but blending that with jdbc looks complex and not worth trying here
                    // todo: pstmt = prepared statment shortened

                    // todo: executeUpdate(); will fire the INSERT off to the DB

                try ( Connection conn = DatabaseConnection.getConnection();
                      PreparedStatement pstmt = conn.prepareStatement(sql)) {

                          pstmt.setString(1, ticket.getTitle());
                          pstmt.setString(2, ticket.getDescription());
                          pstmt.setString(3, ticket.getCategory());
                          pstmt.setString(4, ticket.getPriority());
                          pstmt.setString(5, ticket.getStatus());
                          pstmt.setInt(6, ticket.getSubmittedBy());
                          pstmt.setDate(7, Date.valueOf(LocalDate.now()));
                          pstmt.executeUpdate();


        }       catch (SQLException sqlException) {

                    logger.logError("Adding Ticket Failed: " + sqlException.getMessage());

                }


    }

    // todo: Returns all tickets — ADMIN USE

    // todo: note that the rs = executeQuery will send select to the DB and return the result set.



    public List<Ticket> getAllTickets() throws SQLException {
        List<Ticket> tickets = new ArrayList<>();


        String sql = "SELECT * FROM tickets";
        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            

            ResultSet rs = pstmt.executeQuery()){

              // while loop comes into play to loop through the array ( this will populate the array )

        while(rs.next()) {

            tickets.add(mapRow(rs));

        }
            logger.logError("getAllTickets returned with: " + tickets.size() + " tickets");
        } catch (SQLException sqlException) {
            logger.logError("Failed To Get All Tickets " + sqlException.getMessage());
        }
                  return tickets;

    }








            // todo: returning a ticket from a user - USER ACCESS HERE




    public List<Ticket> getTicketsByUser(int userId) throws SQLException {


        List<Ticket> tickets = new ArrayList<>();

        String sql = "SELECT * FROM tickets WHERE submitted_by = ?";


        // todo: NOTE prepared vs prepare is a uh oh ,, spent too long trying to debug a missing "d" 
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1,userId );

        try (ResultSet rs = pstmt.executeQuery()){
            while(rs.next()){
                tickets.add(mapRow(rs));
            }
        }

        logger.logInfo("getTicketsByUser returned as: " + tickets.size() + " tickets for user " + userId);

        }catch ( SQLException sqlException){
            logger.logError("Failed to get the ticket for user " + userID + " " + sqlException.getMessage());
        }


        return tickets;

    }




//
//here this constructor returns ONLY the open tickets
// todo: this is used by the Technician



    public List<Ticket> getOpenTickets() throws SQLException{
        List<Ticket> tickets = new ArrayList<>();


        String sql = "SELECT * FROM tickets WHERE  status = 'Open'";

        try ( Connection conn = DatabaseConnection.getConnection();
              PreparedStatement pstmt = conn.prepareStatement(sql);

              ResultSet rs = pstmt.executeQuery()){

            while(rs.next()) {
                tickets.add(mapRow(rs));
            }

            // loggers here
            logger.logInfo("getOpenTickets returned " + tickets.size() + " opened tickets");


        } catch (SQLException sqlException) {
            logger.logError("ERROR getOpenTickets failed " + sqlException.getMessage());
        }

        return tickets;
    }








//todo: updating ticket status: technicians use this



    public void updateTicketStatus(int ticketId, String newStatus) throws SQLException {

        String sql = "UPDATE tickets SET status = ? WHERE id = ?";


        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setString(1, newStatus);
            pstmt.setInt( 2, ticketId);


        }
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

