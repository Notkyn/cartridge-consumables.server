package ua.notky.cartridge.consumables.repository.parts.drum;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.notky.cartridge.consumables.model.parts.Drum;

import java.util.List;

@Slf4j
@Repository
public class DrumRepositoryImp implements DrumRepository {
    private CrudDrumRepository repository;

    @Override
    public Drum save(Drum drum) {
        log.info("Save to bd: {}", drum);
        return repository.save(drum);
    }

    @Override
    public Drum getById(int id) {
        log.info("Find by Id [id={}] from bd", id);
        return repository.findById(id).orElse(null);
    }

    @Override
    public Drum getWithCartridges(int id) {
        log.info("Find by Id [id={}] from bd with cartridges", id);
        return repository.getWithCartridges(id);
    }

    @Override
    public boolean delete(int id) {
        log.info("Delete from bd with id={}", id);
        return repository.delete(id) != 0;
    }

    @Override
    public List<Drum> getAll() {
        log.info("Find all from bd");
        return repository.findAll();
    }

    @Autowired
    public void setRepository(CrudDrumRepository repository) {
        this.repository = repository;
    }
}
