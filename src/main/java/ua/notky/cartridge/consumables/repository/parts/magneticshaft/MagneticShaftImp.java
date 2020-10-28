package ua.notky.cartridge.consumables.repository.parts.magneticshaft;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.notky.cartridge.consumables.model.parts.MagneticShaft;

import java.util.List;

@Slf4j
@Repository
public class MagneticShaftImp implements MagneticShaftRepository {
    private CrudMagneticShaftRepository repository;

    @Override
    public MagneticShaft save(MagneticShaft magneticShaft) {
        log.info("Save to bd: {}", magneticShaft);
        return repository.save(magneticShaft);
    }

    @Override
    public MagneticShaft getById(int id) {
        log.info("Find by Id [id={}] from bd", id);
        return repository.findById(id).orElse(null);
    }

    @Override
    public MagneticShaft getWithCartridges(int id) {
        log.info("Find by Id [id={}] from bd with cartridges", id);
        return repository.getWithCartridges(id);
    }

    @Override
    public boolean delete(int id) {
        log.info("Delete from bd with id={}", id);
        return repository.delete(id) != 0;
    }

    @Override
    public List<MagneticShaft> getAll() {
        log.info("Find all from bd");
        return repository.findAll();
    }

    @Autowired
    public void setRepository(CrudMagneticShaftRepository repository) {
        this.repository = repository;
    }
}
