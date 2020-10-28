package ua.notky.cartridge.consumables.repository.parts.dispensingblade;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.notky.cartridge.consumables.model.parts.DispensingBlade;

import java.util.List;

@Slf4j
@Repository
public class DispensingBladeRepositoryImp implements DispensingBladeRepository{
    private CrudDispensingBladeRepository repository;

    @Override
    public DispensingBlade save(DispensingBlade dispensingBlade) {
        log.info("Save to bd: {}", dispensingBlade);
        return repository.save(dispensingBlade);
    }

    @Override
    public DispensingBlade getById(int id) {
        log.info("Find by Id [id={}] from bd", id);
        return repository.findById(id).orElse(null);
    }

    @Override
    public DispensingBlade getWithCartridges(int id) {
        log.info("Find by Id [id={}] from bd with cartridges", id);
        return repository.getWithCartridges(id);
    }

    @Override
    public boolean delete(int id) {
        log.info("Delete from bd with id={}", id);
        return repository.delete(id) != 0;
    }

    @Override
    public List<DispensingBlade> getAll() {
        log.info("Find all from bd");
        return repository.findAll();
    }

    @Autowired
    public void setRepository(CrudDispensingBladeRepository repository) {
        this.repository = repository;
    }
}
