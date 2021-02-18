package ua.notky.cartridge.consumables.repository.model;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ua.notky.cartridge.consumables.model.AbstractBaseEntity;
import ua.notky.cartridge.consumables.model.Cartridge;
import ua.notky.cartridge.consumables.repository.AbstractTestRepository;
import ua.notky.cartridge.consumables.repository.cartridge.CartridgeRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static ua.notky.cartridge.consumables.tools.data.model.CartridgeTool.*;
import static ua.notky.cartridge.consumables.tools.data.model.DepartmentTool.DEPARTMENT_2;
import static ua.notky.cartridge.consumables.tools.data.model.parts.CleaningBladeTool.CLEANING_BLADES;
import static ua.notky.cartridge.consumables.tools.data.model.parts.CleaningBladeTool.CLEANING_BLADE_2;
import static ua.notky.cartridge.consumables.tools.data.model.parts.DispensingBladeTool.DISPENSING_BLADES;
import static ua.notky.cartridge.consumables.tools.data.model.parts.DispensingBladeTool.DISPENSING_BLADE_2;
import static ua.notky.cartridge.consumables.tools.data.model.parts.DrumTool.DRUMS;
import static ua.notky.cartridge.consumables.tools.data.model.parts.DrumTool.DRUM_2;
import static ua.notky.cartridge.consumables.tools.data.model.parts.MagneticShaftTool.MAGNETIC_SHAFTS;
import static ua.notky.cartridge.consumables.tools.data.model.parts.MagneticShaftTool.MAGNETIC_SHAFT_2;
import static ua.notky.cartridge.consumables.tools.data.model.parts.PrimaryChargeShaftTool.PRIMARY_CHARGE_SHAFTS;
import static ua.notky.cartridge.consumables.tools.data.model.parts.PrimaryChargeShaftTool.PRIMARY_CHARGE_SHAFT_2;
import static ua.notky.cartridge.consumables.tools.data.model.parts.TonerTool.TONERS;
import static ua.notky.cartridge.consumables.tools.data.model.parts.TonerTool.TONER_2;

public class CartridgeRepositoryTest extends AbstractTestRepository {
    @Autowired
    private CartridgeRepository repository;

    @Test
    @Transactional
    void save() {
        Cartridge newCartridge = getNew();

        Cartridge result = repository.save(newCartridge);
        assertEquals(newCartridge, result);

        int newCartridgeId = result.getId();
        assertEquals(repository.getById(newCartridgeId), newCartridge);
    }

    @Test
    void getById() {
        assertEquals(repository.getById(ID_CARTRIDGE_2), CARTRIDGE_2);
        assertNotEquals(repository.getById(ID_CARTRIDGE_2), CARTRIDGE_3);
        assertNull(repository.getById(INVALID_ID));
    }

    @Test
    void getWithAllParts(){
        Cartridge cartridge = repository.getWithAllParts(ID_CARTRIDGE_2);
        assertEquals(cartridge, CARTRIDGE_2);
        assertEquals(cartridge.getToner(), TONER_2);
        assertEquals(cartridge.getDrum(), DRUM_2);
        assertEquals(cartridge.getMagneticShaft(), MAGNETIC_SHAFT_2);
        assertEquals(cartridge.getPrimaryChargeShaft(), PRIMARY_CHARGE_SHAFT_2);
        assertEquals(cartridge.getCleaningBlade(), CLEANING_BLADE_2);
        assertEquals(cartridge.getDispensingBlade(), DISPENSING_BLADE_2);
    }

    @Test
    @Transactional
    void delete() {
        assertTrue(repository.delete(ID_CARTRIDGE_5));
        assertFalse(repository.delete(INVALID_ID));
        assertNull(repository.getById(ID_CARTRIDGE_5));
        assertIterableEquals(repository.getAll(),
                Arrays.asList(CARTRIDGE_1, CARTRIDGE_2, CARTRIDGE_3, CARTRIDGE_4));
    }

    @Test
    void getAll() {
        assertIterableEquals(repository.getAll(), CARTRIDGES);
    }

    @Test
    void getAllWithAllParts() {
        List<Cartridge> cartridges = repository.getAllWithAllParts();

        cartridges.sort(Comparator.comparingInt(AbstractBaseEntity::getId));

        assertIterableEquals(cartridges, CARTRIDGES);

        for(int i = 0; i < 5; i++){
            assertEquals(cartridges.get(i), CARTRIDGES.get(i));

            int k = i;
            if(i == 4){
                k = 0;
            }

            assertEquals(cartridges.get(i).getToner(), TONERS.get(k));
            assertEquals(cartridges.get(i).getDrum(), DRUMS.get(k));
            assertEquals(cartridges.get(i).getMagneticShaft(), MAGNETIC_SHAFTS.get(k));
            assertEquals(cartridges.get(i).getPrimaryChargeShaft(), PRIMARY_CHARGE_SHAFTS.get(k));
            assertEquals(cartridges.get(i).getCleaningBlade(), CLEANING_BLADES.get(k));
            assertEquals(cartridges.get(i).getDispensingBlade(), DISPENSING_BLADES.get(k));
        }
    }
}
