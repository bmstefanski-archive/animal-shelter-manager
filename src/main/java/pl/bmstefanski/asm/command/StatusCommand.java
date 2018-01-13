package pl.bmstefanski.asm.command;

import pl.bmstefanski.asm.AnimalShelterManager;
import pl.bmstefanski.asm.api.basic.Animal;
import pl.bmstefanski.asm.api.basic.Shelter;
import pl.bmstefanski.asm.basic.manager.AnimalManager;
import pl.bmstefanski.asm.command.basic.Command;

import java.util.Map;

public class StatusCommand {

    private final AnimalShelterManager main;

    public StatusCommand(AnimalShelterManager main) {
        this.main = main;
    }

    @Command(name = "status", description = "check shelter's capacity")
    private void status() {

        Map<String, Animal> animals = AnimalManager.getAnimalMap();
        Shelter shelter = main.getShelter();

        System.out.println("There are " + animals.size() + " out of a maximum " + shelter.getCapacity() + " pets in " + shelter.getName());
        System.out.println(animals.size() < shelter.getCapacity() ? "You can bring your pet!" : "The shelter is full!");
    }
}
