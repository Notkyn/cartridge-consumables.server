package ua.notky.cartridge.consumables.tools.data.model.parts;

import ua.notky.cartridge.consumables.model.parts.PrimaryChargeShaft;
import ua.notky.cartridge.consumables.tools.data.AbstractModelTool;

import java.util.List;

public class PrimaryChargeShaftTool extends AbstractModelTool {
    public static final int ID_PRIMARY_CHARGE_SHAFT_2 = 2;
    public static final int ID_PRIMARY_CHARGE_SHAFT_5 = 5;

    public static PrimaryChargeShaft PRIMARY_CHARGE_SHAFT_1 = new PrimaryChargeShaft(1,
            "primary_charge_shaft_1");
    public static PrimaryChargeShaft PRIMARY_CHARGE_SHAFT_2 = new PrimaryChargeShaft(ID_PRIMARY_CHARGE_SHAFT_2,
            "primary_charge_shaft_2");
    public static PrimaryChargeShaft PRIMARY_CHARGE_SHAFT_3 = new PrimaryChargeShaft(3,
            "primary_charge_shaft_3");
    public static PrimaryChargeShaft PRIMARY_CHARGE_SHAFT_4 = new PrimaryChargeShaft(4,
            "primary_charge_shaft_4");
    public static PrimaryChargeShaft PRIMARY_CHARGE_SHAFT_5 = new PrimaryChargeShaft(ID_PRIMARY_CHARGE_SHAFT_5,
            "primary_charge_shaft_5");

    public static List<PrimaryChargeShaft> PRIMARY_CHARGE_SHAFTS = List.of(PRIMARY_CHARGE_SHAFT_1,
            PRIMARY_CHARGE_SHAFT_2, PRIMARY_CHARGE_SHAFT_3, PRIMARY_CHARGE_SHAFT_4, PRIMARY_CHARGE_SHAFT_5);

    public static PrimaryChargeShaft getNew(){
        return new PrimaryChargeShaft("New Primary Charge Shaft");
    }

    public static PrimaryChargeShaft getCopy(PrimaryChargeShaft value){
        return new PrimaryChargeShaft(value.getId(), value.getName());
    }

    public static PrimaryChargeShaft getUpdated(PrimaryChargeShaft value){
        PrimaryChargeShaft update = getCopy(value);
        update.setName("Updated Primary Charge Shaft");
        return update;
    }

}
