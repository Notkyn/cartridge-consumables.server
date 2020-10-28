package ua.notky.cartridge.consumables.service.model.parts.primarychargeshaft;

import org.springframework.transaction.annotation.Transactional;
import ua.notky.cartridge.consumables.model.parts.PrimaryChargeShaft;

import java.util.List;

@Transactional(readOnly = true)
public interface PrimaryChargeShaftService {
    @Transactional
    PrimaryChargeShaft create(PrimaryChargeShaft primaryChargeShaft);

    @Transactional
    PrimaryChargeShaft update(PrimaryChargeShaft primaryChargeShaft);

    PrimaryChargeShaft get(int id);
    PrimaryChargeShaft getWithCartridges(int id);

    @Transactional
    void delete(int id);

    List<PrimaryChargeShaft> getAll();
}
