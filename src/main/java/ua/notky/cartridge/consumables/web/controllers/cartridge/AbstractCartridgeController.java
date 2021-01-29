package ua.notky.cartridge.consumables.web.controllers.cartridge;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import ua.notky.cartridge.consumables.model.Cartridge;
import ua.notky.cartridge.consumables.service.model.cartridge.CartridgeService;

import java.util.List;

import static ua.notky.cartridge.consumables.util.ValidationUtil.checkNewBean;

@Slf4j
public abstract class AbstractCartridgeController {
    private CartridgeService service;

    List<Cartridge> getAll(){
        log.info("Get all");
        return service.getAllWithAllParts();
    }

    void delete(int id){
        log.info("Delete id={}", id);
        service.delete(id);
    }

    Cartridge get(int id){
        log.info("Get id={}", id);
        return service.getWithAllParts(id);
    }

    void create(Cartridge cartridge){
        log.info("Create {}", cartridge);
        checkNewBean(cartridge);
        service.create(cartridge);
    }

    void update(Cartridge cartridge){
        log.info("Update id={}, {}",cartridge.getId(), cartridge);
        service.update(cartridge);
    }

    @Autowired
    public void setService(CartridgeService service) {
        this.service = service;
    }
}
