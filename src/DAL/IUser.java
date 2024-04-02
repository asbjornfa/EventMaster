package DAL;

import BE.User;

import java.util.List;

public interface IUser {

    public List<User> getAllUsers();

    public User createUser(User user);

    public void deleteUser(User user);

    public void updateUser(User user);

    public User getPasswordByUsername(String username);

    public boolean usernameExists(String username);

    public boolean emailExists(String email);

    public void updatePassword(String username, String newPassword);


}
