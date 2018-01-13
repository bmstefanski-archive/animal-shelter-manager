package pl.bmstefanski.asm.command;

import pl.bmstefanski.asm.AnimalShelterManager;
import pl.bmstefanski.asm.api.basic.Animal;
import pl.bmstefanski.asm.basic.AnimalImpl;
import pl.bmstefanski.asm.basic.manager.AnimalManager;
import pl.bmstefanski.asm.command.basic.Command;

import java.util.List;

public class RemoveCommand {

    private final AnimalShelterManager main;

    public RemoveCommand(AnimalShelterManager main) {
        this.main = main;
    }

    @Command(name = "remove", description = "remove pet")
    private void remove(List<String> args) {
        if (args.size() != 2) {
            System.out.println("Correct usage: !remove <animal>");
            return;
        }

        Animal animal = new AnimalImpl(args.get(1));

        if (!AnimalManager.getAnimalMap().containsKey(animal.getName())) {
            System.out.println("The pet with this name does not exists!");
            return;
        }

        main.getResourceManager().remove(animal);
        System.out.println("Successfully removed a pet named: " + animal.getName() + "!");
    }
}
