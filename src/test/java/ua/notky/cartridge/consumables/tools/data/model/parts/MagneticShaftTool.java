package ua.notky.cartridge.consumables.tools.data.model.parts;

import ua.notky.cartridge.consumables.model.parts.MagneticShaft;
import ua.notky.cartridge.consumables.tools.data.AbstractModelTool;

import java.util.List;

public class MagneticShaftTool extends AbstractModelTool {
    public static final int ID_MAGNETIC_SHAFT_2 = 2;
    public static final int ID_MAGNETIC_SHAFT_5 = 5;

    public static MagneticShaft MAGNETIC_SHAFT_1 = new MagneticShaft(1, "magnetic_shaft_1");
    public static MagneticShaft MAGNETIC_SHAFT_2 = new MagneticShaft(ID_MAGNETIC_SHAFT_2, "magnetic_shaft_2");
    public static MagneticShaft MAGNETIC_SHAFT_3 = new MagneticShaft(3, "magnetic_shaft_3");
    public static MagneticShaft MAGNETIC_SHAFT_4 = new MagneticShaft(4, "magnetic_shaft_4");
    public static MagneticShaft MAGNETIC_SHAFT_5 = new MagneticShaft(ID_MAGNETIC_SHAFT_5, "magnetic_shaft_5");

    public static List<MagneticShaft> MAGNETIC_SHAFTS = List.of(MAGNETIC_SHAFT_1,
            MAGNETIC_SHAFT_2, MAGNETIC_SHAFT_3, MAGNETIC_SHAFT_4, MAGNETIC_SHAFT_5);

    public static MagneticShaft getNew(){
        return new MagneticShaft("New Magnetic Shaft");
    }

    public static MagneticShaft getCopy(MagneticShaft value){
        return new MagneticShaft(value.getId(), value.getName());
    }

    public static MagneticShaft getUpdated(MagneticShaft value){
        MagneticShaft update = getCopy(value);
        update.setName("Updated Magnetic Shaft");
        return update;
    }
}
