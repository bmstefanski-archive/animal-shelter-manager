package pl.bmstefanski.asm.api;

import pl.bmstefanski.asm.api.basic.Shelter;
import pl.bmstefanski.asm.api.storage.Storage;
import pl.bmstefanski.asm.storage.manager.AnimalResourceManager;

public interface AnimalShelterManagerApi {

    AnimalResourceManager getResourceManager();

    Storage getStorage();

    Shelter getShelter();
}
