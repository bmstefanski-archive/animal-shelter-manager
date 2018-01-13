package pl.bmstefanski.asm.command;

import pl.bmstefanski.asm.basic.manager.AnimalManager;
import pl.bmstefanski.asm.command.basic.Command;

public class ListCommand {

    @Command(name = "list", description = "show all pets")
    private void list() {

        if (AnimalManager.getAnimalMap().isEmpty()) {
            System.out.println("We don't yet have any pet!");
            return;
        }

        AnimalManager.getAnimalMap().forEach((key, value) -> System.out.println(value.toString()));
    }
}
