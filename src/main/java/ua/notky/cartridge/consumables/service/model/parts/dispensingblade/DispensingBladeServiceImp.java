package ua.notky.cartridge.consumables.service.model.parts.dispensingblade;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.notky.cartridge.consumables.model.parts.DispensingBlade;
import ua.notky.cartridge.consumables.repository.parts.dispensingblade.DispensingBladeRepository;

import java.util.List;

import static ua.notky.cartridge.consumables.util.ValidationUtil.*;

@Slf4j
@Service
public class DispensingBladeServiceImp implements DispensingBladeService{
    private DispensingBladeRepository repository;

    @Override
    public DispensingBlade create(DispensingBlade dispensingBlade) {
        log.info("Create new Dispensing Blade: {}", dispensingBlade);
        checkNotNull(dispensingBlade);
        checkNew(dispensingBlade);
        return repository.save(dispensingBlade);
    }

    @Override
    public DispensingBlade update(DispensingBlade dispensingBlade) {
        log.info("Update Dispensing Blade: {}", dispensingBlade);
        checkNotNull(dispensingBlade);
        checkUpdated(dispensingBlade);
        return repository.save(dispensingBlade);
    }

    @Override
    public DispensingBlade get(int id) {
        log.info("Get one Dispensing Blade by id={}", id);
        return checkNotFoundWithId(repository.getById(id), id);
    }

    @Override
    public DispensingBlade getWithCartridges(int id) {
        log.info("Get one Dispensing Blade by id={} with cartridges", id);
        return checkNotFoundWithId(repository.getWithCartridges(id), id);
    }

    @Override
    public void delete(int id) {
        log.info("Delete Dispensing Blade by id={}", id);
        DispensingBlade blade = checkNotFoundWithId(repository.getById(id), id);
        checkDependencySet(blade, blade.getCartridges());
        repository.delete(id);
    }

    @Override
    public List<DispensingBlade> getAll() {
        log.info("Get all Dispensing Blades");
        return repository.getAll();
    }

    @Autowired
    public void setRepository(DispensingBladeRepository repository) {
        this.repository = repository;
    }
}
