package ua.notky.cartridge.consumables.service.model.parts.primarychargeshaft;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.notky.cartridge.consumables.model.parts.PrimaryChargeShaft;
import ua.notky.cartridge.consumables.repository.parts.primarychargeshaft.PrimaryChargeShaftRepository;

import java.util.List;

import static ua.notky.cartridge.consumables.util.ValidationUtil.*;

@Slf4j
@Service
public class PrimaryChargeShaftServiceImp implements PrimaryChargeShaftService {
    private PrimaryChargeShaftRepository repository;

    @Override
    public PrimaryChargeShaft create(PrimaryChargeShaft primaryChargeShaft) {
        log.info("Create new Primary Charge Shaft: {}", primaryChargeShaft);
        checkNotNull(primaryChargeShaft);
        checkNew(primaryChargeShaft);
        return repository.save(primaryChargeShaft);
    }

    @Override
    public PrimaryChargeShaft update(PrimaryChargeShaft primaryChargeShaft) {
        log.info("Update Primary Charge Shaft: {}", primaryChargeShaft);
        checkNotNull(primaryChargeShaft);
        checkUpdated(primaryChargeShaft);
        return repository.save(primaryChargeShaft);
    }

    @Override
    public PrimaryChargeShaft get(int id) {
        log.info("Get one Primary Charge Shaft by id={}", id);
        return checkNotFoundWithId(repository.getById(id), id);
    }

    @Override
    public PrimaryChargeShaft getWithCartridges(int id) {
        log.info("Get one Primary Charge Shaft by id={} with cartridges", id);
        return checkNotFoundWithId(repository.getWithCartridges(id), id);
    }

    @Override
    public void delete(int id) {
        log.info("Delete Primary Charge Shaft by id={}", id);
        PrimaryChargeShaft shaft = checkNotFoundWithId(repository.getById(id), id);
        checkDependencySet(shaft, shaft.getCartridges());
        repository.delete(id);
    }

    @Override
    public List<PrimaryChargeShaft> getAll() {
        log.info("Get all Primary Charge Shafts");
        return repository.getAll();
    }

    @Autowired
    public void setRepository(PrimaryChargeShaftRepository repository) {
        this.repository = repository;
    }
}
