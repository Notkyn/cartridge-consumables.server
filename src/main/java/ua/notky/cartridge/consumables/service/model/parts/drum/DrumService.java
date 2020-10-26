package ua.notky.cartridge.consumables.service.model.parts.drum;

import org.springframework.transaction.annotation.Transactional;
import ua.notky.cartridge.consumables.model.parts.Drum;

import java.util.List;

@Transactional(readOnly = true)
public interface DrumService {
    @Transactional
    Drum create(Drum drum);

    @Transactional
    Drum update(Drum drum);

    Drum get(int id);
    Drum getWithCartridges(int id);

    @Transactional
    void delete(int id);

    List<Drum> getAll();
}
