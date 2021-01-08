package ua.notky.cartridge.consumables.web.controllers.parts.primarychargeshaft;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import ua.notky.cartridge.consumables.model.parts.PrimaryChargeShaft;
import ua.notky.cartridge.consumables.service.model.parts.primarychargeshaft.PrimaryChargeShaftService;

import java.util.List;

import static ua.notky.cartridge.consumables.util.ValidationUtil.checkNewBean;

@Slf4j
public abstract class AbstractPrimaryChargeShaftController {
    private PrimaryChargeShaftService service;

    List<PrimaryChargeShaft> getAll(){
        log.info("Get all");
        return service.getAll();
    }

    void delete(int id){
        log.info("Delete id={}", id);
        service.delete(id);
    }

    PrimaryChargeShaft get(int id){
        log.info("Get id={}", id);
        return service.get(id);
    }

    void create(PrimaryChargeShaft shaft){
        log.info("Create {}", shaft);
        checkNewBean(shaft);
        service.create(shaft);
    }

    void update(PrimaryChargeShaft shaft){
        log.info("Update id={}, {}",shaft.getId(), shaft);
        service.update(shaft);
    }

    @Autowired
    public void setService(PrimaryChargeShaftService service) {
        this.service = service;
    }
}
