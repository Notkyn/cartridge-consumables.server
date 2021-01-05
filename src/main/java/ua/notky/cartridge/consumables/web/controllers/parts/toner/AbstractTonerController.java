package ua.notky.cartridge.consumables.web.controllers.parts.toner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import ua.notky.cartridge.consumables.model.parts.Toner;
import ua.notky.cartridge.consumables.service.model.parts.toner.TonerService;

import java.util.List;

import static ua.notky.cartridge.consumables.util.ValidationUtil.checkNewBean;

@Slf4j
public class AbstractTonerController {
    private TonerService tonerService;

    List<Toner> getAll(){
        log.info("Get all");
        return tonerService.getAll();
    }

    void delete(int id){
        log.info("Delete id={}", id);
        tonerService.delete(id);
    }

    Toner get(int id){
        log.info("Get id={}", id);
        return tonerService.get(id);
    }

    void create(Toner toner){
        log.info("Create {}", toner);
        checkNewBean(toner);
        tonerService.create(toner);
    }

    void update(Toner toner){
        log.info("Update id={}, {}",toner.getId(), toner);
        tonerService.update(toner);
    }

    @Autowired
    public void setTonerService(TonerService tonerService) {
        this.tonerService = tonerService;
    }
}
