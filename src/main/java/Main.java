import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String dbFilePath = "/Users/jananihemachandran/IdeaProjects/LibraryRoomReservationApp/librarydatabase.db"; // Specify the path to your SQLite database file

        // Create a database connection
        DatabaseConnector dbConnector = new DatabaseConnector(dbFilePath);
        Connection connection = dbConnector.getConnection();

// Create the "users" table
        String createUserTableSQL = "CREATE TABLE IF NOT EXISTS users (" +
                "customerID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, " +
                "email TEXT)";

// Create the "bookings" table
        String createBookingsTableSQL = "CREATE TABLE IF NOT EXISTS bookings (" +
                "bookingID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "customerID INTEGER, " +
                "roomNumber INTEGER, " +
                "bookingDate DATE, " +
                "timeSlot TEXT)";

        try {
            Statement statement = connection.createStatement();
            statement.execute(createUserTableSQL);
            statement.execute(createBookingsTableSQL);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Initialize user and room managers
        UserManager userManager = new UserManager(connection);
        RoomManager roomManager = new RoomManager(connection);

        // User input
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();

        try {
            if (!userManager.isUserRegistered(name, email)) {
                int customerID = userManager.registerUser(name, email);

                System.out.print("Enter the room number (100-105): ");
                int roomNumber = scanner.nextInt();
                scanner.nextLine();  // Consume the newline character

                System.out.println("Time slots:");
                String[] timeSlots = {"10am-12pm", "12pm-2pm", "2pm-4pm", "4pm-6pm"};
                for (int i = 0; i < timeSlots.length; i++) {
                    System.out.println(i + 1 + ". " + timeSlots[i]);
                }

                System.out.print("Select a time slot (1-4): ");
                int selectedTimeSlotIndex = scanner.nextInt();
                scanner.nextLine();  // Consume the newline character
                String selectedTimeSlot = timeSlots[selectedTimeSlotIndex - 1];

                if (roomManager.isTimeSlotAvailable(roomNumber, selectedTimeSlot)) {
                    roomManager.bookRoom(customerID, roomNumber, selectedTimeSlot);
                    System.out.println("Room booked successfully!");
                } else {
                    System.out.println("This time slot has been booked. Try another time slot in another room.");
                }
            } else {
                System.out.println("User already registered. Cannot register again.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Close the database connection
        dbConnector.closeConnection();
    }
}

