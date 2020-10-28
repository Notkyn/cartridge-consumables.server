package ua.notky.cartridge.consumables.service.model.parts.dispensingblade;

import org.springframework.transaction.annotation.Transactional;
import ua.notky.cartridge.consumables.model.parts.DispensingBlade;

import java.util.List;

@Transactional(readOnly = true)
public interface DispensingBladeService {
    @Transactional
    DispensingBlade create(DispensingBlade dispensingBlade);

    @Transactional
    DispensingBlade update(DispensingBlade dispensingBlade);

    DispensingBlade get(int id);
    DispensingBlade getWithCartridges(int id);

    @Transactional
    void delete(int id);

    List<DispensingBlade> getAll();
}
