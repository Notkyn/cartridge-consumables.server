package ua.notky.cartridge.consumables.service.model.cartridge;

import org.springframework.transaction.annotation.Transactional;
import ua.notky.cartridge.consumables.model.Cartridge;

import java.util.List;

@Transactional(readOnly = true)
public interface CartridgeService {
    @Transactional
    Cartridge create(Cartridge cartridge);

    @Transactional
    Cartridge update(Cartridge cartridge);

    Cartridge get(int id);
    Cartridge getWithAllParts(int id);

    @Transactional
    void delete(int id);

    List<Cartridge> getAll();
    List<Cartridge> getAllWithAllParts();
}
