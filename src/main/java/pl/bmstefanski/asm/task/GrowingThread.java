package pl.bmstefanski.asm.task;

import pl.bmstefanski.asm.basic.util.ShelterUtil;

public class GrowingThread implements Runnable {

    @Override
    public void run() {
        ShelterUtil.ANIMALS.forEach((key, value) -> value.setAge(value.getAge() + 1));
    }
}
