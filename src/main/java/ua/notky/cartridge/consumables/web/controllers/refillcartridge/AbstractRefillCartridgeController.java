package ua.notky.cartridge.consumables.web.controllers.refillcartridge;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import ua.notky.cartridge.consumables.model.RefillCartridge;
import ua.notky.cartridge.consumables.service.model.refillcartridge.RefillCartridgeService;

import java.util.List;

import static ua.notky.cartridge.consumables.util.ValidationUtil.checkNewBean;

@Slf4j
public class AbstractRefillCartridgeController {
    private RefillCartridgeService service;

    List<RefillCartridge> getAll(){
        log.info("Get all");
        return service.getAll();
    }

    void delete(int id){
        log.info("Delete id={}", id);
        service.delete(id);
    }

    RefillCartridge get(int id){
        log.info("Get id={}", id);
        return service.getWithDepartments(id);
    }

    void create(RefillCartridge refillCartridge){
        log.info("Create {}", refillCartridge);
        checkNewBean(refillCartridge);
        service.create(refillCartridge);
    }

    void update(RefillCartridge refillCartridge){
        log.info("Update id={}, {}", refillCartridge.getId(), refillCartridge);
        service.update(refillCartridge);
    }

    @Autowired
    public void setService(RefillCartridgeService service) {
        this.service = service;
    }
}
