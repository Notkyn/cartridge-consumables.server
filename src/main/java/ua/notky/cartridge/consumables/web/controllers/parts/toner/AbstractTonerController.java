package ua.notky.cartridge.consumables.web.controllers.parts.toner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import ua.notky.cartridge.consumables.model.parts.Toner;
import ua.notky.cartridge.consumables.service.model.parts.toner.TonerService;

import java.util.List;

import static ua.notky.cartridge.consumables.util.ValidationUtil.checkNewBean;

@Slf4j
public abstract class AbstractTonerController {
    private TonerService service;

    List<Toner> getAll(){
        log.info("Get all");
        return service.getAll();
    }

    void delete(int id){
        log.info("Delete id={}", id);
        service.delete(id);
    }

    Toner get(int id){
        log.info("Get id={}", id);
        return service.get(id);
    }

    void create(Toner toner){
        log.info("Create {}", toner);
        checkNewBean(toner);
        service.create(toner);
    }

    void update(Toner toner){
        log.info("Update id={}, {}",toner.getId(), toner);
        service.update(toner);
    }

    @Autowired
    public void setService(TonerService service) {
        this.service = service;
    }
}
