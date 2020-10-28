package ua.notky.cartridge.consumables.tools.data.model.parts;

import ua.notky.cartridge.consumables.model.parts.DispensingBlade;
import ua.notky.cartridge.consumables.tools.data.AbstractModelTool;

import java.util.List;

public class DispensingBladeTool extends AbstractModelTool {
    public static final int ID_DISPENSING_BLADE_2 = 2;
    public static final int ID_DISPENSING_BLADE_5 = 5;

    public static DispensingBlade DISPENSING_BLADE_1 = new DispensingBlade(1, "dispensing_blade_1");
    public static DispensingBlade DISPENSING_BLADE_2 = new DispensingBlade(ID_DISPENSING_BLADE_2, "dispensing_blade_2");
    public static DispensingBlade DISPENSING_BLADE_3 = new DispensingBlade(3, "dispensing_blade_3");
    public static DispensingBlade DISPENSING_BLADE_4 = new DispensingBlade(4, "dispensing_blade_4");
    public static DispensingBlade DISPENSING_BLADE_5 = new DispensingBlade(ID_DISPENSING_BLADE_5, "dispensing_blade_5");

    public static List<DispensingBlade> DISPENSING_BLADES = List.of(DISPENSING_BLADE_1,
            DISPENSING_BLADE_2, DISPENSING_BLADE_3, DISPENSING_BLADE_4, DISPENSING_BLADE_5);

    public static DispensingBlade getNew(){
        return new DispensingBlade("New Dispensing Blade");
    }

    public static DispensingBlade getCopy(DispensingBlade value){
        return new DispensingBlade(value.getId(), value.getName());
    }

    public static DispensingBlade getUpdated(DispensingBlade value){
        DispensingBlade update = getCopy(value);
        update.setName("Updated Dispensing Blade");
        return update;
    }
}
