package ua.notky.cartridge.consumables.repository.refillcartridge;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.notky.cartridge.consumables.model.RefillCartridge;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Repository
public class RefillCartridgeRepositoryImp implements RefillCartridgeRepository {
    private CrudRefillCartridgeRepository repository;

    @Override
    public RefillCartridge save(RefillCartridge refillCartridge) {
        log.info("Save to bd: {}", refillCartridge);
        return repository.save(refillCartridge);
    }

    @Override
    public RefillCartridge getById(int id) {
        log.info("Find by Id [id={}] from bd", id);
        return repository.findById(id).orElse(null);
    }

    @Override
    public boolean delete(RefillCartridge refillCartridge) {
        log.info("Delete from bd with id={}", refillCartridge.getId());
        repository.delete(refillCartridge);
        return repository.findById(refillCartridge.getId()).orElse(null) == null;
    }

    @Override
    public List<RefillCartridge> getAll() {
        log.info("Find all from bd");
        return repository.findAll();
    }

    @Override
    public List<LocalDate> getAllDates() {
        log.info("Find All dates from all objects");
        return repository.getAllDates();
    }

    @Override
    public List<RefillCartridge> getAllByDateWithDepartment(LocalDate date) {
        log.info("Find all by Date [date={}] from bd", date);
        return repository.getAllByDateWithDepartment(date);
    }

    @Autowired
    public void setRepository(CrudRefillCartridgeRepository repository) {
        this.repository = repository;
    }
}
