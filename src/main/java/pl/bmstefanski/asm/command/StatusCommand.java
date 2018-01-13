package pl.bmstefanski.asm.command;

import org.apache.commons.lang3.StringUtils;
import pl.bmstefanski.asm.AnimalShelterManager;
import pl.bmstefanski.asm.api.basic.Animal;
import pl.bmstefanski.asm.api.basic.Shelter;
import pl.bmstefanski.asm.basic.manager.AnimalManager;
import pl.bmstefanski.asm.command.basic.Command;
import pl.bmstefanski.asm.type.Message;

import java.util.Map;

public class StatusCommand {

    private final AnimalShelterManager main;

    public StatusCommand(AnimalShelterManager main) {
        this.main = main;
    }

    @Command(name = "status", description = "check shelter's capacity", min = 1, max = 1)
    private void status() {

        Map<String, Animal> animals = AnimalManager.getAnimalMap();
        Shelter shelter = main.getShelter();

        System.out.println(StringUtils.replaceEach(Message.STATUS,
                new String[] {"$size", "$capacity", "$shelter"},
                new String[] {animals.size() + "", shelter.getCapacity() + "", shelter.getName()}));
        System.out.println(animals.size() < shelter.getCapacity() ? Message.SHELTER_OPEN : Message.SHELTER_CLOSED);
    }
}
