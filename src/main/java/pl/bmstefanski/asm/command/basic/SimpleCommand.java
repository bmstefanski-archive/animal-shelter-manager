package pl.bmstefanski.asm.command.basic;

import java.lang.reflect.Method;

public final class SimpleCommand {

    private final String name;
    private final String description;
    private final Object object;
    private final Method method;

    public SimpleCommand(String name, String description, Object object, Method method) {
        this.name = name;
        this.description = description;
        this.object = object;
        this.method = method;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Object getObject() {
        return object;
    }

    public Method getMethod() {
        return method;
    }
}
