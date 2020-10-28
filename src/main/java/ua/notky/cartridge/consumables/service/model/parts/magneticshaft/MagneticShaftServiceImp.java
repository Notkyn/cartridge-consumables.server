package ua.notky.cartridge.consumables.service.model.parts.magneticshaft;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.notky.cartridge.consumables.model.parts.MagneticShaft;
import ua.notky.cartridge.consumables.repository.parts.magneticshaft.MagneticShaftRepository;

import java.util.List;

import static ua.notky.cartridge.consumables.util.ValidationUtil.*;

@Slf4j
@Service
public class MagneticShaftServiceImp implements MagneticShaftService {
    private MagneticShaftRepository repository;

    @Override
    public MagneticShaft create(MagneticShaft magneticShaft) {
        log.info("Create new Magnetic Shaft: {}", magneticShaft);
        checkNotNull(magneticShaft);
        checkNew(magneticShaft);
        return repository.save(magneticShaft);
    }

    @Override
    public MagneticShaft update(MagneticShaft magneticShaft) {
        log.info("Update Magnetic Shaft: {}", magneticShaft);
        checkNotNull(magneticShaft);
        checkUpdated(magneticShaft);
        return repository.save(magneticShaft);
    }

    @Override
    public MagneticShaft get(int id) {
        log.info("Get one Magnetic Shaft by id={}", id);
        return checkNotFoundWithId(repository.getById(id), id);
    }

    @Override
    public MagneticShaft getWithCartridges(int id) {
        log.info("Get one Magnetic Shaft by id={} with cartridges", id);
        return checkNotFoundWithId(repository.getWithCartridges(id), id);
    }

    @Override
    public void delete(int id) {
        log.info("Delete Magnetic Shaft by id={}", id);
        MagneticShaft shaft = checkNotFoundWithId(repository.getById(id), id);
        checkDependencySet(shaft, shaft.getCartridges());
        repository.delete(id);
    }

    @Override
    public List<MagneticShaft> getAll() {
        log.info("Get all Magnetic Shaft");
        return repository.getAll();
    }

    @Autowired
    public void setRepository(MagneticShaftRepository repository) {
        this.repository = repository;
    }
}
