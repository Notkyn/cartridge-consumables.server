package ua.notky.cartridge.consumables.service.model.parts;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ua.notky.cartridge.consumables.model.parts.PrimaryChargeShaft;
import ua.notky.cartridge.consumables.service.AbstractTestService;
import ua.notky.cartridge.consumables.service.model.parts.primarychargeshaft.PrimaryChargeShaftService;
import ua.notky.cartridge.consumables.util.exception.HasDependencyException;
import ua.notky.cartridge.consumables.util.exception.IllegalEntityException;
import ua.notky.cartridge.consumables.util.exception.NotFoundDataException;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static ua.notky.cartridge.consumables.tools.data.AbstractModelTool.INVALID_ID;
import static ua.notky.cartridge.consumables.tools.data.model.CartridgeTool.CARTRIDGE_2;
import static ua.notky.cartridge.consumables.tools.data.model.parts.PrimaryChargeShaftTool.*;

public class PrimaryCheargeShaftServiceTest extends AbstractTestService {
    @Autowired
    private PrimaryChargeShaftService service;

    @Test
    @Transactional
    void create() {
        PrimaryChargeShaft newPrimaryChargeShaft = getNew();

        PrimaryChargeShaft result = service.create(newPrimaryChargeShaft);
        assertEquals(newPrimaryChargeShaft, result);

        int newPrimaryChargeShaftId = result.getId();
        assertEquals(service.get(newPrimaryChargeShaftId), newPrimaryChargeShaft);
    }

    @Test
    void createIllegalArgument(){
        assertThrows(IllegalArgumentException.class, () -> service.create(null));
    }

    @Test
    void createIllegalEntity(){
        assertThrows(IllegalEntityException.class, () -> service.create(getUpdated(PRIMARY_CHARGE_SHAFT_2)));
    }

    @Test
    void update() {
        PrimaryChargeShaft updated = getUpdated(PRIMARY_CHARGE_SHAFT_2);

        assertEquals(service.update(updated), updated);
        assertEquals(service.get(updated.getId()), updated);
    }

    @Test
    @Transactional
    void updateIllegalArgument(){
        assertThrows(IllegalArgumentException.class, () -> service.create(null));
    }

    @Test
    @Transactional
    void updateIllegalEntity(){
        assertThrows(IllegalEntityException.class, () -> service.update(getNew()));
    }

    @Test
    void get() {
        assertEquals(service.get(ID_PRIMARY_CHARGE_SHAFT_2), PRIMARY_CHARGE_SHAFT_2);
        assertNotEquals(service.get(ID_PRIMARY_CHARGE_SHAFT_2), PRIMARY_CHARGE_SHAFT_3);
    }

    @Test
    void getWithCartridges(){
        assertIterableEquals(service.getWithCartridges(ID_PRIMARY_CHARGE_SHAFT_2).getCartridges(),
                Collections.singletonList(CARTRIDGE_2));
    }

    @Test
    void getNotFound(){
        assertThrows(NotFoundDataException.class, () -> service.get(INVALID_ID));
    }

    @Test
    @Transactional
    void delete() {
        service.delete(ID_PRIMARY_CHARGE_SHAFT_5);
        assertIterableEquals(service.getAll(),
                Arrays.asList(PRIMARY_CHARGE_SHAFT_1, PRIMARY_CHARGE_SHAFT_2, PRIMARY_CHARGE_SHAFT_3, PRIMARY_CHARGE_SHAFT_4));

    }

    @Test
    @Transactional
    void deleteNotFound(){
        assertThrows(NotFoundDataException.class, () -> service.delete(INVALID_ID));
    }

    @Test
    @Transactional
    void deleteHasDependency() {
        assertThrows(HasDependencyException.class, () -> service.delete(ID_PRIMARY_CHARGE_SHAFT_2));
    }

    @Test
    void getAll() {
        assertIterableEquals(service.getAll(), PRIMARY_CHARGE_SHAFTS);
    }

}
