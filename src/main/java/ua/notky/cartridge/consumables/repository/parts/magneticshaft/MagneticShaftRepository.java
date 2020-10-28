package ua.notky.cartridge.consumables.repository.parts.magneticshaft;

import ua.notky.cartridge.consumables.model.parts.MagneticShaft;

import java.util.List;

public interface MagneticShaftRepository {
    MagneticShaft save(MagneticShaft magneticShaft);
    MagneticShaft getById(int id);
    MagneticShaft getWithCartridges(int id);
    boolean delete(int id);
    List<MagneticShaft> getAll();
}
