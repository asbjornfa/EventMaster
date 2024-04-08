package BLL;

import BE.Positions;
import DAL.DB.PositionDAO_DB;
import DAL.IPosition;

import java.sql.SQLException;
import java.util.List;

public class PositionManager {

    private IPosition positionDAO;

    public PositionManager() throws SQLException {
        positionDAO = new PositionDAO_DB();
    }

    public List<Positions> getAllPositions(){
        return positionDAO.getAllPositions();
    }

}
