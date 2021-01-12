package ua.notky.cartridge.consumables.web.controllers.parts.dispensingblade;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ua.notky.cartridge.consumables.model.parts.DispensingBlade;
import ua.notky.cartridge.consumables.util.constant.ConstUrl;

import java.util.List;

@RestController
@RequestMapping(value = ConstUrl.UI_DISPENSING_BLADE, produces = MediaType.APPLICATION_JSON_VALUE)
public class DispensingBladeController extends AbstractDispensingBladeController {
    @Override
    @GetMapping
    List<DispensingBlade> getAll() {
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
    DispensingBlade get(@PathVariable int id) {
        return super.get(id);
    }

    @PostMapping()
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    void createOrUpdate(@Validated @RequestBody DispensingBlade blade) {
        if(blade.isNew()) {
            super.create(blade);
        } else {
            super.update(blade);
        }
    }
}
