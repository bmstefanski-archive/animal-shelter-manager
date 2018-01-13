package pl.bmstefanski.asm.command;

import pl.bmstefanski.asm.AnimalShelterManager;
import pl.bmstefanski.asm.api.basic.Animal;
import pl.bmstefanski.asm.basic.AnimalImpl;
import pl.bmstefanski.asm.basic.manager.AnimalManager;
import pl.bmstefanski.asm.command.basic.Command;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class AddCommand {

    private final AnimalShelterManager main;

    public AddCommand(AnimalShelterManager main) {
        this.main = main;
    }

    @Command(name = "add", description = "add new pet", usage = "[name]", min = 2, max = 2)
    private void add(List<String> args) {

        Animal animal = new AnimalImpl(args.get(1));
        Map<String, Animal> animals = AnimalManager.getAnimalMap();

        if (animals.size() >= main.getShelter().getCapacity()) {
            System.out.println("Shelter is full, try again later!");
            return;
        }

        if (animals.containsKey(animal.getName())) {
            System.out.println("We already have pet with same name!");
            return;
        }

        Timestamp timestamp = new Timestamp(new Date().getTime());

        animal.setBirth(timestamp);
        main.getResourceManager().add(animal);
        System.out.println("Successfully added a pet named: " + animal.getName() + "!");
    }
}
