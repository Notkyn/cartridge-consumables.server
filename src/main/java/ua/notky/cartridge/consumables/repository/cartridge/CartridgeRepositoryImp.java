package ua.notky.cartridge.consumables.repository.cartridge;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.notky.cartridge.consumables.model.Cartridge;

import java.util.List;

@Slf4j
@Repository
public class CartridgeRepositoryImp implements CartridgeRepository {
    private CrudCartridgeRepository repository;

    @Override
    public Cartridge save(Cartridge cartridge) {
        log.info("Save to bd: {}", cartridge);
        return repository.save(cartridge);
    }

    @Override
    public Cartridge getById(int id) {
        log.info("Find by Id [id={}] from bd", id);
        return repository.findById(id).orElse(null);
    }

    @Override
    public boolean delete(int id) {
        log.info("Delete from bd with id={}", id);
        return repository.delete(id) != 0;
    }

    @Override
    public List<Cartridge> getAll() {
        log.info("Find all from bd");
        return repository.findAll();
    }

    @Autowired
    public void setRepository(CrudCartridgeRepository repository) {
        this.repository = repository;
    }
}
