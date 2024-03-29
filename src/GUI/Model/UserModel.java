package GUI.Model;

import BE.User;
import BLL.UserManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class UserModel {

    private ObservableList<User> userToBeViewed;

    private UserManager userManager;

    public UserModel() throws SQLException {
        userManager = new UserManager();

        userToBeViewed = FXCollections.observableArrayList();
        userToBeViewed.addAll(userManager.getAllUsers());
    }

    public ObservableList<User> getObservableUsers() {
        return userToBeViewed;
    }

    public void createUser(String name, String email, String password, String username){
        User user = new User(name, email, password, username);

        userManager.createUser(user);

        userToBeViewed.add(user);
    }

    public void deleteUser(User user){
        userManager.deleteUser(user);

        userToBeViewed.remove(user);
    }

    public void updateUser(User user){

    }

}
