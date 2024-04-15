package BE;

import javafx.geometry.Pos;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionsTest {

    @Test
    void getId() {
        // Arrange
        int expectedId = 123;
        Positions positions = new Positions(expectedId, "Admin");

        // Act
        int actualId = positions.getId();

        // Assert
        assertEquals(expectedId, actualId);
    }

    @Test
    void getPosition() {
        // Arrange
        String expectedPosition = "Admin";
        Positions positions = new Positions(123, expectedPosition);

        // Act
        String actualPosition = positions.getPosition();

        // Assert
        assertEquals(expectedPosition, actualPosition);
    }

    @Test
    void setPosition() {
        Positions positions = new Positions(123, "Udvikler");

        String forventetPosition = "Manager";

        positions.setPosition(forventetPosition);

        assertEquals(forventetPosition, positions.getPosition());
    }
}