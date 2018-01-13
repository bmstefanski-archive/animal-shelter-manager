package pl.bmstefanski.asm.command;

import pl.bmstefanski.asm.basic.manager.AnimalManager;
import pl.bmstefanski.asm.command.basic.Command;

public class ListCommand {

    @Command(name = "list", description = "show all pets")
    private void list() {
        AnimalManager.list();
    }
}
