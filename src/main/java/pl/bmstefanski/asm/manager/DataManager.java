package pl.bmstefanski.asm.manager;

public class DataManager {

    private DataManager instance = null;

    public DataManager getInstance() {
        if(instance == null) instance = new DataManager();
        return instance;
    }
}
