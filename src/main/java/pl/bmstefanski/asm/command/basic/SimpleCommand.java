package pl.bmstefanski.asm.command.basic;

import java.lang.reflect.Method;

public final class SimpleCommand {

    private final String name;
    private final Object object;
    private final Method method;

    public SimpleCommand(String name, Object object, Method method) {
        this.name = name;
        this.object = object;
        this.method = method;
    }

    public String getName() {
        return name;
    }

    public Object getObject() {
        return object;
    }

    public Method getMethod() {
        return method;
    }
}
