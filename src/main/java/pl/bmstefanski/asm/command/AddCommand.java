package pl.bmstefanski.asm.command;

import pl.bmstefanski.asm.api.basic.Animal;
import pl.bmstefanski.asm.basic.AnimalImpl;
import pl.bmstefanski.asm.basic.ShelterImpl;
import pl.bmstefanski.asm.basic.util.ShelterUtil;
import pl.bmstefanski.asm.command.basic.Command;

import java.util.List;

public class AddCommand {

    private final ShelterImpl shelter;

    public AddCommand(ShelterImpl shelter) {
        this.shelter = shelter;
    }

    @Command(name = "add", description = "add new pet")
    private void add(List<String> args) {
        if (args.size() != 2) {
            System.out.println("Correct usage: !add <animal>");
            return;
        }

        Animal animal = new AnimalImpl(args.get(1));

        ShelterUtil.addAnimal(animal, shelter);
    }
}
