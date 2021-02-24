package ua.notky.cartridge.consumables.web.controllers.parts.primarychargeshaft;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ua.notky.cartridge.consumables.model.parts.PrimaryChargeShaft;
import ua.notky.cartridge.consumables.util.constant.ConstUrl;

import java.util.List;

@RestController
@RequestMapping(value = ConstUrl.UI_PARTS_PRIMARY_CHARGE_SHAFT, produces = MediaType.APPLICATION_JSON_VALUE)
public class PrimaryChargeShaftUIController extends AbstractPrimaryChargeShaftController {
    @Override
    @GetMapping
    List<PrimaryChargeShaft> getAll() {
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
    PrimaryChargeShaft get(@PathVariable int id) {
        return super.get(id);
    }

    @PostMapping()
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    void createOrUpdate(@Validated @RequestBody PrimaryChargeShaft shaft) {
        if(shaft.isNew()) {
            super.create(shaft);
        } else {
            super.update(shaft);
        }
    }
}
