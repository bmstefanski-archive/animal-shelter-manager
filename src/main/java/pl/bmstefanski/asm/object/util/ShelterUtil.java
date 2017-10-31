package pl.bmstefanski.asm.object.util;

import pl.bmstefanski.asm.object.Animal;
import pl.bmstefanski.asm.object.Shelter;

import java.util.*;

public class ShelterUtil {

    public static HashMap<String, Animal> ANIMALS = new HashMap<>();

    public static void addAnimal(Animal animal, Shelter shelter) {
        if(ANIMALS.size() < shelter.getCapacity()) {

            if(ANIMALS.containsKey(animal.getName())) {
                System.out.println("We already have pet with same name!");
                return;
            }

            ANIMALS.put(animal.getName(), animal);
            System.out.println("Successfully added a pet named: " + animal.getName() + "!");
        } else System.out.println("Shelter is full, try again later!");
    }

    public static void removeAnimal(Animal animal) {
        if(ANIMALS.containsKey(animal.getName())) {
            ANIMALS.remove(animal.getName());
            System.out.println("Successfully removed a pet named: " + animal.getName() + "!");
        } else System.out.println("The pet with this name is not exists!");
    }

    public static void status(Shelter shelter) {
        System.out.println("There are " + ANIMALS.size() + " out of a maximum " + shelter.getCapacity() + " pets in " + shelter.getName());
        System.out.println(ANIMALS.size() < shelter.getCapacity() ? "You can bring your pet!" : "The shelter is full!");
    }

    public static void list() {
        if (ANIMALS.isEmpty()) {
            System.out.println("We don't yet have any pet!");
            return;
        }

        ANIMALS.forEach((key, value) -> System.out.println(value.toString()));
    }

    public static void info(Animal animal) {
        if (!ANIMALS.containsKey(animal.getName())) {
            System.out.println("We do not have pet with this name!");
            return;
        }

        System.out.println(animal.toString());
    }
}
