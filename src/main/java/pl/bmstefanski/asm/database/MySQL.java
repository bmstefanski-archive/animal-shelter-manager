package pl.bmstefanski.asm.database;

import pl.bmstefanski.asm.manager.DatabaseManager;
import pl.bmstefanski.asm.basic.Animal;
import pl.bmstefanski.asm.basic.util.ShelterUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.UUID;

public class MySQL implements Database {

    private static MySQL instance;
    private final DatabaseManager database;

    private MySQL() {
        this.database = DatabaseManager.getInstance();
    }

    @Override
    public void checkData() {
        try {
            String sql = "CREATE TABLE IF NOT EXISTS `animals`(" +
                    "`name` VARCHAR(100) NOT NULL," +
                    "`health` DOUBLE NOT NULL," +
                    "`age` INTEGER NOT NULL," +
                    "`uuid` BINARY(36) NOT NULL," +
                    "`birth` TIMESTAMP NOT NULL," +
                    "PRIMARY KEY (`name`));";

            PreparedStatement preparedStatement = database.getConnection().prepareStatement(sql);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void loadData() {
        try {
            String sql = "SELECT * FROM `animals`";

            PreparedStatement preparedStatement = database.getConnection().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Animal animal = new Animal(resultSet.getString("name"));
                animal.setHealth(resultSet.getDouble("health"));
                animal.setAge(resultSet.getInt("age"));
                animal.setUUID(UUID.nameUUIDFromBytes(resultSet.getBytes("uuid")));
                animal.setBirth(resultSet.getTimestamp("birth"));

                ShelterUtil.ANIMALS.put(animal.getName(), animal);
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void saveData() {
        ShelterUtil.ANIMALS.forEach((key, value) -> {
            try {

                String sql = "UPDATE `animals` SET `name`=?, `health`=?, `age`=?, `uuid`=?, `birth`=? WHERE `name`=?";

                PreparedStatement preparedStatement = database.getConnection().prepareStatement(sql);
                preparedStatement.setString(1, value.getName());
                preparedStatement.setDouble(2, value.getHealth());
                preparedStatement.setInt(3, value.getAge());
                preparedStatement.setObject(4, value.getUUID(), Types.BINARY);
                preparedStatement.setTimestamp(5, value.getBirth());
                preparedStatement.setString(6, value.getName());

                preparedStatement.executeUpdate();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void addAnimal(Animal animal) {
        try {
            String sql = "INSERT INTO `animals` (`name`, `health`, `age`, `uuid`, `birth`) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = database.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, animal.getName());
            preparedStatement.setDouble(2, animal.getHealth());
            preparedStatement.setInt(3, animal.getAge());
            preparedStatement.setObject(4, animal.getUUID(), Types.BINARY);
            preparedStatement.setTimestamp(5, animal.getBirth());


            preparedStatement.executeUpdate();
            preparedStatement.close();

            ShelterUtil.ANIMALS.put(animal.getName(), animal);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void removeAnimal(Animal animal) {
        try {
            String sql = "DELETE FROM `animals` WHERE `name`=?";

            PreparedStatement preparedStatement = database.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, animal.getName());

            preparedStatement.executeUpdate();
            preparedStatement.close();

            ShelterUtil.ANIMALS.remove(animal.getName());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static MySQL getInstance() {
        if (instance == null) instance = new MySQL();
        return instance;
    }
}
