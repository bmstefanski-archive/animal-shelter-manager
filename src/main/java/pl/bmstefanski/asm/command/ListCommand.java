package pl.bmstefanski.asm.command;

import pl.bmstefanski.asm.basic.util.ShelterUtil;
import pl.bmstefanski.asm.command.basic.Command;

import java.util.List;

public class ListCommand {

    @Command(name = "list", description = "show all pets")
    private void list(List<String> args) {
        ShelterUtil.list();
    }
}
