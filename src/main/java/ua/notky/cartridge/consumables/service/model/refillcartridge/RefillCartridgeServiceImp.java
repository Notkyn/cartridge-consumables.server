package ua.notky.cartridge.consumables.service.model.refillcartridge;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.notky.cartridge.consumables.model.RefillCartridge;
import ua.notky.cartridge.consumables.repository.refillcartridge.RefillCartridgeRepository;

import java.util.List;

import static ua.notky.cartridge.consumables.util.ValidationUtil.*;

@Slf4j
@Service
public class RefillCartridgeServiceImp implements RefillCartridgeService {
    private RefillCartridgeRepository repository;

    @Override
    public RefillCartridge create(RefillCartridge refillCartridge) {
        log.info("Create new Refill Cartridge: {}", refillCartridge);
        checkNotNull(refillCartridge);
        checkNew(refillCartridge);
        return repository.save(refillCartridge);
    }

    @Override
    public RefillCartridge update(RefillCartridge refillCartridge) {
        log.info("Update Refill Cartridge: {}", refillCartridge);
        checkNotNull(refillCartridge);
        checkUpdated(refillCartridge);
        return repository.save(refillCartridge);
    }

    @Override
    public RefillCartridge get(int id) {
        log.info("Get one Refill Cartridge by id={}", id);
        return checkNotFoundWithId(repository.getById(id), id);
    }

    @Override
    public RefillCartridge getWithDepartments(int id) {
        log.info("Get one Refill Cartridge by id={} with Departments", id);
        return checkNotFoundWithId(repository.getWithDepartments(id), id);
    }

    @Override
    public void delete(int id) {
        log.info("Delete Refill Cartridge by id={}", id);
        RefillCartridge refillCartridge = repository.getById(id);
        checkNotFoundWithId(refillCartridge, id);
        repository.delete(refillCartridge);
    }

    @Override
    public List<RefillCartridge> getAll() {
        log.info("Get all Refill Cartridge");
        return repository.getAll();
    }

    @Autowired
    public void setRepository(RefillCartridgeRepository repository) {
        this.repository = repository;
    }
}
