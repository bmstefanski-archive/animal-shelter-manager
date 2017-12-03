package pl.bmstefanski.asm.command;

import pl.bmstefanski.asm.basic.Animal;
import pl.bmstefanski.asm.basic.util.ShelterUtil;
import pl.bmstefanski.asm.command.basic.Command;

import java.util.List;

public class RemoveCommand {

    @Command(name = "remove", description = "remove pet")
    private void remove(List<String> args) {
        if (args.size() != 2) {
            System.out.println("Correct usage: !remove <animal>");
            return;
        }

        ShelterUtil.removeAnimal(new Animal(args.get(1)));
    }
}
