package pl.bmstefanski.asm.storage;

import pl.bmstefanski.asm.api.storage.Storage;

public abstract class AbstractStorage {

    private final Storage storage;

    protected AbstractStorage(Storage storage) {
        this.storage = storage;
    }

    protected AbstractDatabase getStorage() {
        return (AbstractDatabase) storage;
    }
}
