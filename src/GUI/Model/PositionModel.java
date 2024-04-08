package GUI.Model;

import BE.Positions;
import BE.User;
import BLL.PositionManager;
import BLL.UserManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class PositionModel {

    private ObservableList<Positions> positionsToBeViewed;

    private PositionManager positionManager;

    public PositionModel() throws SQLException {
        positionManager = new PositionManager();

        positionsToBeViewed = FXCollections.observableArrayList();
        positionsToBeViewed.addAll(positionManager.getAllPositions());
    }

    public ObservableList<Positions> getObservablePositions() {

        return positionsToBeViewed;
    }
}
