package ua.notky.cartridge.consumables.repository.parts.primarychargeshaft;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.notky.cartridge.consumables.model.parts.PrimaryChargeShaft;

import java.util.List;

@Slf4j
@Repository
public class PrimaryChargeShaftRepositoryImp implements PrimaryChargeShaftRepository{
    private CrudPrimaryChargeShaftRepository repository;

    @Override
    public PrimaryChargeShaft save(PrimaryChargeShaft primaryChargeShaft) {
        log.info("Save to bd: {}", primaryChargeShaft);
        return repository.save(primaryChargeShaft);
    }

    @Override
    public PrimaryChargeShaft getById(int id) {
        log.info("Find by Id [id={}] from bd", id);
        return repository.findById(id).orElse(null);
    }

    @Override
    public PrimaryChargeShaft getWithCartridges(int id) {
        log.info("Find by Id [id={}] from bd with cartridges", id);
        return repository.getWithCartridges(id);
    }

    @Override
    public boolean delete(int id) {
        log.info("Delete from bd with id={}", id);
        return repository.delete(id) != 0;
    }

    @Override
    public List<PrimaryChargeShaft> getAll() {
        log.info("Find all from bd");
        return repository.findAll();
    }

    @Autowired
    public void setRepository(CrudPrimaryChargeShaftRepository repository) {
        this.repository = repository;
    }
}
