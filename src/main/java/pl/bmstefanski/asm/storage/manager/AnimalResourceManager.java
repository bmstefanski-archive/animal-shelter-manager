package pl.bmstefanski.asm.storage.manager;

import pl.bmstefanski.asm.api.basic.Animal;
import pl.bmstefanski.asm.api.storage.Storage;
import pl.bmstefanski.asm.basic.AnimalImpl;
import pl.bmstefanski.asm.basic.util.ShelterManager;
import pl.bmstefanski.asm.storage.AbstractStorage;
import pl.bmstefanski.asm.type.StatementType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.UUID;

public class AnimalResourceManager extends AbstractStorage {

    public AnimalResourceManager(Storage storage) {
        super(storage);
    }

    public void checkTable() {

        getStorage().connect();

        try {
            PreparedStatement preparedStatement = getStorage().getPreparedStatement(StatementType.CHECK_TABLE.name());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void load() {

        getStorage().connect();

        try {
            PreparedStatement preparedStatement = getStorage().getPreparedStatement(StatementType.LOAD_ANIMALS.name());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Animal animal = new AnimalImpl(resultSet.getString("name"));
                animal.setHealth(resultSet.getDouble("health"));
                animal.setAge(resultSet.getInt("age"));
                animal.setUUID(UUID.nameUUIDFromBytes(resultSet.getBytes("uuid")));
                animal.setBirth(resultSet.getTimestamp("birth"));

                ShelterManager.ANIMALS.put(animal.getName(), animal);
            }

            resultSet.close();
            preparedStatement.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void save() {

        getStorage().connect();

        ShelterManager.ANIMALS.forEach((key, value) -> {
            try {
                PreparedStatement preparedStatement = getStorage().getPreparedStatement(StatementType.SAVE_ANIMALS.name());
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

    public void add(Animal animal) {

        getStorage().connect();

        try {
            PreparedStatement preparedStatement = getStorage().getPreparedStatement(StatementType.ADD_ANIMAL.name());
            preparedStatement.setString(1, animal.getName());
            preparedStatement.setDouble(2, animal.getHealth());
            preparedStatement.setInt(3, animal.getAge());
            preparedStatement.setObject(4, animal.getUUID(), Types.BINARY);
            preparedStatement.setTimestamp(5, animal.getBirth());

            preparedStatement.executeUpdate();
            preparedStatement.close();

            ShelterManager.ANIMALS.put(animal.getName(), animal);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void remove(Animal animal) {

        getStorage().connect();

        try {
            PreparedStatement preparedStatement = getStorage().getPreparedStatement(StatementType.REMOVE_ANIMAL.name());
            preparedStatement.setString(1, animal.getName());

            preparedStatement.executeUpdate();
            preparedStatement.close();

            ShelterManager.ANIMALS.remove(animal.getName());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
