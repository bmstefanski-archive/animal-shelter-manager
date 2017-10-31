package pl.bmstefanski.asm;

import pl.bmstefanski.asm.database.MySQL;
import pl.bmstefanski.asm.manager.DatabaseManager;
import pl.bmstefanski.asm.object.Animal;
import pl.bmstefanski.asm.object.Shelter;
import pl.bmstefanski.asm.object.util.ShelterUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AnimalShelterManager {

    private static DatabaseManager database = DatabaseManager.getInstance();;
    private static MySQL mySQL = MySQL.getInstance();

    public static void main(String[] args) {
        database.establishConnection();
        mySQL.checkData();
        mySQL.loadData();

        System.out.println("------[ ASM ]------");
        System.out.println("!add <name> - add new pet");
        System.out.println("!remove <name> - remove pet");
        System.out.println("!status - check shelter's capacity");
        System.out.println("!list - show all pets");
        System.out.println("!animal <animal> - statistics");
        System.out.println("!save - save and exit");
        System.out.println("-------------------");

        Shelter shelter = new Shelter(2, "Main");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String result = scanner.nextLine();

            List<String> arguments = Arrays.stream(result.split(" ")).collect(Collectors.toList());

            if (arguments.get(0).equalsIgnoreCase("!add")) {
                if (arguments.size() != 2) {
                    System.out.println("Correct usage: !add <animal>");
                    continue;
                }

                ShelterUtil.addAnimal(getAnimalName(arguments), shelter);
            }

            else if (arguments.get(0).equalsIgnoreCase("!remove")) {
                if (arguments.size() != 2) {
                    System.out.println("Correct usage: !remove <animal>");
                    continue;
                }

                ShelterUtil.removeAnimal(getAnimalName(arguments));
            }

            else if (arguments.get(0).equalsIgnoreCase("!animal")) {
                if (arguments.size() != 2) {
                    System.out.println("Correct usage: !animal <animal>");
                    continue;
                }

                ShelterUtil.info(getAnimalName(arguments));
            }

            else if (result.equalsIgnoreCase("!list")) {
                ShelterUtil.list();
            } else if (result.equalsIgnoreCase("!status")) {
                ShelterUtil.status(shelter);
            } else if (result.equalsIgnoreCase("!save")) {
                mySQL.saveData();
                System.exit(0);
            }

        }
    }

    private static Animal getAnimalName(List<String> arguments) {
        String name = arguments.get(1);
        return new Animal(name);
    }
}
