import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserManager {
    private Connection connection;

    public UserManager(Connection connection) {
        this.connection = connection;
    }

    public boolean isUserRegistered(String name, String email) throws SQLException {
        String query = "SELECT customerID FROM users WHERE name = ? AND email = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, email);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet.next();
    }

    public int registerUser(String name, String email) throws SQLException {
        String query = "INSERT INTO users (name, email) VALUES (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, email);
        preparedStatement.executeUpdate();
        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

        if (generatedKeys.next()) {
            return generatedKeys.getInt(1);
        }

        return -1;  // Error
    }
}
