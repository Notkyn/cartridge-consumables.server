package ua.notky.cartridge.consumables.repository.parts.toner;

import ua.notky.cartridge.consumables.model.parts.Toner;

import java.util.List;

public interface TonerRepository {
    Toner save(Toner toner);
    Toner getById(int id);
    Toner getWithCartridges(int id);
    boolean delete(int id);
    List<Toner> getAll();
}
