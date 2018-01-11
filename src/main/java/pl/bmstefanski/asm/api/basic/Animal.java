package pl.bmstefanski.asm.api.basic;

import java.sql.Timestamp;
import java.util.UUID;

public interface Animal {

    String getName();

    double getHealth();

    int getAge();

    UUID getUUID();

    Timestamp getBirth();

    void setHealth(double health);

    void setAge(int age);

    void setUUID(UUID uuid);

    void setBirth(Timestamp birth);
}
