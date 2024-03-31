package DAL;

import BE.User;

import java.util.List;

public interface IUser {

    public List<User> getAllUsers();

    public User createUser(User user);

    public void deleteUser(User user);

    public void updateUser(User user);

    public User getUserByUsername(String username, String password);

    public void updatePassword(String username, String newPassword);

}
