package pl.bmstefanski.asm.command;

import pl.bmstefanski.asm.basic.Animal;
import pl.bmstefanski.asm.basic.Shelter;
import pl.bmstefanski.asm.basic.util.ShelterUtil;
import pl.bmstefanski.asm.command.basic.Command;

import java.util.List;

public class AddCommand {

    private final Shelter shelter;

    public AddCommand(Shelter shelter) {
        this.shelter = shelter;
    }

    @Command(name = "add", description = "add new pet")
    private void add(List<String> args) {
        if (args.size() != 2) {
            System.out.println("Correct usage: !add <animal>");
            return;
        }

        ShelterUtil.addAnimal(new Animal(args.get(1)), shelter);
    }
}
