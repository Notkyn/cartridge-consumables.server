package ua.notky.cartridge.consumables.web.controllers.refillcartridge;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ua.notky.cartridge.consumables.model.RefillCartridge;
import ua.notky.cartridge.consumables.util.constant.ConstUrl;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = ConstUrl.UI_REFILL_CARTRIDGE, produces = MediaType.APPLICATION_JSON_VALUE)
public class RefillCartridgeController extends AbstractRefillCartridgeController {
    @Override
    @GetMapping
    List<RefillCartridge> getAll() {
        return super.getAll();
    }

    @Override
    @GetMapping(ConstUrl.DATES)
    List<LocalDate> getAllDates() {
        return super.getAllDates();
    }

    @Override
    @GetMapping(ConstUrl.FILTER)
    List<RefillCartridge> getAllByDate(@RequestParam(required = false) LocalDate date){
        System.out.println("LocalDate: " + date);
        return super.getAllByDate(date);
    }

    @Override
    @DeleteMapping(ConstUrl.ID)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    void delete(@PathVariable int id) {
        super.delete(id);
    }

    @Override
    @GetMapping(ConstUrl.ID)
    RefillCartridge get(@PathVariable int id) {
        return super.get(id);
    }

    @PostMapping()
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    void createOrUpdate(@Validated @RequestBody RefillCartridge refillCartridge) {
        if(refillCartridge.isNew()) {
            super.create(refillCartridge);
        } else {
            super.update(refillCartridge);
        }
    }
}
