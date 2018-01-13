package pl.bmstefanski.asm.storage;

import pl.bmstefanski.asm.api.storage.Storage;
import pl.bmstefanski.asm.type.DataStorageType;

public class StorageConnector {

    private final DataStorageType dataStorageType;
    private Storage storage;

    public StorageConnector(DataStorageType dataStorageType) {
        this.dataStorageType = dataStorageType;

        connect();
    }

    public void connect() {

        switch (dataStorageType) {
            case MYSQL:

                storage = new MySQLStorage(
                        "root",
                        "root",
                        "localhost",
                        "asm",
                        3306
                );

                break;
        }
    }

    public Storage getStorage() {
        return storage;
    }
}
