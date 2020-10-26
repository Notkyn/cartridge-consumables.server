package ua.notky.cartridge.consumables.repository.parts.cleaningblade;

import ua.notky.cartridge.consumables.model.parts.CleaningBlade;

import java.util.List;

public interface CleaningBladeRepository {
    CleaningBlade save(CleaningBlade cleaningBlade);
    CleaningBlade getById(int id);
    CleaningBlade getWithCartridges(int id);
    boolean delete(int id);
    List<CleaningBlade> getAll();
}
