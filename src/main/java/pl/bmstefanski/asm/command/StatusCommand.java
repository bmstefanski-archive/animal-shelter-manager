package pl.bmstefanski.asm.command;

import pl.bmstefanski.asm.basic.ShelterImpl;
import pl.bmstefanski.asm.basic.util.ShelterUtil;
import pl.bmstefanski.asm.command.basic.Command;

public class StatusCommand {

    private final ShelterImpl shelter;

    public StatusCommand(ShelterImpl shelter) {
        this.shelter = shelter;
    }

    @Command(name = "status", description = "check shelter's capacity")
    private void status() {
        ShelterUtil.status(shelter);
    }
}
