package ua.notky.cartridge.consumables.web.controllers.parts.toner;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.notky.cartridge.consumables.model.parts.Toner;

import java.util.List;

@RestController
@RequestMapping(value = TonerUIController.UI_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class TonerUIController extends AbstractTonerController {
    static final String UI_URL = "/ui/parts/toner";


    @Override
    @GetMapping(value = "/")
    List<Toner> getAll() {
        return super.getAll();
    }
}
