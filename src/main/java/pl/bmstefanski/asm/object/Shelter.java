package pl.bmstefanski.asm.object;

public class Shelter {

    private int capacity = 0;
    private final String name;

    public Shelter(int capacity, String name) {
        this.capacity = capacity;
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getName() {
        return name;
    }
}
