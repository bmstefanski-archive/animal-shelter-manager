package pl.bmstefanski.asm.command;

import pl.bmstefanski.asm.AnimalShelterManager;
import pl.bmstefanski.asm.api.basic.Animal;
import pl.bmstefanski.asm.basic.AnimalImpl;
import pl.bmstefanski.asm.basic.manager.AnimalManager;
import pl.bmstefanski.asm.command.basic.Command;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class AddCommand {

    private final AnimalShelterManager main;

    public AddCommand(AnimalShelterManager main) {
        this.main = main;
    }

    @Command(name = "add", description = "add new pet")
    private void add(List<String> args) {

        if (args.size() != 2) {
            System.out.println("Correct usage: !add <animal>");
            return;
        }

        Animal animal = new AnimalImpl(args.get(1));

        if (AnimalManager.getAnimalMap().size() >= main.getShelter().getCapacity()) {
            System.out.println("Shelter is full, try again later!");
            return;
        }

        if (AnimalManager.getAnimalMap().containsKey(animal.getName())) {
            System.out.println("We already have pet with same name!");
            return;
        }

        Timestamp timestamp = new Timestamp(new Date().getTime());

        animal.setBirth(timestamp);
        main.getResourceManager().add(animal);
        System.out.println("Successfully added a pet named: " + animal.getName() + "!");
    }
}
