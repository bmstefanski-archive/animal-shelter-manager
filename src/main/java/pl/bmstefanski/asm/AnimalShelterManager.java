package pl.bmstefanski.asm;

import pl.bmstefanski.asm.command.basic.CommandMap;
import pl.bmstefanski.asm.command.basic.SimpleCommand;
import pl.bmstefanski.asm.database.MySQL;
import pl.bmstefanski.asm.manager.DatabaseManager;

import java.util.Scanner;

public class AnimalShelterManager {

    private static DatabaseManager database = DatabaseManager.getInstance();
    private static MySQL mySQL = MySQL.getInstance();
    private static final CommandMap commandMap = new CommandMap();

    public static void main(String[] args) {
        database.establishConnection();
        mySQL.checkData();
        mySQL.loadData();

        System.out.println("------------------[ ASM ]------------------");
        for (SimpleCommand simpleCommand : commandMap.getCommands()) {
            System.out.println(commandMap.getPrefix() + simpleCommand.getName() + " - " + simpleCommand.getDescription());
        }
        System.out.println("-------------------------------------------");

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
