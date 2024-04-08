package DAL.DB;

import BE.Positions;
import DAL.IPosition;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PositionDAO_DB implements IPosition {


    private MyDatabaseConnector databaseConnector;

    public PositionDAO_DB() throws SQLException {
        databaseConnector = new MyDatabaseConnector();
    }

    @Override
    public List<Positions> getAllPositions() {

        ArrayList<Positions> allPositions = new ArrayList<>();

        try (Connection conn = databaseConnector.getConnection();
             Statement stmt = conn.createStatement()) {

            String sql = "SELECT * FROM dbo.Position";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

                String name = rs.getString("position");
                int id = rs.getInt("id");


                Positions positions = new Positions(id, name);
                allPositions.add(positions);
            }
            return allPositions;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}



