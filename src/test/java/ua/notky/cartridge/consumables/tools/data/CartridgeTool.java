package ua.notky.cartridge.consumables.tools.data;

import ua.notky.cartridge.consumables.model.Cartridge;

import java.util.List;

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
        return new Cartridge("New Cartridge");
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
