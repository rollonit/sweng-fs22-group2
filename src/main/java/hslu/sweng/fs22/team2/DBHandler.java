package hslu.sweng.fs22.team2;

import java.sql.*;
import java.util.Properties;

/**
 * Handles the low level aspects of the SQL database connection and allows for simpler usage.
 */
public class DBHandler {
    /**
     * Stores the URL of the SQL server.
     */
    private final String connectionUrl;
    /**
     * Stores the connection properties, such as credentials.
     */
    private final Properties connectionProperties;

    /**
     * Default constructor for the DB handler, initialises the connection and makes it ready for queries. TODO check if static info and functions are better suited here.
     */
    public DBHandler() {
        this.connectionUrl = "jdbc:mariadb://hashtagit.ch:3306/hashtag_SWENG";
        this.connectionProperties = new Properties();
        connectionProperties.setProperty("user", "cinema_admin");
        connectionProperties.setProperty("password", "SgWm21M6gp9S");
    }


    /**
     * Runs a single query on the database and TODO returns the results.
     *
     * @param queryText the text contents of the SQL query
     */
    public ResultSet query(String queryText) {
        try (Connection sqlConnection = DriverManager.getConnection(connectionUrl, connectionProperties); Statement statement = sqlConnection.createStatement();) {
            return statement.executeQuery(queryText);
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
