package ua.notky.cartridge.consumables.service.model.parts.toner;

import org.springframework.transaction.annotation.Transactional;
import ua.notky.cartridge.consumables.model.parts.Toner;

import java.util.List;

@Transactional(readOnly = true)
public interface TonerService {
    @Transactional
    Toner create(Toner toner);

    @Transactional
    Toner update(Toner toner);

    Toner get(int id);
    Toner getWithCartridges(int id);

    @Transactional
    void delete(int id);

    List<Toner> getAll();
}
