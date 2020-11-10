package ua.notky.cartridge.consumables.service.model.parts.toner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.notky.cartridge.consumables.model.parts.Toner;
import ua.notky.cartridge.consumables.repository.parts.toner.TonerRepository;

import java.util.List;

import static ua.notky.cartridge.consumables.util.ValidationUtil.*;

@Slf4j
@Service
public class TonerServiceImp implements TonerService {
    private TonerRepository repository;

    @Override
    public Toner create(Toner toner) {
        log.info("Create new Toner: {}", toner);
        checkNotNull(toner);
        checkNew(toner);
        return repository.save(toner);
    }

    @Override
    public Toner update(Toner toner) {
        log.info("Update Toner: {}", toner);
        checkNotNull(toner);
        checkUpdated(toner);
        return repository.save(toner);
    }

    @Override
    public Toner get(int id) {
        log.info("Get one Toner by id={}", id);
        return checkNotFoundWithId(repository.getById(id), id);
    }

    @Override
    public Toner getWithCartridges(int id) {
        log.info("Get one Toner by id={} with cartridges", id);
        return checkNotFoundWithId(repository.getWithCartridges(id), id);
    }

    @Override
    public void delete(int id) {
        log.info("Delete Toner by id={}", id);
        Toner toner = checkNotFoundWithId(repository.getById(id), id);
        checkDependencySet(toner, toner.getCartridges());
        repository.delete(id);
    }

    @Override
    public List<Toner> getAll() {
        log.info("Get all Toners");
        return repository.getAll();
    }

    @Autowired
    public void setRepository(TonerRepository repository) {
        this.repository = repository;
    }
}
