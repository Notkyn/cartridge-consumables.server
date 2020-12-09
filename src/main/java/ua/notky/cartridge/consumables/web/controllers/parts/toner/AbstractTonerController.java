package ua.notky.cartridge.consumables.web.controllers.parts.toner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import ua.notky.cartridge.consumables.model.parts.Toner;
import ua.notky.cartridge.consumables.service.model.parts.toner.TonerService;

import java.util.List;

@Slf4j
public class AbstractTonerController {
    private TonerService tonerService;

    List<Toner> getAll(){
        log.info("Get all Toners");
        return tonerService.getAll();
    }

    @Autowired
    public void setTonerService(TonerService tonerService) {
        this.tonerService = tonerService;
    }
}
