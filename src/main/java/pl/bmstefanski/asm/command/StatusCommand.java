package pl.bmstefanski.asm.command;

import pl.bmstefanski.asm.basic.Shelter;
import pl.bmstefanski.asm.basic.util.ShelterUtil;
import pl.bmstefanski.asm.command.basic.Command;

import java.util.List;

public class StatusCommand {

    private final Shelter shelter;

    public StatusCommand(Shelter shelter) {
        this.shelter = shelter;
    }

    @Command(name = "status", description = "check shelter's capacity")
    private void status(List<String> args) {
        ShelterUtil.status(shelter);
    }
}
