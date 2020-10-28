package ua.notky.cartridge.consumables.repository.parts.dispensingblade;

import ua.notky.cartridge.consumables.model.parts.DispensingBlade;

import java.util.List;

public interface DispensingBladeRepository {
    DispensingBlade save(DispensingBlade dispensingBlade);
    DispensingBlade getById(int id);
    DispensingBlade getWithCartridges(int id);
    boolean delete(int id);
    List<DispensingBlade> getAll();
}
