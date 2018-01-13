package pl.bmstefanski.asm.command;

import org.apache.commons.lang3.StringUtils;
import pl.bmstefanski.asm.AnimalShelterManager;
import pl.bmstefanski.asm.api.basic.Animal;
import pl.bmstefanski.asm.basic.AnimalImpl;
import pl.bmstefanski.asm.basic.manager.AnimalManager;
import pl.bmstefanski.asm.command.basic.Command;
import pl.bmstefanski.asm.type.Message;

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
            System.out.println(Message.SHELTER_FULL);
            return;
        }

        if (animals.containsKey(animal.getName())) {
            System.out.println(Message.PET_EXISTS);
            return;
        }

        Timestamp timestamp = new Timestamp(new Date().getTime());

        animal.setBirth(timestamp);
        main.getResourceManager().add(animal);
        System.out.println(StringUtils.replace(Message.PET_CREATED, "$name", animal.getName()));
    }
}
