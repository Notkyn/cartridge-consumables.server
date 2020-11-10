package ua.notky.cartridge.consumables.tools.data.model.parts;

import ua.notky.cartridge.consumables.model.parts.Toner;
import ua.notky.cartridge.consumables.tools.data.AbstractModelTool;

import java.util.List;

public class TonerTool extends AbstractModelTool {
    public static final int ID_TONER_2 = 2;
    public static final int ID_TONER_5 = 5;

    public static final Toner TONER_1 = new Toner(1, "toner_1");
    public static final Toner TONER_2 = new Toner(ID_TONER_2, "toner_2");
    public static final Toner TONER_3 = new Toner(3, "toner_3");
    public static final Toner TONER_4 = new Toner(4, "toner_4");
    public static final Toner TONER_5 = new Toner(ID_TONER_5, "toner_5");

    public static final List<Toner> TONERS = List.of(TONER_1, TONER_2, TONER_3,
            TONER_4, TONER_5);


    public static Toner getNew(){
        return new Toner("New Toner");
    }

    public static Toner getCopy(Toner value){
        return new Toner(value.getId(), value.getName());
    }

    public static Toner getUpdated(Toner value){
        Toner update = getCopy(value);
        update.setName("Updated Toner");
        return update;
    }
}
