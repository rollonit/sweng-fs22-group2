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
        this.connectionUrl = "jdbc:postgresql://192.168.137.81:5432/sweng2"; //TODO replace with actual final DB url, may not be PostgreSQL
        this.connectionProperties = new Properties();
        connectionProperties.setProperty("user", "sweng2");
        connectionProperties.setProperty("password", "sweng2pass");
    }


    /**
     * Runs a single query on the database and TODO returns the results.
     *
     * @param queryText the text contents of the SQL query
     */
    public void query(String queryText) {
        try (Connection connection = DriverManager.getConnection(connectionUrl, connectionProperties); Statement statement = connection.createStatement();) {
            //String testQueryTable = "insert into test" + "\nvalues(01, 'test01first', 'test01last');";
            ResultSet resultSet = statement.executeQuery(queryText);
            System.out.println(resultSet.toString());
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
