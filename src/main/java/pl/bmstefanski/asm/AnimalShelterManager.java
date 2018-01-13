package pl.bmstefanski.asm;

import pl.bmstefanski.asm.api.AnimalShelterManagerApi;
import pl.bmstefanski.asm.api.basic.Shelter;
import pl.bmstefanski.asm.api.storage.Storage;
import pl.bmstefanski.asm.basic.ShelterImpl;
import pl.bmstefanski.asm.command.basic.CommandMap;
import pl.bmstefanski.asm.command.basic.SimpleCommand;
import pl.bmstefanski.asm.storage.StorageConnector;
import pl.bmstefanski.asm.storage.manager.AnimalResourceManager;
import pl.bmstefanski.asm.task.GrowingThread;
import pl.bmstefanski.asm.type.DataStorageType;

import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AnimalShelterManager implements AnimalShelterManagerApi {

    private static final CommandMap COMMAND_MAP = new CommandMap();
    public static final ScheduledExecutorService EXECUTOR_SERVICE = Executors.newScheduledThreadPool(1);

    private AnimalResourceManager animalResourceManager;
    private final Shelter shelter;
    private Storage storage;

    public AnimalShelterManager() {
        this.storage = new StorageConnector(DataStorageType.MYSQL).getStorage();
        this.animalResourceManager = new AnimalResourceManager(storage);
        this.shelter = new ShelterImpl(2, "Main");
    }

    private void start() {
        EXECUTOR_SERVICE.scheduleAtFixedRate(new GrowingThread(), 0, 1, TimeUnit.HOURS);

        this.animalResourceManager.checkTable();
        this.animalResourceManager.load();


        System.out.println("------------------[ ASM ]------------------");
        for (SimpleCommand simpleCommand : COMMAND_MAP.getCommands()) {
            System.out.println(COMMAND_MAP.getPrefix() + simpleCommand.getName() + " - " + simpleCommand.getDescription());
        }
        System.out.println("-------------------------------------------");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String result = scanner.nextLine();

            if (result.startsWith(COMMAND_MAP.getPrefix())) {
                result = result.replaceFirst(COMMAND_MAP.getPrefix(), "");

                COMMAND_MAP.commandUser(result);
            }
        }
    }

    public static void main(String[] args) {
        AnimalShelterManager animalShelterManager = new AnimalShelterManager();
        animalShelterManager.start();
    }

    @Override
    public AnimalResourceManager getResourceManager() {
        return animalResourceManager;
    }

    @Override
    public Storage getStorage() {
        return storage;
    }

    @Override
    public Shelter getShelter() {
        return shelter;
    }
}
