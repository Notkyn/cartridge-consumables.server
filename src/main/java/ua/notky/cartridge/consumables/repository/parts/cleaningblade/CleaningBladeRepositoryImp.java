package ua.notky.cartridge.consumables.repository.parts.cleaningblade;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.notky.cartridge.consumables.model.parts.CleaningBlade;

import java.util.List;

@Slf4j
@Repository
public class CleaningBladeRepositoryImp implements CleaningBladeRepository {
    private CrudCleaningBladeRepository repository;

    @Override
    public CleaningBlade save(CleaningBlade cleaningBlade) {
        log.info("Save to bd: {}", cleaningBlade);
        return repository.save(cleaningBlade);
    }

    @Override
    public CleaningBlade getById(int id) {
        log.info("Find by Id [id={}] from bd", id);
        return repository.findById(id).orElse(null);
    }

    @Override
    public CleaningBlade getWithCartridges(int id) {
        log.info("Find by Id [id={}] from bd with cartridges", id);
        return repository.getWithCartridges(id);
    }

    @Override
    public boolean delete(int id) {
        log.info("Delete from bd with id={}", id);
        return repository.delete(id) != 0;
    }

    @Override
    public List<CleaningBlade> getAll() {
        log.info("Find all from bd");
        return repository.findAll();
    }

    @Autowired
    public void setRepository(CrudCleaningBladeRepository repository) {
        this.repository = repository;
    }
}
