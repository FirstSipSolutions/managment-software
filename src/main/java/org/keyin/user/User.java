package org.keyin.user;

//*
// This is the parent class for all users, There are 3 types of users: Technician, Employee, and Admin
//
// *//
public class User {
    // Instance variables
    private Integer user_id;
    private String user_name;
    private String user_password;
    private String user_role;

    public User(){
    }

    public User(String user_name, String user_password, String user_role) {
        this.user_name = user_name;
        this.user_password = user_password;
        this.user_role = user_role;
    }

    public  User(Integer user_id, String user_name, String user_password, String user_role){
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_password = user_password;
            if(user_role.equals("Admin") || user_role.equals("Technician") || user_role.equals("Employee")){
                this.user_role = user_role;
            }
            else{
                System.out.println("Error: User role must be Admin, Technician or Employee");
            }
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
}
