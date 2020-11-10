package ua.notky.cartridge.consumables.service.model;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ua.notky.cartridge.consumables.model.Cartridge;
import ua.notky.cartridge.consumables.service.AbstractTestService;
import ua.notky.cartridge.consumables.service.model.cartridge.CartridgeService;
import ua.notky.cartridge.consumables.util.exception.IllegalEntityException;
import ua.notky.cartridge.consumables.util.exception.NotFoundDataException;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static ua.notky.cartridge.consumables.tools.data.model.CartridgeTool.*;
import static ua.notky.cartridge.consumables.tools.data.model.parts.CleaningBladeTool.CLEANING_BLADE_2;
import static ua.notky.cartridge.consumables.tools.data.model.parts.DispensingBladeTool.DISPENSING_BLADE_2;
import static ua.notky.cartridge.consumables.tools.data.model.parts.DrumTool.DRUM_2;
import static ua.notky.cartridge.consumables.tools.data.model.parts.MagneticShaftTool.MAGNETIC_SHAFT_2;
import static ua.notky.cartridge.consumables.tools.data.model.parts.PrimaryChargeShaftTool.PRIMARY_CHARGE_SHAFT_2;
import static ua.notky.cartridge.consumables.tools.data.model.parts.TonerTool.TONER_2;

public class CartridgeServiceTest extends AbstractTestService {
    @Autowired
    CartridgeService service;

    @Test
    @Transactional
    void create() {
        Cartridge newCartridge = getNew();

        Cartridge result = service.create(newCartridge);
        assertEquals(newCartridge, result);

        int newCartridgeId = result.getId();
        assertEquals(service.get(newCartridgeId), newCartridge);
    }

    @Test
    void createIllegalArgument(){
        assertThrows(IllegalArgumentException.class, () -> service.create(null));
    }

    @Test
    void createIllegalEntity(){
        assertThrows(IllegalEntityException.class, () -> service.create(getUpdated(CARTRIDGE_2)));
    }

    @Test
    void update() {
        Cartridge updated = getUpdated(service.getWithAllParts(ID_CARTRIDGE_2));

        assertEquals(service.update(updated), updated);
        assertEquals(service.get(updated.getId()), updated);
    }

    @Test
    @Transactional
    void updateIllegalArgument(){
        assertThrows(IllegalArgumentException.class, () -> service.update(null));
    }

    @Test
    @Transactional
    void updateIllegalEntity(){
        assertThrows(IllegalEntityException.class, () -> service.update(getNew()));
    }

    @Test
    void get() {
        assertEquals(service.get(ID_CARTRIDGE_2), CARTRIDGE_2);
        assertNotEquals(service.get(ID_CARTRIDGE_2), CARTRIDGE_3);
    }

    @Test
    void getWithAllParts(){
        Cartridge cartridge = service.getWithAllParts(ID_CARTRIDGE_2);
        assertEquals(cartridge, CARTRIDGE_2);
        assertEquals(cartridge.getToner(), TONER_2);
        assertEquals(cartridge.getDrum(), DRUM_2);
        assertEquals(cartridge.getMagneticShaft(), MAGNETIC_SHAFT_2);
        assertEquals(cartridge.getPrimaryChargeShaft(), PRIMARY_CHARGE_SHAFT_2);
        assertEquals(cartridge.getCleaningBlade(), CLEANING_BLADE_2);
        assertEquals(cartridge.getDispensingBlade(), DISPENSING_BLADE_2);
    }

    @Test
    void getNotFound(){
        assertThrows(NotFoundDataException.class, () -> service.get(INVALID_ID));
    }

    @Test
    @Transactional
    void delete() {
        service.delete(ID_CARTRIDGE_2);
        assertIterableEquals(service.getAll(),
                Arrays.asList(CARTRIDGE_1, CARTRIDGE_3, CARTRIDGE_4, CARTRIDGE_5));

    }

    @Test
    @Transactional
    void deleteNotFound(){
        assertThrows(NotFoundDataException.class, () -> service.delete(INVALID_ID));
    }

    @Test
    void getAll() {
        assertIterableEquals(service.getAll(), CARTRIDGES);
    }

}
