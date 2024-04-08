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

            String sql = "SELECT * FROM dbo.Users u INNER JOIN dbo.Position p ON u.positionId = p.id";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

                String name = rs.getString("name");
                String email = rs.getString("email");
                String username = rs.getString("username");
                String position = rs.getString("position");
                int id = rs.getInt("id");


                User user = new User(id, name, email, username, position);
                allUser.add(user);
            }
            return allUser;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User createUser(User user) {

        String sql =
                "INSERT INTO dbo.Users (name, email, username, positionId) VALUES (?,?,?,?)";

        try (Connection conn = databaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getUsername());
            stmt.setInt(4,user.getPositionId());


            stmt.executeUpdate();


            return user;
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

        System.out.println("Received user information for update:");
        System.out.println("Name: " + user.getName());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Username: " + user.getUsername());
        System.out.println("positionId: " + user.getPositionId());
        System.out.println("userId: " + user.getId());

        String sql = "UPDATE dbo.Users SET name = ?, email = ?, username = ?, positionId = ? WHERE id = ?";

        try (Connection conn = databaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getUsername());
            stmt.setInt(4, user.getPositionId());
            stmt.setInt(5, user.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public String getPositionFromUser(String username) {
        String position = null; // Declare position outside try block

        String sql = "SELECT p.position FROM Users u JOIN Position p ON u.positionId = p.id WHERE u.username = ?";

        try (Connection conn = databaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                position = rs.getString("position");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return position;
    }

    @Override
    public User getPasswordByUsername(String username) {
        String sql = "SELECT password FROM dbo.Users WHERE username = ?";

        try (Connection conn = databaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String storedPassword = rs.getString("password");

                return new User(username,storedPassword);
                }
            } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null; // Return null if user not found
    }

    @Override
    public boolean usernameExists(String username){

        String sql = "SELECT username FROM dbo.Users WHERE username = ?";

        try (Connection conn = databaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();

            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean emailExists(String email){

        String sql = "SELECT email FROM dbo.Users WHERE email = ?";

        try (Connection conn = databaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();

            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
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