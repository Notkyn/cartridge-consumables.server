package ua.notky.cartridge.consumables.web.controllers.cartridge;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ua.notky.cartridge.consumables.model.Cartridge;
import ua.notky.cartridge.consumables.util.constant.ConstUrl;

import java.util.List;

@RestController
@RequestMapping(value = ConstUrl.UI_CARTRIDGE, produces = MediaType.APPLICATION_JSON_VALUE)
public class CartridgeController extends AbstractCartridgeController{

    @Override
    @GetMapping
    List<Cartridge> getAll() {
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
    Cartridge get(@PathVariable int id) {
        return super.get(id);
    }

    @PostMapping()
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    void createOrUpdate(@Validated @RequestBody Cartridge cartridge) {
        if(cartridge.isNew()) {
            super.create(cartridge);
        } else {
            super.update(cartridge);
        }
    }
}
