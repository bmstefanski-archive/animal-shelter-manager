package pl.bmstefanski.asm.storage;

import pl.bmstefanski.asm.api.storage.Database;
import pl.bmstefanski.asm.api.storage.Storage;
import pl.bmstefanski.asm.type.StatementType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractDatabase implements Database, Storage {

    protected Connection connection;
    protected final Map<String, PreparedStatement> preparedStatementMap = new HashMap<>();

    @Override
    public boolean checkConnection() throws SQLException {
        return connection != null && !connection.isClosed();
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public boolean closeConnection() throws SQLException {
        if (connection == null) {
            return false;
        }

        connection.close();
        return true;
    }

    @Override
    public boolean setUp() {
        return connect();
    }

    public abstract boolean connect();

    public abstract int returnGeneratedKey(Statement statement);

    public abstract boolean isReturnGeneratedKeysSupported();

    public void addPreparedStatement(String name, PreparedStatement statement) {
        preparedStatementMap.put(name, statement);
    }

    public PreparedStatement getPreparedStatement(String name) throws SQLException {
        if (preparedStatementMap.isEmpty() || !preparedStatementMap.containsKey(name)) {
            prepareStatements();
        }

        PreparedStatement preparedStatement = preparedStatementMap.get(name);
        if (preparedStatement != null && preparedStatement.isClosed()) {
            prepareStatements();

            preparedStatement = preparedStatementMap.get(name);
        }

        if (preparedStatement == null) {
            throw new IllegalArgumentException("Invalid statement enum");
        }

        preparedStatement.clearParameters();
        return preparedStatement;
    }

    protected void prepareStatements() {
        try {
            preparedStatementMap.clear();
            connect();

            int returnKeys = isReturnGeneratedKeysSupported() ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS;

            String checkTableSql = "CREATE TABLE IF NOT EXISTS `animals`(`name` VARCHAR(100) NOT NULL,`health` DOUBLE NOT NULL,`age` INTEGER NOT NULL,`uuid` BINARY(36) NOT NULL,`birth` TIMESTAMP NOT NULL,PRIMARY KEY (`name`));";
            PreparedStatement checkTable = getConnection().prepareStatement(checkTableSql, returnKeys);
            addPreparedStatement(StatementType.CHECK_TABLE.name(), checkTable);

            String loadAnimalsSql = "SELECT * FROM `animals`";
            PreparedStatement loadAnimals = getConnection().prepareStatement(loadAnimalsSql, returnKeys);
            addPreparedStatement(StatementType.LOAD_ANIMALS.name(), loadAnimals);

            String saveAnimalsSql = "UPDATE `animals` SET `name`=?, `health`=?, `age`=?, `uuid`=?, `birth`=? WHERE `name`=?";
            PreparedStatement saveAnimals = getConnection().prepareStatement(saveAnimalsSql, returnKeys);
            addPreparedStatement(StatementType.SAVE_ANIMALS.name(), saveAnimals);

            String addAnimalSql = "INSERT INTO `animals` (`name`, `health`, `age`, `uuid`, `birth`) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement addAnimal = getConnection().prepareStatement(addAnimalSql, returnKeys);
            addPreparedStatement(StatementType.ADD_ANIMAL.name(), addAnimal);

            String removeAnimalSql = "DELETE FROM `animals` WHERE `name`=?";
            PreparedStatement removeAnimal = getConnection().prepareStatement(removeAnimalSql, returnKeys);
            addPreparedStatement(StatementType.REMOVE_ANIMAL.name(), removeAnimal);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }


}
