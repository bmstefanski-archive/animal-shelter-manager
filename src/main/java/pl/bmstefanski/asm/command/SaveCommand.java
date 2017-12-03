package pl.bmstefanski.asm.command;

import pl.bmstefanski.asm.AnimalShelterManager;
import pl.bmstefanski.asm.command.basic.Command;
import pl.bmstefanski.asm.database.MySQL;

public class SaveCommand {

    private final MySQL mySQL = MySQL.getInstance();

    @Command(name = "save", description = "save and exit")
    private void save() {
        mySQL.saveData();
        Runtime.getRuntime().exit(1);

        AnimalShelterManager.EXECUTOR_SERVICE.shutdown();
    }
}
