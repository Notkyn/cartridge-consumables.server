package ua.notky.cartridge.consumables.tools.data.model;

import ua.notky.cartridge.consumables.model.Cartridge;
import ua.notky.cartridge.consumables.tools.data.AbstractModelTool;

import java.util.List;

import static ua.notky.cartridge.consumables.tools.data.model.parts.CleaningBladeTool.CLEANING_BLADE_2;

public class CartridgeTool extends AbstractModelTool {
    public static final int ID_CARTRIDGE_2 = 2;

    public static final Cartridge CARTRIDGE_1 = new Cartridge(1, "cartridge_1");
    public static final Cartridge CARTRIDGE_2 = new Cartridge(ID_CARTRIDGE_2, "cartridge_2");
    public static final Cartridge CARTRIDGE_3 = new Cartridge(3, "cartridge_3");
    public static final Cartridge CARTRIDGE_4 = new Cartridge(4, "cartridge_4");
    public static final Cartridge CARTRIDGE_5 = new Cartridge(5, "cartridge_5");

    public static final List<Cartridge> CARTRIDGES = List.of(CARTRIDGE_1, CARTRIDGE_2,
            CARTRIDGE_3, CARTRIDGE_4, CARTRIDGE_5);

    public static Cartridge getNew(){
        Cartridge cartridge = new Cartridge("New Cartridge");
        cartridge.setCleaningBlade(CLEANING_BLADE_2);
        return cartridge;
    }

    public static Cartridge getCopy(Cartridge value){
        return new Cartridge(
                value.getId(),
                value.getName());
    }

    public static Cartridge getUpdated(Cartridge value){
        Cartridge update = getCopy(value);
        update.setName("Updated Cartridge");
        return update;
    }
}
