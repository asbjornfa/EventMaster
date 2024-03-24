package DAL.DB;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class MyDatabaseConnector {
    private static final String PROP_FILE = "config/config.settings";

    private SQLServerDataSource dataSource;

    public MyDatabaseConnector() throws SQLException {
        Properties databaseProperties = new Properties();
        try {
            databaseProperties.load(new FileInputStream((PROP_FILE)));

            dataSource = new SQLServerDataSource();
            dataSource.setServerName(databaseProperties.getProperty("Server"));
            dataSource.setDatabaseName(databaseProperties.getProperty("Database"));
            dataSource.setUser(databaseProperties.getProperty("User"));
            dataSource.setPassword(databaseProperties.getProperty("Password"));
            dataSource.setPortNumber(1433);
            dataSource.setTrustServerCertificate(true);
        } catch (Exception e) {
            throw new SQLException("Could not get connection to database", e);
        }
    }



    public Connection getConnection() throws SQLException {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public static void main(String[] args) throws SQLException {

        MyDatabaseConnector databaseConnector = new MyDatabaseConnector();

        try (Connection connection = databaseConnector.getConnection()) {

            System.out.println("Is it open? " + !connection.isClosed());
        } catch (SQLException e){
            throw new SQLException(e);
        }
        //Connection gets closed here
    }
}

