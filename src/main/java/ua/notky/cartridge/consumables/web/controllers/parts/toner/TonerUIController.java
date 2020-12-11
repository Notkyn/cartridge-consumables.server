package ua.notky.cartridge.consumables.web.controllers.parts.toner;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ua.notky.cartridge.consumables.model.parts.Toner;

import java.util.List;

@RestController
@RequestMapping(value = TonerUIController.UI_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class TonerUIController extends AbstractTonerController {
    static final String UI_URL = "/ui/parts/toner";


    @Override
    @GetMapping
    List<Toner> getAll() {
        return super.getAll();
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    void delete(@PathVariable int id) {
        super.delete(id);
    }
}
