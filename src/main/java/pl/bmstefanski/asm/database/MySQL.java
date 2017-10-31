package pl.bmstefanski.asm.database;

import pl.bmstefanski.asm.manager.DatabaseManager;
import pl.bmstefanski.asm.object.Animal;
import pl.bmstefanski.asm.object.util.ShelterUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

                String sql = "INSERT INTO `animals` (`name`, `health`, `age`) VALUES ('"
                        + value.getName()
                        + "','" + value.getHealth()
                        + "','" + value.getAge()
                        + "') ON DUPLICATE KEY UPDATE name='" + value.getName()
                        + "',`health`='" + value.getHealth()
                        + "',`age`='" + value.getAge() + "';";

                PreparedStatement preparedStatement = database.getConnection().prepareStatement(sql);
                preparedStatement.executeUpdate();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public static MySQL getInstance() {
        if (instance == null) instance = new MySQL();
        return instance;
    }
}
