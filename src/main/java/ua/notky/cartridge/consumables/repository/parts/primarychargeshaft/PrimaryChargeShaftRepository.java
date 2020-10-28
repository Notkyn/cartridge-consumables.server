package ua.notky.cartridge.consumables.repository.parts.primarychargeshaft;

import ua.notky.cartridge.consumables.model.parts.PrimaryChargeShaft;

import java.util.List;

public interface PrimaryChargeShaftRepository {
    PrimaryChargeShaft save(PrimaryChargeShaft primaryChargeShaft);
    PrimaryChargeShaft getById(int id);
    PrimaryChargeShaft getWithCartridges(int id);
    boolean delete(int id);
    List<PrimaryChargeShaft> getAll();
}
