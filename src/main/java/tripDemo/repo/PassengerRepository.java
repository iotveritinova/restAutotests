package tripDemo.repo;

import tripDemo.db.BaseConnection;
import tripDemo.dictionaries.ServiceEnum;
import tripDemo.model.Passenger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PassengerRepository {
   /* private final Connection connection = BaseConnection.getInstance().getConnection(ServiceEnum.TRIP);

    public Passenger getById(long id) {
        String sql = "select * from passenger where id = ?";
        ResultSet resultSet;
        Passenger passenger = null;
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                passenger = new Passenger();
                passenger.setId(resultSet.getLong("id"));
                passenger.setFirstName(resultSet.getString("first_name"));
                passenger.setMiddleName(resultSet.getString("middle_name"));
                passenger.setLastName(resultSet.getString("last_name"));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return passenger;
    }

    */
}
