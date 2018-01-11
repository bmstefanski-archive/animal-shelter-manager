package pl.bmstefanski.asm.basic;

import pl.bmstefanski.asm.api.basic.Shelter;

public class ShelterImpl implements Shelter {

    private int capacity;
    private final String name;

    public ShelterImpl(int capacity, String name) {
        this.capacity = capacity;
        this.name = name;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public String getName() {
        return name;
    }
}
