package ua.notky.cartridge.consumables.repository.refillcartridge;

import ua.notky.cartridge.consumables.model.RefillCartridge;

import java.time.LocalDate;
import java.util.List;

public interface RefillCartridgeRepository {
    RefillCartridge save(RefillCartridge refillCartridge);
    RefillCartridge getById(int id);
    boolean delete(RefillCartridge refillCartridge);
    List<RefillCartridge> getAll();
    List<LocalDate> getAllDates();
    List<RefillCartridge> getAllByDateWithDepartment(LocalDate date);
}
