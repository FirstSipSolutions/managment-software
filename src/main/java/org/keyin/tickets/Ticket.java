package org.keyin.tickets;

import java.time.LocalDate;

public class Ticket {

 // variables here

 private int ticket_id;

 private String title;
 private String description;
 private String priority;
 private String status;
 private String category;

 // submitted by
private int submittedBy;

// asigned to user

 private int assignedTo;

 //dates opened and resolved

 private LocalDate dateOpened;
 private LocalDate dateClosed;


// here is the default comnstructor for the ticket

 public Ticket(){

 }

/*
 * Here is the full constructor for the ticket creation
 * this will include creaqtion of ticket without id or dates
 * this does NOT include the local date in the params of this constructor as it will be called with .now()
 */


 public Ticket(String title, String description, String category, String priority, String status, int submittedBy){

  this.title = title;
  this.description = description;
  this.category = category;
  this.priority = priority;
  this.status = status;
  this.submittedBy = submittedBy;
  this.dateOpened = LocalDate.now();

 }

 // adding in the full rebuild of ticket from before only this will include the id and DB info


 public Ticket(int ticket_id, String title, String description, String category, String priority, String status, int submittedBy, int assignedTo, LocalDate dateOpened, LocalDate dateClosed) {
  this.ticket_id = ticket_id;
  this.title = title;
  this.description = description;


  this.category = category;
  this.priority = priority;
  this.status = status;


  this.submittedBy = submittedBy;
  this.assignedTo = assignedTo;
  this.dateOpened = dateOpened;
  this.dateClosed = dateClosed;
 }

/*
getters and setters added after the building of constructors
 */

 public int getTicket_id() {
  return ticket_id;
 }

 public void setTicket_id(int ticket_id) {
  this.ticket_id = ticket_id;
 }

 public String getTitle() {
  return title;
 }

 public void setTitle(String title) {
  this.title = title;
 }

 public String getDescription() {
  return description;
 }

 public void setDescription(String description) {
  this.description = description;
 }

 public String getPriority() {
  return priority;
 }

 public void setPriority(String priority) {
  this.priority = priority;
 }

 public String getStatus() {
  return status;
 }

 public void setStatus(String status) {
  this.status = status;
 }

 public String getCategory() {
  return category;
 }

 public void setCategory(String category) {
  this.category = category;
 }

 public int getSubmittedBy() {
  return submittedBy;
 }

 public void setSubmittedBy(int submittedBy) {
  this.submittedBy = submittedBy;
 }

 public int getAssignedTo() {
  return assignedTo;
 }

 public void setAssignedTo(int assignedTo) {
  this.assignedTo = assignedTo;
 }

 public LocalDate getDateOpened() {
  return dateOpened;
 }

 public void setDateOpened(LocalDate dateOpened) {
  this.dateOpened = dateOpened;
 }

 public LocalDate getDateClosed() {
  return dateClosed;
 }

 public void setDateClosed(LocalDate dateClosed) {
  this.dateClosed = dateClosed;
 }
}
