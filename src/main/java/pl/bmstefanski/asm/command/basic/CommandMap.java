package pl.bmstefanski.asm.command.basic;

import pl.bmstefanski.asm.AnimalShelterManager;
import pl.bmstefanski.asm.command.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;
import java.util.stream.Collectors;

public class CommandMap {

    private final AnimalShelterManager main = new AnimalShelterManager();
    private final Map<String, SimpleCommand> commands = new HashMap<>();
    private final String prefix = "!";

    public CommandMap() {
        registerCommand(new AddCommand(main));
        registerCommand(new AnimalCommand());
        registerCommand(new ListCommand());
        registerCommand(new RemoveCommand(main));
        registerCommand(new SaveCommand(main));
        registerCommand(new StatusCommand(main));
    }

    private void registerCommand(Object object) {
        for (Method method : object.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(Command.class)) {
                Command command = method.getAnnotation(Command.class);
                method.setAccessible(true);
                SimpleCommand simpleCommand = new SimpleCommand(command.name(), command.description(), object, method);
                commands.put(command.name(), simpleCommand);
            }
        }
    }

    public void commandUser(String command) {
        Object[] objects = getCommand(command);

        if (objects[0] == null) return;

        try {
            execute( ((SimpleCommand) objects[0]), command, (List<?>) objects[1]) ;
        } catch(IllegalAccessException | InvocationTargetException ex) {
            ex.printStackTrace();
        }

    }

    private Object[] getCommand(String command) {
        List<String> arguments = Arrays.stream(command.split(" ")).collect(Collectors.toList());

        SimpleCommand simpleCommand = commands.get(arguments.get(0));
        return new Object[] {simpleCommand, arguments};
    }



    private void execute(SimpleCommand simpleCommand, String command, List<?> args) throws InvocationTargetException, IllegalAccessException {
        Parameter[] parameters = simpleCommand.getMethod().getParameters();
        Object[] objects = new Object[parameters.length];

        for (int i = 0; i < parameters.length; i++) {
            if (parameters[i].getType() == List.class) objects[i] = args;
            else if (parameters[i].getType() == String.class) objects[i] = command;
        }

        simpleCommand.getMethod().invoke(simpleCommand.getObject(), objects);
    }

    public Collection<SimpleCommand> getCommands() {
        return commands.values();
    }

    public String getPrefix() {
        return prefix;
    }
}
