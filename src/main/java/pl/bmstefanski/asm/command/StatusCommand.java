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
        System.out.println("There are " + AnimalManager.getAnimalMap().size() + " out of a maximum " + main.getShelter().getCapacity() + " pets in " + main.getShelter().getName());
        System.out.println(AnimalManager.getAnimalMap().size() < main.getShelter().getCapacity() ? "You can bring your pet!" : "The shelter is full!");
    }
}
