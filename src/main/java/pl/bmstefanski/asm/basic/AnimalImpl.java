package pl.bmstefanski.asm.basic;

import pl.bmstefanski.asm.api.basic.Animal;

import java.sql.Timestamp;
import java.util.UUID;

public class AnimalImpl implements Animal {

    private final String name;
    private UUID uuid;
    private double health;
    private int age;
    private Timestamp birth;

    public AnimalImpl(String name) {
        this.name = name;
        this.uuid = UUID.randomUUID();
        this.health = 100D;
        this.age = 10;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getHealth() {
        return health;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public UUID getUUID() {
        return uuid;
    }

    @Override
    public Timestamp getBirth() {
        return birth;
    }

    @Override
    public void setHealth(double health) {
        this.health = health;
    }

    @Override
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public void setUUID(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public void setBirth(Timestamp birth) {
        this.birth = birth;
    }

    @Override
    public String toString() {
        return "UUID: " + uuid.toString() + " | Name: " + name + " | Health: " + health + " | Age: " + age + " | Birth: " + birth;
    }
}
