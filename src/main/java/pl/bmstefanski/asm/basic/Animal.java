package pl.bmstefanski.asm.basic;

import java.sql.Timestamp;
import java.util.UUID;

public class Animal {

    private final String name;
    private UUID uuid;
    private double health;
    private int age;
    private Timestamp birth;

    public Animal(String name) {
        this.name = name;
        this.uuid = UUID.randomUUID();
        this.health = 100D;
        this.age = 10;
    }

    public String getName() {
        return name;
    }

    public double getHealth() {
        return health;
    }

    public int getAge() {
        return age;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public UUID getUUID() {
        return uuid;
    }

    public void setUUID(UUID uuid) {
        this.uuid = uuid;
    }

    public Timestamp getBirth() {
        return birth;
    }

    public void setBirth(Timestamp birth) {
        this.birth = birth;
    }

    @Override
    public String toString() {
        return "UUID: " + uuid.toString() + " | Name: " + name + " | Health: " + health + " | Age: " + age + " | Birth: " + birth;
    }
}
