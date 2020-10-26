package ua.notky.cartridge.consumables.service.model.parts.cleaningblade;

import org.springframework.transaction.annotation.Transactional;
import ua.notky.cartridge.consumables.model.parts.CleaningBlade;

import java.util.List;

@Transactional(readOnly = true)
public interface CleaningBladeService {
    @Transactional
    CleaningBlade create(CleaningBlade cleaningBlade);

    @Transactional
    CleaningBlade update(CleaningBlade cleaningBlade);

    CleaningBlade get(int id);
    CleaningBlade getWithCartridges(int id);

    @Transactional
    void delete(int id);

    List<CleaningBlade> getAll();
}
