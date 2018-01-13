package pl.bmstefanski.asm.api.storage;

import java.sql.Connection;
import java.sql.SQLException;

public interface Database {

    void openConnection() throws SQLException;

    boolean checkConnection() throws SQLException;

    Connection getConnection();

    boolean closeConnection() throws SQLException;
}
