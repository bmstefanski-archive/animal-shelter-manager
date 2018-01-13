package pl.bmstefanski.asm.basic.manager;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import pl.bmstefanski.asm.AnimalShelterManager;
import pl.bmstefanski.asm.api.basic.Animal;
import pl.bmstefanski.asm.api.basic.Shelter;
import pl.bmstefanski.asm.basic.AnimalImpl;

import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class AnimalManager {

    private final static AnimalShelterManager MAIN = new AnimalShelterManager();
    private final static Map<String, Animal> ANIMAL_MAP = new HashMap<>();
    private final static Cache<String, Animal> ANIMAL_MAP_CACHE = CacheBuilder.newBuilder().expireAfterAccess(5, TimeUnit.MINUTES).build();

    public static Animal get(String name) {
        Animal animal = ANIMAL_MAP_CACHE.getIfPresent(name);

        if (animal == null) {
            animal = ANIMAL_MAP.get(name);

            if (animal != null) {
                ANIMAL_MAP_CACHE.put(name, animal);
                return animal;
            }

            return new AnimalImpl(name);
        }

        return animal;
    }

    public static Map<String, Animal> getAnimalMap() {
        return ANIMAL_MAP;
    }

    public static List<Animal> getAnimals() {
        return new ArrayList<>(ANIMAL_MAP.values());
    }

    public static void addAnimal(Animal animal, Shelter shelter) {
        if(ANIMAL_MAP.size() < shelter.getCapacity()) {

            if(ANIMAL_MAP.containsKey(animal.getName())) {
                System.out.println("We already have pet with same name!");
                return;
            }

            Timestamp timestamp = new Timestamp(new Date().getTime());
            animal.setBirth(timestamp);
            MAIN.getResourceManager().add(animal);
            System.out.println("Successfully added a pet named: " + animal.getName() + "!");
        } else System.out.println("Shelter is full, try again later!");
    }

    public static void removeAnimal(Animal animal) {
        if(ANIMAL_MAP.containsKey(animal.getName())) {
            MAIN.getResourceManager().remove(animal);
            System.out.println("Successfully removed a pet named: " + animal.getName() + "!");
        } else System.out.println("The pet with this name is not exists!");
    }

    public static void status(Shelter shelter) {
        System.out.println("There are " + ANIMAL_MAP.size() + " out of a maximum " + shelter.getCapacity() + " pets in " + shelter.getName());
        System.out.println(ANIMAL_MAP.size() < shelter.getCapacity() ? "You can bring your pet!" : "The shelter is full!");
    }

    public static void list() {
        if (ANIMAL_MAP.isEmpty()) {
            System.out.println("We don't yet have any pet!");
            return;
        }

        ANIMAL_MAP.forEach((key, value) -> System.out.println(value.toString()));
    }

    public static void info(Animal animal) {
        if (!ANIMAL_MAP.containsKey(animal.getName())) {
            System.out.println("We do not have pet with this name!");
            return;
        }

        System.out.println(animal.toString());
    }
}
