package ua.notky.cartridge.consumables.service.model.parts.drum;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.notky.cartridge.consumables.model.parts.Drum;
import ua.notky.cartridge.consumables.repository.parts.drum.DrumRepository;

import java.util.List;

import static ua.notky.cartridge.consumables.util.ValidationUtil.*;

@Slf4j
@Service
public class DrumServiceImp implements DrumService{
    private DrumRepository repository;

    @Override
    public Drum create(Drum drum) {
        log.info("Create new Drum: {}", drum);
        checkNotNull(drum);
        checkNew(drum);
        return repository.save(drum);
    }

    @Override
    public Drum update(Drum drum) {
        log.info("Update Drum: {}", drum);
        checkNotNull(drum);
        checkUpdated(drum);
        return repository.save(drum);
    }

    @Override
    public Drum get(int id) {
        log.info("Get one Drum by id={}", id);
        return checkNotFoundWithId(repository.getById(id), id);
    }

    @Override
    public Drum getWithCartridges(int id) {
        log.info("Get one Drum by id={} with cartridges", id);
        return checkNotFoundWithId(repository.getWithCartridges(id), id);
    }

    @Override
    public void delete(int id) {
        log.info("Delete Drum by id={}", id);
        Drum drum = checkNotFoundWithId(repository.getById(id), id);
        checkDependencySet(drum, drum.getCartridges());
        repository.delete(id);
    }

    @Override
    public List<Drum> getAll() {
        log.info("Get all Drums");
        return repository.getAll();
    }

    @Autowired
    public void setRepository(DrumRepository repository) {
        this.repository = repository;
    }
}
