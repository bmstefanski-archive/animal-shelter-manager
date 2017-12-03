package pl.bmstefanski.asm.command;

import pl.bmstefanski.asm.basic.Animal;
import pl.bmstefanski.asm.basic.util.ShelterUtil;
import pl.bmstefanski.asm.command.basic.Command;

import java.util.List;

public class AnimalCommand {

    @Command(name = "animal", description = "statistics")
    private void animal(List<String> args) {
        if (args.size() != 2) {
            System.out.println("Correct usage: !animal <animal>");
            return;
        }

        ShelterUtil.info(new Animal(args.get(1)));
    }
}
