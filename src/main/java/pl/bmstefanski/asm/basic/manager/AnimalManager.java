package pl.bmstefanski.asm.basic.manager;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import pl.bmstefanski.asm.api.basic.Animal;
import pl.bmstefanski.asm.basic.AnimalImpl;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class AnimalManager {

    private final static Map<String, Animal> ANIMAL_MAP = new HashMap<>();
    private final static Cache<String, Animal> ANIMAL_MAP_CACHE = CacheBuilder.newBuilder().expireAfterAccess(5, TimeUnit.MINUTES).build();

    public static Animal get(String name) {
        Animal animal = ANIMAL_MAP_CACHE.getIfPresent(name);

        if (animal == null) {
            animal = ANIMAL_MAP.get(name);

            if (animal != null) {
                ANIMAL_MAP_CACHE.put(name, animal);
                return animal;
            }

            return new AnimalImpl(name);
        }

        return animal;
    }

    public static Map<String, Animal> getAnimalMap() {
        return ANIMAL_MAP;
    }
}
