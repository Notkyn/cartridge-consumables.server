package ua.notky.cartridge.consumables.web.controllers.parts.cleaningblade;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import ua.notky.cartridge.consumables.model.parts.CleaningBlade;
import ua.notky.cartridge.consumables.service.model.parts.cleaningblade.CleaningBladeService;

import java.util.List;

import static ua.notky.cartridge.consumables.util.ValidationUtil.checkNewBean;

@Slf4j
public class AbstractCleaningBladeController {

    private CleaningBladeService service;

    List<CleaningBlade> getAll(){
        log.info("Get all");
        return service.getAll();
    }

    void delete(int id){
        log.info("Delete id={}", id);
        service.delete(id);
    }

    CleaningBlade get(int id){
        log.info("Get id={}", id);
        return service.get(id);
    }

    void create(CleaningBlade blade){
        log.info("Create {}", blade);
        checkNewBean(blade);
        service.create(blade);
    }

    void update(CleaningBlade blade){
        log.info("Update id={}, {}",blade.getId(), blade);
        service.update(blade);
    }

    @Autowired
    public void setService(CleaningBladeService service) {
        this.service = service;
    }
}
