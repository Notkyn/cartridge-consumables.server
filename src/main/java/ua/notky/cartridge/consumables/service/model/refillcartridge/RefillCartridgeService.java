package ua.notky.cartridge.consumables.service.model.refillcartridge;

import org.springframework.transaction.annotation.Transactional;
import ua.notky.cartridge.consumables.model.RefillCartridge;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface RefillCartridgeService {
    @Transactional
    RefillCartridge create(RefillCartridge refillCartridge);

    @Transactional
    RefillCartridge update(RefillCartridge refillCartridge);

    RefillCartridge get(int id);

    @Transactional
    void delete(int id);

    List<RefillCartridge> getAll();
    List<LocalDate> getAllDates();
    List<RefillCartridge> getAllByDateWithDepartment(LocalDate date);
}
