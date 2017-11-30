package pl.bmstefanski.asm.basic;

public class Animal {

    private final String name;
    private double health;
    private long id;
    private int age;

    public Animal(String name) {
        this.name = name;
        this.id = id++; // todo
        this.health = 100D;
        this.age = 10;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
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

    @Override
    public String toString() {
        return "id: " + id + " | Name: " + name + " | Health: " + health + " | Age: " + age;
    }
}
