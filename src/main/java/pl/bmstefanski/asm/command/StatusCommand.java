package pl.bmstefanski.asm.command;

import pl.bmstefanski.asm.AnimalShelterManager;
import pl.bmstefanski.asm.basic.manager.AnimalManager;
import pl.bmstefanski.asm.command.basic.Command;

public class StatusCommand {

    private final AnimalShelterManager main;

    public StatusCommand(AnimalShelterManager main) {
        this.main = main;
    }

    @Command(name = "status", description = "check shelter's capacity")
    private void status() {
        AnimalManager.status(main.getShelter());
    }
}
