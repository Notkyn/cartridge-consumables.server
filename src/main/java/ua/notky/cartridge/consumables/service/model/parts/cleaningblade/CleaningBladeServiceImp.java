package ua.notky.cartridge.consumables.service.model.parts.cleaningblade;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.notky.cartridge.consumables.model.parts.CleaningBlade;
import ua.notky.cartridge.consumables.repository.parts.cleaningblade.CleaningBladeRepository;

import java.util.List;

import static ua.notky.cartridge.consumables.util.ValidationUtil.*;

@Slf4j
@Service
public class CleaningBladeServiceImp implements CleaningBladeService {
    private CleaningBladeRepository repository;

    @Override
    public CleaningBlade create(CleaningBlade cleaningBlade) {
        log.info("Create new Cleaning Blade: {}", cleaningBlade);
        checkNotNull(cleaningBlade);
        checkNew(cleaningBlade);
        return repository.save(cleaningBlade);
    }

    @Override
    public CleaningBlade update(CleaningBlade cleaningBlade) {
        log.info("Update Cleaning Blade: {}", cleaningBlade);
        checkNotNull(cleaningBlade);
        checkUpdated(cleaningBlade);
        return repository.save(cleaningBlade);
    }

    @Override
    public CleaningBlade get(int id) {
        log.info("Get one Cleaning Blade by id={}", id);
        return checkNotFoundWithId(repository.getById(id), id);
    }

    @Override
    public CleaningBlade getWithCartridges(int id) {
        log.info("Get one Cleaning Blade by id={} with cartridges", id);
        return checkNotFoundWithId(repository.getWithCartridges(id), id);
    }

    @Override
    public void delete(int id) {
        log.info("Delete Cleaning Blade by id={}", id);
        CleaningBlade blade = checkNotFoundWithId(repository.getById(id), id);
        checkDependencySet(blade, blade.getCartridges());
        repository.delete(id);
    }

    @Override
    public List<CleaningBlade> getAll() {
        log.info("Get all Cleaning Blades");
        return repository.getAll();
    }

    @Autowired
    public void setRepository(CleaningBladeRepository repository) {
        this.repository = repository;
    }
}
