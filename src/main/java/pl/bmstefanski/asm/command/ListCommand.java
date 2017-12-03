package pl.bmstefanski.asm.command;

import pl.bmstefanski.asm.basic.util.ShelterUtil;
import pl.bmstefanski.asm.command.basic.Command;

public class ListCommand {

    @Command(name = "list", description = "show all pets")
    private void list() {
        ShelterUtil.list();
    }
}
