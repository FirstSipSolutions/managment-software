package org.keyin.user;

import org.keyin.utils.PasswordUtil;

import java.sql.SQLException;

public class UserService {

    private UserDAO userDAO = new UserDAO();

    public UserService(){
    }

    public UserService(String username, String password) throws SQLException {

    }

    public User loginForUser(String username, String password) throws SQLException{
        User user = userDAO.getUserByUsername(username);


        // added calling on passwordUtil class to initiate BCrypt through that class and handle passwords
        // this user.getUser_password().equals(password))
        // was removed to add bycrypt checking the password via password util



        // this takes plain text pw and compares that against the BCrypt hash that is stored in the DB
        // returning boolean true value if passing correctly

        if (user != null && PasswordUtil.checkPassword(password, user.getUser_password())){
            return user;
        }
        return null;
    }


    // added hashing here in the sql execption
    // user added affter hash accepted


    public void addUser(User user) throws SQLException{



        String hashed = PasswordUtil.hashPassword(user.getUser_password());
        user.setUser_password(hashed);
        userDAO.addUser(user);



    }
}
