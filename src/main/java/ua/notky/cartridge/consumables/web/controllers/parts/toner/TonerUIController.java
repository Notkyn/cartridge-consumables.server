package ua.notky.cartridge.consumables.web.controllers.parts.toner;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ua.notky.cartridge.consumables.util.constant.ConstUrl;
import ua.notky.cartridge.consumables.model.parts.Toner;

import java.util.List;

@RestController
@RequestMapping(value = ConstUrl.UI_PARTS_TONER, produces = MediaType.APPLICATION_JSON_VALUE)
public class TonerUIController extends AbstractTonerController {

    @Override
    @GetMapping
    List<Toner> getAll() {
        return super.getAll();
    }

    @Override
    @DeleteMapping(ConstUrl.ID)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    void delete(@PathVariable int id) {
        super.delete(id);
    }

    @Override
    @GetMapping(ConstUrl.ID)
    Toner get(@PathVariable int id) {
        return super.get(id);
    }

    @PostMapping()
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    void createOrUpdate(@Validated @RequestBody Toner toner) {
        if(toner.isNew()) {
            super.create(toner);
        } else {
            super.update(toner);
        }
    }
}