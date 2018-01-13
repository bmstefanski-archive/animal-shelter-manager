package pl.bmstefanski.asm.command;

import org.apache.commons.lang3.StringUtils;
import pl.bmstefanski.asm.AnimalShelterManager;
import pl.bmstefanski.asm.api.basic.Animal;
import pl.bmstefanski.asm.basic.AnimalImpl;
import pl.bmstefanski.asm.basic.manager.AnimalManager;
import pl.bmstefanski.asm.command.basic.Command;
import pl.bmstefanski.asm.type.Message;

import java.util.List;

public class RemoveCommand {

    private final AnimalShelterManager main;

    public RemoveCommand(AnimalShelterManager main) {
        this.main = main;
    }

    @Command(name = "remove", description = "remove pet", usage = "[name]", min = 2, max = 2)
    private void remove(List<String> args) {

        Animal animal = new AnimalImpl(args.get(1));

        if (!AnimalManager.getAnimalMap().containsKey(animal.getName())) {
            System.out.println(Message.PET_DOES_NOT_EXIST);
            return;
        }

        main.getResourceManager().remove(animal);
        System.out.println(StringUtils.replace(Message.PET_REMOVED, "$name", animal.getName()));
    }
}
