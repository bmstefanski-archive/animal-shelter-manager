package pl.bmstefanski.asm.database;

import pl.bmstefanski.asm.basic.Animal;

public interface Database {

    void checkData();

    void loadData();

    void saveData();

    void addAnimal(Animal animal);

    void removeAnimal(Animal animal);
}
