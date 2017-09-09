package pl.bmstefanski.asm.object.util;

import pl.bmstefanski.asm.object.Animal;
import pl.bmstefanski.asm.object.Shelter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShelterUtil {

    private static List<String> animals = new ArrayList<>();

    public static void addAnimal(Animal animal, Shelter shelter) {
        if(animals.size() < shelter.getCapacity()) {
            System.out.println("Please, write pet's name..");
            Scanner scanner = new Scanner(System.in);
            String result = scanner.nextLine();
            animal.setName(result);

            if(animals.contains(animal.getName())) {
                System.out.println("We already have pet with same name!");
                return;
            }

            animals.add(animal.getName());
            System.out.println("Successfully added a pet named: " + animal.getName() + "!");
        } else System.out.println("Shelter is full, try again later!");
    }

    public static void removeAnimal(Animal animal) {
        System.out.println("Please, write pet's name..");
        Scanner scanner = new Scanner(System.in);
        String result = scanner.nextLine();
        animal.setName(result);

        if(animals.contains(animal.getName())) {
            animals.remove(animal.getName());
            System.out.println("Successfully removed a pet named: " + result + "!");
        } else System.out.println("The pet with this name is not exists!");
    }

    public static void status(Shelter shelter) {
        System.out.println("There are " + animals.size() + " out of a maximum " + shelter.getCapacity() + " pets");
        System.out.println(animals.size() < shelter.getCapacity() ? "You can bring your pet!" : "The shelter is full!");
    }

    public static void list() {
        if(animals.isEmpty()) {
            System.out.println("We don't yet have any pet!");
            return;
        }

        for(int i = 0; i < animals.size(); i++) {
            for(String animal : animals) {
                System.out.println("ID: " + i + " | " + "NAME: " + animal);
                i++;
            }
        }
    }
}
