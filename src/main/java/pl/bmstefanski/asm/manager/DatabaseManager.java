package pl.bmstefanski.asm.manager;

public class DatabaseManager {

    private DatabaseManager instance = null;

    public DatabaseManager getInstance() {
        if(instance == null) instance = new DatabaseManager();
        return instance;
    }
}
