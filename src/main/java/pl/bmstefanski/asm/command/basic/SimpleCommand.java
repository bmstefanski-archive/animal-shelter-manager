package pl.bmstefanski.asm.command.basic;

import java.lang.reflect.Method;

public final class SimpleCommand {

    private final String name;
    private final String description;
    private final String usage;
    private final int min;
    private final int max;
    private final Object object;
    private final Method method;

    public SimpleCommand(String name, String description, String usage, int min, int max, Object object, Method method) {
        this.name = name;
        this.description = description;
        this.usage = usage;
        this.min = min;
        this.max = max;
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

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public String getUsage() {
        return usage;
    }
}
