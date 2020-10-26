package ua.notky.cartridge.consumables.repository.parts.drum;

import ua.notky.cartridge.consumables.model.parts.Drum;

import java.util.List;

public interface DrumRepository {
    Drum save(Drum drum);
    Drum getById(int id);
    Drum getWithCartridges(int id);
    boolean delete(int id);
    List<Drum> getAll();
}
