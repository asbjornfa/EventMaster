package BLL;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class UserManagerTest {

    @Test
    void authenticateUser() throws SQLException {

        // Triple A pattern.
        // Arrange - setup our test object etc.
        UserManager user = new UserManager();

        // Act - do the actual method run.
        String actualvalue = String.valueOf(user.authenticateUser("Asbjornfa", "googlemaster123"));
        String expectedValue = "googlemaster123";

        // Assert - check if actual val is equal to expected value.
        Assertions.assertNotNull(actualvalue);
    }
}