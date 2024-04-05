package BLL;

import BE.User;
import DAL.DB.UserDAO_DB;
import DAL.IUser;
import org.mindrot.jbcrypt.BCrypt;

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

    public void updateUser(User user){
        userDAO.updateUser(user);
    }

    public User authenticateUser(String username, String password) {
        User user= userDAO.getPasswordByUsername(username);

        if (user != null) {
            String storedPassword = user.getPassword();
            if (storedPassword == null) {
                // User has no password, return the user
                return user;
            } else if (BCrypt.checkpw(password, storedPassword)) {
                // Passwords match, return the authenticated user
                return user;
            }
        }

        // Authentication failed
        return null;
    }

    public boolean usernameExists(String username){
       return userDAO.usernameExists(username);
    }

    public boolean emailExists(String email){
        return userDAO.emailExists(email);
    }

    public void updatePassword(String username, String newPassword) {
        userDAO.updatePassword(username, newPassword);
    }

}
