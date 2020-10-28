package ua.notky.cartridge.consumables.service.model.parts.magneticshaft;

import org.springframework.transaction.annotation.Transactional;
import ua.notky.cartridge.consumables.model.parts.MagneticShaft;

import java.util.List;

@Transactional(readOnly = true)
public interface MagneticShaftService {
    @Transactional
    MagneticShaft create(MagneticShaft magneticShaft);

    @Transactional
    MagneticShaft update(MagneticShaft magneticShaft);

    MagneticShaft get(int id);
    MagneticShaft getWithCartridges(int id);

    @Transactional
    void delete(int id);

    List<MagneticShaft> getAll();
}
