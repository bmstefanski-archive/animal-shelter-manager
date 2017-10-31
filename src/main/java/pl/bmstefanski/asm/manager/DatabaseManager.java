package pl.bmstefanski.asm.manager;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseManager {

    private static DatabaseManager instance;
    private Connection connection;

    private final String mysqlHostname;
    private final String mysqlPort;
    private final String mysqlDatabase;
    private final String mysqlUsername;
    private final String mysqlPassword;

    private DatabaseManager() {
        this.mysqlHostname = "localhost";
        this.mysqlPort = "3306";
        this.mysqlDatabase = "asm";
        this.mysqlUsername = "root";
        this.mysqlPassword = "root";
    }


    public void establishConnection() {
        try {
            String connectionString = "jdbc:mysql://" + mysqlHostname
                    + ":" + mysqlPort
                    + "/" + mysqlDatabase
                    + "?user=" + mysqlUsername
                    + "&password=" + mysqlPassword;

            Class.forName("com.mysql.jdbc.Driver");
            this.connection = DriverManager.getConnection(connectionString);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static DatabaseManager getInstance() {
        if (instance == null) instance = new DatabaseManager();
        return instance;
    }
}
