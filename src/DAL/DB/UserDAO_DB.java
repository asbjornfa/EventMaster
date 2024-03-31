package DAL.DB;

import BE.User;
import DAL.IUser;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO_DB implements IUser {

    private MyDatabaseConnector databaseConnector;

    public UserDAO_DB() throws SQLException {
        databaseConnector = new MyDatabaseConnector();
    }

    @Override
    public List<User> getAllUsers() {
        ArrayList<User> allUser = new ArrayList<>();

        try (Connection conn = databaseConnector.getConnection();
             Statement stmt = conn.createStatement()) {

            String sql = "SELECT * FROM dbo.Users";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

                String name = rs.getString("name");
                String email = rs.getString("email");
                String username = rs.getString("username");
                int id = rs.getInt("id");


                User user = new User(id, name, email, username);
                allUser.add(user);
            }
            return allUser;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User createUser(User user) {

        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());

        String sql =
                "INSERT INTO dbo.Users (name, email, password, username) VALUES (?,?,?,?)";

        try (Connection conn = databaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Bind parameters
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, hashedPassword);
            stmt.setString(4, user.getUsername());

            // Execute the SQL statement to delete the movie
            stmt.executeUpdate();

            User createdUser = new User(user.getName(), user.getEmail(), hashedPassword, user.getUsername());

            return createdUser;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteUser(User user) {
        String sql = "DELETE FROM dbo.Users WHERE Id = (?);";

        try (Connection conn = databaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Bind parameters
            stmt.setInt(1, user.getId());

            // Run the specified SQL statement
            stmt.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateUser(User user) {
    }

    public User getUserByUsername(String username, String password) {
        String sql = "SELECT password FROM dbo.Users WHERE username = ?";

        try (Connection conn = databaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Bind parameters
            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String storedPassword = rs.getString("password");

                return new User(username, storedPassword);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null; // Return null if user not found
    }


    public void updatePassword(String username, String newPassword){

        String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());

        String sql = "UPDATE dbo.Users SET password = ? WHERE username = ?";

        try (Connection conn = databaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, hashedPassword);
            stmt.setString(2, username);

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}