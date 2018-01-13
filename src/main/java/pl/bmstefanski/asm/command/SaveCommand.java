package pl.bmstefanski.asm.command;

import pl.bmstefanski.asm.AnimalShelterManager;
import pl.bmstefanski.asm.command.basic.Command;

public class SaveCommand {

    private final AnimalShelterManager main;

    public SaveCommand(AnimalShelterManager main) {
        this.main = main;
    }

    @Command(name = "save", description = "save and exit")
    private void save() {
        AnimalShelterManager.EXECUTOR_SERVICE.shutdown();

        main.getResourceManager().save();

        Runtime.getRuntime().exit(1);
    }
}
