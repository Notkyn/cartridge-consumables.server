package ua.notky.cartridge.consumables.web.controllers.parts.magneticshaft;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import ua.notky.cartridge.consumables.model.parts.MagneticShaft;
import ua.notky.cartridge.consumables.service.model.parts.magneticshaft.MagneticShaftService;

import java.util.List;

import static ua.notky.cartridge.consumables.util.ValidationUtil.checkNewBean;

@Slf4j
public class AbstracrtMagneticShaftController {

    private MagneticShaftService service;

    List<MagneticShaft> getAll(){
        log.info("Get all");
        return service.getAll();
    }

    void delete(int id){
        log.info("Delete id={}", id);
        service.delete(id);
    }

    MagneticShaft get(int id){
        log.info("Get id={}", id);
        return service.get(id);
    }

    void create(MagneticShaft shaft){
        log.info("Create {}", shaft);
        checkNewBean(shaft);
        service.create(shaft);
    }

    void update(MagneticShaft shaft){
        log.info("Update id={}, {}",shaft.getId(), shaft);
        service.update(shaft);
    }

    @Autowired
    public void setService(MagneticShaftService service) {
        this.service = service;
    }
}
