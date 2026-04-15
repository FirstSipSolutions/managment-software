package org.keyin.tickets;


import java.sql.SQLException;
import java.util.List;




public class TicketService {


    private TicketDAO ticketDAO = new TicketDAO();



  // employee submitts new ticket entry

    public void subTicket(Ticket ticket) throws SQLException {
        ticketDAO.addTicket(ticket);
    }


    // view all via admin permission

    public List<Ticket> getAllTickets() throws SQLException {
        return ticketDAO.getAllTickets();
    }


   // employee views their own tickets only
    public List<Ticket> getMyTicket(int userId) throws SQLException {
        return ticketDAO.getTicketsByUser(userId);
    }

    // technician views open tickets
    public List<Ticket> getOpenTickets() throws SQLException {
        return ticketDAO.getOpenTickets();
    }


    // technician updates ticket status
    public void updateStatus(int ticketId, String newStatus) throws SQLException {
        ticketDAO.updateTicketStatus(ticketId, newStatus);
    }

    // admin deletes ticket
    public void deleteTicket(int ticketId) throws SQLException {
        ticketDAO.deleteTicket(ticketId);
    }










}
