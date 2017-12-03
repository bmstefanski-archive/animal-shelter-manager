package pl.bmstefanski.asm.command;

import pl.bmstefanski.asm.command.basic.Command;

import java.util.List;

public class ExampleCommand {

    @Command(name = "example")
    private void example(List<String> args) {
        System.out.println("Hello World!");

    }
}
