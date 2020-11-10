package ua.notky.cartridge.consumables.repository.parts.toner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.notky.cartridge.consumables.model.parts.Toner;

import java.util.List;

@Slf4j
@Repository
public class TonerRepositoryImp implements TonerRepository {
    private CrudTonerRepository repository;

    @Override
    public Toner save(Toner toner) {
        log.info("Save to bd: {}", toner);
        return repository.save(toner);
    }

    @Override
    public Toner getById(int id) {
        log.info("Find by Id [id={}] from bd", id);
        return repository.findById(id).orElse(null);
    }

    @Override
    public Toner getWithCartridges(int id) {
        log.info("Find by Id [id={}] from bd with cartridges", id);
        return repository.getWithCartridges(id);
    }

    @Override
    public boolean delete(int id) {
        log.info("Delete from bd with id={}", id);
        return repository.delete(id) != 0;
    }

    @Override
    public List<Toner> getAll() {
        log.info("Find all from bd");
        return repository.findAll();
    }

    @Autowired
    public void setRepository(CrudTonerRepository repository) {
        this.repository = repository;
    }
}
