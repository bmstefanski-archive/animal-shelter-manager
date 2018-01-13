package pl.bmstefanski.asm.storage;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLStorage extends AbstractDatabase {

    private final String username;
    private final String password;
    private final String hostname;
    private final String database;
    private final int port;

    public MySQLStorage(String username, String password, String hostname, String database, int port) {
        this.username = username;
        this.password = password;
        this.hostname = hostname;
        this.database = database;
        this.port = port;

        if (!setUp()) {
            throw new RuntimeException("Failed while connecting to MySQL database");
        }
    }

    @Override
    public boolean connect() {
        try {
            if (checkConnection()) {
                return true;
            }

            openConnection();
            prepareStatements();

            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();

            return false;
        }
    }

    @Override
    public void openConnection() throws SQLException {
        try {
            if (checkConnection()) {
                return;
            }

            Class.forName("com.mysql.jdbc.Driver");

            this.connection = DriverManager.getConnection("jdbc:mysql://" + hostname + ":" + port + "/" + database + "?autoReconnect=true", username, password);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public int returnGeneratedKey(Statement statement) {
        try {
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();

            int id = resultSet.getInt(1);

            if (id == 0) {
                throw new RuntimeException("Could not get generated keys.");
            }

            return id;
        } catch (SQLException ex){
            throw new RuntimeException("Could not get generated keys.", ex);
        }
    }

    @Override
    public boolean isReturnGeneratedKeysSupported() {
        return true;
    }
}
