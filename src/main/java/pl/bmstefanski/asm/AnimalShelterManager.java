package pl.bmstefanski.asm;

import pl.bmstefanski.asm.command.basic.CommandMap;
import pl.bmstefanski.asm.command.basic.SimpleCommand;
import pl.bmstefanski.asm.database.MySQL;
import pl.bmstefanski.asm.manager.DatabaseManager;
import pl.bmstefanski.asm.task.GrowingThread;

import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AnimalShelterManager {

    private static DatabaseManager database = DatabaseManager.getInstance();
    private static MySQL mySQL = MySQL.getInstance();
    private static final CommandMap commandMap = new CommandMap();

    public static void main(String[] args) {
        database.establishConnection();
        mySQL.checkData();
        mySQL.loadData();

        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        service.scheduleAtFixedRate(new GrowingThread(), 0, 5, TimeUnit.HOURS);

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
