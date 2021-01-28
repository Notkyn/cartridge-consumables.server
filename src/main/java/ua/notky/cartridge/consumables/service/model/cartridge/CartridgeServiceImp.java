package ua.notky.cartridge.consumables.service.model.cartridge;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.notky.cartridge.consumables.model.Cartridge;
import ua.notky.cartridge.consumables.repository.cartridge.CartridgeRepository;
import ua.notky.cartridge.consumables.service.model.cartridge.CartridgeService;

import java.util.List;

import static ua.notky.cartridge.consumables.util.ValidationUtil.*;

@Slf4j
@Service
public class CartridgeServiceImp implements CartridgeService {
    private CartridgeRepository repository;

    @Override
    public Cartridge create(Cartridge cartridge) {
        log.info("Create new Cartridge: {}", cartridge);
        checkNotNull(cartridge);
        checkNew(cartridge);
        return repository.save(cartridge);
    }

    @Override
    public Cartridge update(Cartridge cartridge) {
        log.info("Update Cartridge: {}", cartridge);
        checkNotNull(cartridge);
        checkUpdated(cartridge);
        return repository.save(cartridge);
    }

    @Override
    public Cartridge get(int id) {
        log.info("Get one Cartridge by id={}", id);
        return checkNotFoundWithId(repository.getById(id), id);
    }

    @Override
    public Cartridge getWithAllParts(int id) {
        log.info("Get one Cartridge by id={} with all parts", id);
        return checkNotFoundWithId(repository.getWithAllParts(id), id);
    }

    @Override
    public void delete(int id) {
        log.info("Delete Cartridge by id={}", id);
        Cartridge cartridge = repository.getWithAllParts(id);
        checkNotFoundWithId(cartridge, id);
        checkDependencySet(cartridge, cartridge.getDepartments());
        repository.delete(id);
    }

    @Override
    public List<Cartridge> getAll() {
        log.info("Get all Cartridges");
        return repository.getAll();
    }

    @Override
    public List<Cartridge> getAllWithAllParts() {
        log.info("Get all Cartridges with all parts");
        return repository.getAllWithAllParts();
    }

    @Autowired
    public void setRepository(CartridgeRepository repository) {
        this.repository = repository;
    }
}
