package pl.bmstefanski.asm.command;

import pl.bmstefanski.asm.api.basic.Animal;
import pl.bmstefanski.asm.basic.manager.AnimalManager;
import pl.bmstefanski.asm.command.basic.Command;
import pl.bmstefanski.asm.type.Message;

import java.util.List;

public class AnimalCommand {

    @Command(name = "animal", description = "statistics", usage = "[name]", min = 2, max = 2)
    private void animal(List<String> args) {

        Animal animal = AnimalManager.get(args.get(1));

        if (!AnimalManager.getAnimalMap().containsKey(animal.getName())) {
            System.out.println(Message.PET_NOT_FOUND);
            return;
        }

        System.out.println(animal.toString());
    }
}
