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

    public void updateUser(User user){}

    public User authenticateUser(String username, String password) {
        User user = userDAO.getUserByUsername(username, password);

        if (user != null) {
            String storedHashedPassword = user.getPassword();

            if (BCrypt.checkpw(password, storedHashedPassword)) {
                // Passwords match, return the authenticated user
                return user;
            }
        }

        // Authentication failed
        return null;
    }

    public void updatePassword(String username, String newPassword) {
        userDAO.updatePassword(username, newPassword);
    }

}
