package ua.notky.cartridge.consumables.tools.data.model;

import ua.notky.cartridge.consumables.model.Cartridge;
import ua.notky.cartridge.consumables.tools.data.AbstractModelTool;

import java.util.List;

import static ua.notky.cartridge.consumables.tools.data.model.parts.CleaningBladeTool.CLEANING_BLADE_2;
import static ua.notky.cartridge.consumables.tools.data.model.parts.DispensingBladeTool.DISPENSING_BLADE_2;
import static ua.notky.cartridge.consumables.tools.data.model.parts.DrumTool.DRUM_2;
import static ua.notky.cartridge.consumables.tools.data.model.parts.MagneticShaftTool.MAGNETIC_SHAFT_2;
import static ua.notky.cartridge.consumables.tools.data.model.parts.PrimaryChargeShaftTool.PRIMARY_CHARGE_SHAFT_2;
import static ua.notky.cartridge.consumables.tools.data.model.parts.TonerTool.TONER_2;

public class CartridgeTool extends AbstractModelTool {
    public static final int ID_CARTRIDGE_2 = 2;
    public static final int ID_CARTRIDGE_5 = 5;

    public static final Cartridge CARTRIDGE_1 = new Cartridge(1, "cartridge_1", 1);
    public static final Cartridge CARTRIDGE_2 = new Cartridge(ID_CARTRIDGE_2, "cartridge_2", 2);
    public static final Cartridge CARTRIDGE_3 = new Cartridge(3, "cartridge_3", 3);
    public static final Cartridge CARTRIDGE_4 = new Cartridge(4, "cartridge_4", 4);
    public static final Cartridge CARTRIDGE_5 = new Cartridge(ID_CARTRIDGE_5, "cartridge_5", 1);

    public static final List<Cartridge> CARTRIDGES = List.of(CARTRIDGE_1, CARTRIDGE_2,
            CARTRIDGE_3, CARTRIDGE_4, CARTRIDGE_5);

    public static Cartridge getNew(){
        Cartridge cartridge = new Cartridge("New Cartridge", 5);
        cartridge.setToner(TONER_2);
        cartridge.setDrum(DRUM_2);
        cartridge.setMagneticShaft(MAGNETIC_SHAFT_2);
        cartridge.setPrimaryChargeShaft(PRIMARY_CHARGE_SHAFT_2);
        cartridge.setCleaningBlade(CLEANING_BLADE_2);
        cartridge.setDispensingBlade(DISPENSING_BLADE_2);
        return cartridge;
    }

    public static Cartridge getCopy(Cartridge value){
        Cartridge cartridge = new Cartridge(value.getId(), value.getName(), value.getCoefToner());
        cartridge.setToner(value.getToner());
        cartridge.setDrum(value.getDrum());
        cartridge.setMagneticShaft(value.getMagneticShaft());
        cartridge.setPrimaryChargeShaft(value.getPrimaryChargeShaft());
        cartridge.setCleaningBlade(value.getCleaningBlade());
        cartridge.setDispensingBlade(value.getDispensingBlade());
        return cartridge;
    }

    public static Cartridge getUpdated(Cartridge value){
        Cartridge update = getCopy(value);
        update.setName("Updated Cartridge");
        update.setCoefToner(10);
        return update;
    }
}
