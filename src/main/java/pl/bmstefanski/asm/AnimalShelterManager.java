package pl.bmstefanski.asm;

import pl.bmstefanski.asm.command.basic.CommandMap;
import pl.bmstefanski.asm.database.MySQL;
import pl.bmstefanski.asm.manager.DatabaseManager;
import pl.bmstefanski.asm.basic.Shelter;

import java.util.Scanner;

public class AnimalShelterManager {

    private static DatabaseManager database = DatabaseManager.getInstance();
    private static MySQL mySQL = MySQL.getInstance();
    private static final CommandMap commandMap = new CommandMap();

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

            if (result.startsWith(commandMap.getPrefix())) {
                result = result.replaceFirst(commandMap.getPrefix(), "");

                commandMap.commandUser(result);
            }
        }

    }
}
