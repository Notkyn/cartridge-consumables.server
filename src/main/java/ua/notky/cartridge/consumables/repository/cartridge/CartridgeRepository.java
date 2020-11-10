package ua.notky.cartridge.consumables.repository.cartridge;

import ua.notky.cartridge.consumables.model.Cartridge;

import java.util.List;

public interface CartridgeRepository {
    Cartridge save(Cartridge cartridge);
    Cartridge getById(int id);
    Cartridge getWithAllParts(int id);
    boolean delete(int id);
    List<Cartridge> getAll();
}
