package org.keyin.user;

import java.sql.SQLException;

public class UserService {

    private UserDAO userDAO = new UserDAO();

    public UserService(){
    }

    public UserService(String username, String password) throws SQLException {

    }

    public User loginForUser(String username, String password) throws SQLException{
        User user = userDAO.getUserByUsername(username);
        if (user != null && user.getUser_password().equals(password)) {
            return user;
        }
        return null;
    }

    public void addUser(User user) throws SQLException{
    }
}
