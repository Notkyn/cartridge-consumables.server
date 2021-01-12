package ua.notky.cartridge.consumables.web.controllers.parts.dispensingblade;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import ua.notky.cartridge.consumables.model.parts.DispensingBlade;
import ua.notky.cartridge.consumables.service.model.parts.dispensingblade.DispensingBladeService;

import java.util.List;

import static ua.notky.cartridge.consumables.util.ValidationUtil.checkNewBean;

@Slf4j
public abstract class AbstractDispensingBladeController {
    private DispensingBladeService service;

    List<DispensingBlade> getAll(){
        log.info("Get all");
        return service.getAll();
    }

    void delete(int id){
        log.info("Delete id={}", id);
        service.delete(id);
    }

    DispensingBlade get(int id){
        log.info("Get id={}", id);
        return service.get(id);
    }

    void create(DispensingBlade blade){
        log.info("Create {}", blade);
        checkNewBean(blade);
        service.create(blade);
    }

    void update(DispensingBlade blade){
        log.info("Update id={}, {}",blade.getId(), blade);
        service.update(blade);
    }

    @Autowired
    public void setService(DispensingBladeService service) {
        this.service = service;
    }
}
