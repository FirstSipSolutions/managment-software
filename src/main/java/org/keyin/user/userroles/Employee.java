package org.keyin.user.userroles;

import org.keyin.user.User;



public class Employee extends User {


    public Employee(String user_name, String user_password, String user_email, String user_phone, String user_address, String user_role) {


        super( user_name, user_password,  user_email,  user_phone,  user_address,  user_role);


    }
}


