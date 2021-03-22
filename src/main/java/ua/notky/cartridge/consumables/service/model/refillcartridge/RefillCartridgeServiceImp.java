package ua.notky.cartridge.consumables.service.model.refillcartridge;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.notky.cartridge.consumables.model.RefillCartridge;
import ua.notky.cartridge.consumables.repository.refillcartridge.RefillCartridgeRepository;

import java.time.LocalDate;
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

    @Override
    public List<LocalDate> getAllDates() {
        log.info("Get all Dates from All Refill Cartidges");
        return repository.getAllDates();
    }

    @Override
    public List<RefillCartridge> getAllByDateWithDepartment(LocalDate date) {
        log.info("Get All by Date with Department [date={}] from bd", date);
        return repository.getAllByDateWithDepartment(date);
    }

    @Autowired
    public void setRepository(RefillCartridgeRepository repository) {
        this.repository = repository;
    }
}
