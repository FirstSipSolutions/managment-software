package org.keyin.tickets;

// importing built logger
// reusable and fast, this was one of our first steps in this project.

// more imports below it
// was missing this DB import and thorwing errors

import org.keyin.database.DatabaseConnection;

import org.keyin.customlogger.CustomLogger;
import org.keyin.user.User;

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
                    throw sqlException;
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
            logger.logInfo("getAllTickets returned with: " + tickets.size() + " tickets");
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
            logger.logError("Failed to get the ticket for user " + userId + " " + sqlException.getMessage());
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
            pstmt.executeUpdate();
            logger.logInfo("Ticket " + ticketId + " status updated to " + newStatus);


        }catch (SQLException sqlException){
            logger.logError("Failed to update ticket please try again, your ticket number you tried was " + ticketId + " " + sqlException.getMessage());
        }
    }

    public boolean claimTicket(int ticketId, int assignedTo) {

        String sql = "UPDATE tickets SET assigned_to = ?, status = 'In Progress' WHERE ticket_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setInt(1, assignedTo);
            pstmt.setInt( 2, ticketId);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                logger.logInfo("Ticket " + ticketId + " claimed by User ID: " + assignedTo);
                return true;
            } else {
                logger.logError("Failed to claim: Ticket ID " + ticketId + " does not exist.");
                return  false;
            }

        }catch (SQLException sqlException){
            logger.logError("Failed to claim ticket please try again, the ticket number you tried was " + ticketId + " " + sqlException.getMessage());
            return false;
        }
    }

         // this will handle the tickets deleted by ID
        //todo: this will be used by ADMIN







    public void deleteTicket( int ticketId) throws SQLException{


        String sql = "DELETE FROM tickets WHERE id = ?";



        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setInt( 1, ticketId);
            pstmt.executeUpdate();
            logger.logInfo("Ticket deleted " + ticketId );


        }catch (SQLException sqlException){
            logger.logError("Failed to delete ticket please try again, your ticket number you tried was " + ticketId + " " + sqlException.getMessage());
        }
    }






             //todo: this maps DB rows to the ticket object


    private Ticket mapRow(ResultSet rs) throws SQLException{

        return new Ticket(

                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("description"),
                rs.getString("category"),
                rs.getString("priority"),
                rs.getString("status"),
                rs.getInt("submitted_by"),
                rs.getInt("assigned_to"),
                rs.getString("date_opened") != null ? rs.getDate("date_opened").toLocalDate() : null,
                rs.getDate("date_resolved") != null ? rs.getDate("date_resolved").toLocalDate() : null





        );
    }
}

