import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AwsDbGetOnJava {

    public static void main(String[] args) throws ClassNotFoundException {
        String host = "database-2.clfhspcuxzv5.ap-south-1.rds.amazonaws.com";
        int port = 3306;
        String databaseName = "awsDatabase";
        String username = "admin"; 
        String password = "SdoWEfcpUCLiNKUgoxUj";

        String jdbcURL = "jdbc:mysql://" + host + ":" + port + "/" + databaseName;

        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // This loads the MySQL JDBC driver class
            connection = DriverManager.getConnection(jdbcURL, username, password);
            String sqlQuery = "SELECT * FROM itemsData";
            Statement sqlSt;
            sqlSt = connection.createStatement();
            ResultSet resultSet = sqlSt.executeQuery(sqlQuery);
            if (connection != null) {
                System.out.println("Database connection established.");
                while(resultSet.next()!=false){
                    String outPut = resultSet.getString("id") + " "+resultSet.getString("title");
                    System.out.println(outPut);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error connecting to the database: " + e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close(); // Close the connection when done
                }
            } catch (SQLException e) {
                System.err.println("Error closing the database connection: " + e.getMessage());
            }
        }
    }
}
