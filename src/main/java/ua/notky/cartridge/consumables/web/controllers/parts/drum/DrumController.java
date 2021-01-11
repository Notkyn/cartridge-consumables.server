package ua.notky.cartridge.consumables.web.controllers.parts.drum;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ua.notky.cartridge.consumables.model.parts.Drum;
import ua.notky.cartridge.consumables.util.constant.ConstUrl;

import java.util.List;

@RestController
@RequestMapping(value = ConstUrl.UI_DRUM, produces = MediaType.APPLICATION_JSON_VALUE)
public class DrumController extends AbstractDrumController {
    @Override
    @GetMapping
    List<Drum> getAll() {
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
    Drum get(@PathVariable int id) {
        return super.get(id);
    }

    @PostMapping()
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    void createOrUpdate(@Validated @RequestBody Drum drum) {
        if(drum.isNew()) {
            super.create(drum);
        } else {
            super.update(drum);
        }
    }
}
