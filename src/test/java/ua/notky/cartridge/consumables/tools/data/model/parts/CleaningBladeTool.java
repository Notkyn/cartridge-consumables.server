package ua.notky.cartridge.consumables.tools.data.model.parts;

import ua.notky.cartridge.consumables.model.parts.CleaningBlade;
import ua.notky.cartridge.consumables.tools.data.AbstractModelTool;

import java.util.List;

public class CleaningBladeTool extends AbstractModelTool {
    public static final int ID_CLEANING_BLADE_2 = 2;
    public static final int ID_CLEANING_BLADE_5 = 5;

    public static final CleaningBlade CLEANING_BLADE_1 = new CleaningBlade(1, "cleaning_blade_1");
    public static final CleaningBlade CLEANING_BLADE_2 = new CleaningBlade(ID_CLEANING_BLADE_2, "cleaning_blade_2");
    public static final CleaningBlade CLEANING_BLADE_3 = new CleaningBlade(3, "cleaning_blade_3");
    public static final CleaningBlade CLEANING_BLADE_4 = new CleaningBlade(4, "cleaning_blade_4");
    public static final CleaningBlade CLEANING_BLADE_5 = new CleaningBlade(ID_CLEANING_BLADE_5, "cleaning_blade_5");

    public static final List<CleaningBlade> CLEANING_BLADES = List.of(
            CLEANING_BLADE_1, CLEANING_BLADE_2, CLEANING_BLADE_3, CLEANING_BLADE_4, CLEANING_BLADE_5);

    public static CleaningBlade getNew(){
        return new CleaningBlade("New Cleaning Blade");
    }

    public static CleaningBlade getCopy(CleaningBlade value){
        return new CleaningBlade(value.getId(), value.getName());
    }

    public static CleaningBlade getUpdated(CleaningBlade value){
        CleaningBlade update = getCopy(value);
        update.setName("Updated Cleaning Blade");
        return update;
    }
}
