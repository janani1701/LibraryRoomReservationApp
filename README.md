# Library Room Reservation App

A simple Java application that allows users to make reservations for library rooms. This application uses an SQLite database to store user information and room bookings.

## Table of Contents
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Usage](#usage)
- [Database Structure](#database-structure)
- [Contributing](#contributing)
- [License](#license)

## Features

- User registration: Users can register by providing their name and email.
- Room booking: Registered users can book library rooms for specific time slots.
- Availability check: The application checks room availability for a given time slot.
- Database integration: Data is stored in an SQLite database.

## Prerequisites

Before you begin, ensure you have met the following requirements:

- Java Development Kit (JDK) installed.
- An Integrated Development Environment (IDE) for Java, such as IntelliJ IDEA or Eclipse.
- SQLite database engine.
- SQLite JDBC driver (usually included in the IDE's libraries).

## Installation

1. Clone the repository to your local machine.

   ```bash
   git clone https://github.com/yourusername/LibraryRoomReservationApp.git

# Usage

1. Open the project in your Java IDE.

2. Ensure the SQLite JDBC driver is added to the project's libraries.

3. Run the Main class in your Java IDE.

4. Follow the on-screen prompts to register and book a room.

5. You can customize the database file path by updating the `dbFilePath` variable in the Main class.

# Database Structure

The application uses an SQLite database with two tables:

**users table**
- `customerID` (Primary Key, Auto-increment) - User's unique identifier.
- `name` (Text) - User's name.
- `email` (Text) - User's email.

**bookings table**
- `bookingID` (Primary Key, Auto-increment) - Booking's unique identifier.
- `customerID` (Foreign Key) - User's `customerID` who made the booking.
- `roomNumber` (Integer) - Room number.
- `bookingDate` (Date) - Date of the booking (automatically set to the current date).
- `timeSlot` (Text) - Time slot for the booking.

# Contributing

Contributions are welcome! Here are the steps to contribute:

1. Fork the project.

2. Create a new branch with a descriptive name.

3. Make your changes and commit them.

4. Push your changes to your fork.

5. Submit a pull request.
