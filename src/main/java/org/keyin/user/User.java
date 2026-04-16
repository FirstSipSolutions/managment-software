package org.keyin.user;

//*
// This is the parent class for all users, There are 3 types of users: Technician, Employee, and Admin
//
//*



public class User {
    // Instance variables


    private Integer user_id;
    private String user_name;
    private String user_password;
    private String user_role;



    /*
    this was added in as an adjustment to params
    requiring email, address and phone number
    */


    private String user_email;
    private String user_phone;
    private String user_address;

    public User(){

    }

    public User(int user_id){
        this.user_id = user_id;
    }


    public User(String user_name, String user_password, String user_email, String user_phone, String user_address, String user_role) {
        this.user_name = user_name;
        this.user_password = user_password;
        this.user_email = user_email;
        this.user_phone = user_phone;
        this.user_address = user_address;
        this.user_role = user_role;
    }
    // added here EMAIL & PHONE & ADDRESS
    // noting where this took place for documenting later

    public User(Integer user_id, String user_name, String user_password, String user_email, String user_phone, String user_address, String user_role) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_password = user_password;
        this.user_email = user_email;
        this.user_phone = user_phone;
        this.user_address = user_address;
        this.user_role = user_role;
    }

    // Constructor to return user information without the password
    public User(Integer user_id, String user_name, String user_email, String user_phone, String user_address, String user_role){
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_email = user_email;
        this.user_phone = user_phone;
        this.user_address = user_address;
        this.user_role = user_role;
    }

    public String getUser_role() {
        return user_role;
    }

    public void setUser_role(String user_role) {
        this.user_role = user_role;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    /*
        here are the getters and setters added for the altered
        params and constructors
  */
    public String getUser_email() { return user_email; }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_address() {
        return user_address;
    }

    public void setUser_address(String user_address) {
        this.user_address = user_address;
    }
}
