package pl.bmstefanski.asm.command;

import pl.bmstefanski.asm.api.basic.Animal;
import pl.bmstefanski.asm.basic.manager.AnimalManager;
import pl.bmstefanski.asm.command.basic.Command;

import java.util.Map;

public class ListCommand {

    @Command(name = "list", description = "show all pets", min = 1, max = 1)
    private void list() {

        Map<String, Animal> animals = AnimalManager.getAnimalMap();

        if (animals.isEmpty()) {
            System.out.println("We don't yet have any pet!");
            return;
        }

        animals.forEach((key, value) -> System.out.println(value.toString()));
    }
}
