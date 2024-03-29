package BLL;

import BE.User;
import DAL.DB.UserDAO_DB;
import DAL.IUser;

import java.sql.SQLException;
import java.util.List;

public class UserManager {

    private IUser userDAO;

    public UserManager() throws SQLException {
        userDAO = new UserDAO_DB();
    }

    public List<User> getAllUsers(){
        return userDAO.getAllUsers();
    }

    public User createUser(User newUser){
        return userDAO.createUser(newUser);
    }

    public void deleteUser(User user){
        userDAO.deleteUser(user);
    }

    public void updateUser(User user){}

}
