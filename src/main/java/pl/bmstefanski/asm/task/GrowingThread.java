package pl.bmstefanski.asm.task;

import pl.bmstefanski.asm.basic.util.ShelterUtil;

import java.util.Random;

public class GrowingThread implements Runnable {

    private final Random random = new Random();

    @Override
    public void run() {
        ShelterUtil.ANIMALS.forEach((key, value) -> value.setAge(value.getAge() + random.nextInt(5)));
    }
}
