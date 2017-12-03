package pl.bmstefanski.asm.task;

import pl.bmstefanski.asm.basic.util.ShelterUtil;
import pl.bmstefanski.asm.database.MySQL;

import java.util.Random;

public class GrowingThread implements Runnable {

    private final Random random = new Random();
    private static final MySQL mySQL = MySQL.getInstance();

    @Override
    public void run() {
        ShelterUtil.ANIMALS.forEach((key, value) -> value.setAge(value.getAge() + random.nextInt(5)));
    }
}
