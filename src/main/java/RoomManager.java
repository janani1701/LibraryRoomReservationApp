import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomManager {
    private Connection connection;

    public RoomManager(Connection connection) {
        this.connection = connection;
    }

    public boolean isTimeSlotAvailable(int roomNumber, String timeSlot) throws SQLException {
        String query = "SELECT * FROM bookings WHERE roomNumber = ? AND timeSlot = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, roomNumber);
        preparedStatement.setString(2, timeSlot);
        ResultSet resultSet = preparedStatement.executeQuery();
        return !resultSet.next();
    }

    public void bookRoom(int customerID, int roomNumber, String timeSlot) throws SQLException {
        String query = "INSERT INTO bookings (customerID, roomNumber, bookingDate, timeSlot) VALUES (?, ?, DATE('now'), ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, customerID);
        preparedStatement.setInt(2, roomNumber);
        preparedStatement.setString(3, timeSlot);
        preparedStatement.executeUpdate();
    }
}
