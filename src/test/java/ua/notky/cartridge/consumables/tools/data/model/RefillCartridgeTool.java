package ua.notky.cartridge.consumables.tools.data.model;

import ua.notky.cartridge.consumables.model.RefillCartridge;
import ua.notky.cartridge.consumables.tools.data.AbstractModelTool;

import java.time.LocalDate;
import java.util.List;

import static ua.notky.cartridge.consumables.tools.data.model.DepartmentTool.DEPARTMENT_2;
import static ua.notky.cartridge.consumables.tools.data.model.DepartmentTool.DEPARTMENT_3;

public class RefillCartridgeTool extends AbstractModelTool {
    public static final int ID_REFILL_CARTRIDGE_2 = 2;
    public static final int ID_REFILL_CARTRIDGE_5 = 5;

    public static final RefillCartridge REFILL_CARTRIDGE_1 = new RefillCartridge(1,
            LocalDate.of(2015, 05, 01));
    public static final RefillCartridge REFILL_CARTRIDGE_2 = new RefillCartridge(ID_REFILL_CARTRIDGE_2,
            LocalDate.of(2015, 05, 02));
    public static final RefillCartridge REFILL_CARTRIDGE_3 = new RefillCartridge(3,
            LocalDate.of(2015, 05, 03));
    public static final RefillCartridge REFILL_CARTRIDGE_4 = new RefillCartridge(4,
            LocalDate.of(2015, 05, 04));
    public static final RefillCartridge REFILL_CARTRIDGE_5 = new RefillCartridge(ID_REFILL_CARTRIDGE_5,
            LocalDate.of(2015, 05, 05));

    public static final List<RefillCartridge> REFILLS_CARTRIDGES = List.of(REFILL_CARTRIDGE_1,
            REFILL_CARTRIDGE_2, REFILL_CARTRIDGE_3, REFILL_CARTRIDGE_4, REFILL_CARTRIDGE_5);

    public static RefillCartridge getNew(){
        RefillCartridge refillCartridge = new RefillCartridge(LocalDate.of(2020, 10, 10));
        refillCartridge.setDepartments(List.of(DEPARTMENT_2, DEPARTMENT_3));
        return refillCartridge;
    }

    public static RefillCartridge getCopy(RefillCartridge value){
        RefillCartridge refillCartridge = new RefillCartridge(value.getId(), value.getDate());
        refillCartridge.setDepartments(value.getDepartments());
        return refillCartridge;
    }

    public static RefillCartridge getUpdated(RefillCartridge value){
        RefillCartridge refillCartridge = getCopy(value);
        refillCartridge.setDate(LocalDate.of(2020, 12, 12));
        return refillCartridge;
    }
}
