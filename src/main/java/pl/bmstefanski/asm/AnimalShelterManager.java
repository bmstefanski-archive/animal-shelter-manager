package pl.bmstefanski.asm;

import pl.bmstefanski.asm.object.Animal;
import pl.bmstefanski.asm.object.Shelter;
import pl.bmstefanski.asm.object.util.ShelterUtil;

import java.util.Scanner;

public class AnimalShelterManager {

    public static void main(String[] args) {
        System.out.println("------[ ASM ]------");
        System.out.println("/add - add new pet");
        System.out.println("/remove - remove pet");
        System.out.println("/status - check shelter's capacity");
        System.out.println("/list - show all pets");
        System.out.println("-------------------");

        Shelter shelter = new Shelter();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String result = scanner.nextLine();

            if(result.equalsIgnoreCase("/status")) {
                ShelterUtil.status(shelter);
            } else if(result.equalsIgnoreCase("/add")) {
                Animal animal = new Animal();
                ShelterUtil.addAnimal(animal, shelter);
            } else if(result.equalsIgnoreCase("/remove")) {
                Animal animal = new Animal();
                ShelterUtil.removeAnimal(animal);
            } else if(result.equalsIgnoreCase("/list")) {
                ShelterUtil.list();
            }
        }

    }
}
