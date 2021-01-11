package ua.notky.cartridge.consumables.web.controllers.parts.drum;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import ua.notky.cartridge.consumables.model.parts.Drum;
import ua.notky.cartridge.consumables.service.model.parts.drum.DrumService;

import java.util.List;

import static ua.notky.cartridge.consumables.util.ValidationUtil.checkNewBean;

@Slf4j
public abstract class AbstractDrumController {
    private DrumService service;

    List<Drum> getAll(){
        log.info("Get all");
        return service.getAll();
    }

    void delete(int id){
        log.info("Delete id={}", id);
        service.delete(id);
    }

    Drum get(int id){
        log.info("Get id={}", id);
        return service.get(id);
    }

    void create(Drum drum){
        log.info("Create {}", drum);
        checkNewBean(drum);
        service.create(drum);
    }

    void update(Drum drum){
        log.info("Update id={}, {}",drum.getId(), drum);
        service.update(drum);
    }

    @Autowired
    public void setService(DrumService service) {
        this.service = service;
    }
}
