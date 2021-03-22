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

    private static final LocalDate DATE_REFILL_1 = LocalDate.of(2015, 5, 1);
    private static final LocalDate DATE_REFILL_2 = LocalDate.of(2015, 5, 2);
    public static final LocalDate DATE_REFILL_3 = LocalDate.of(2015, 5, 3);
    private static final LocalDate DATE_REFILL_4 = LocalDate.of(2015, 5, 4);
    private static final LocalDate DATE_REFILL_5 = LocalDate.of(2015, 5, 5);
    public static final LocalDate INVALID_DATE_REFILL = LocalDate.of(2020, 5, 5);

    public static final RefillCartridge REFILL_CARTRIDGE_1 = new RefillCartridge(1, DATE_REFILL_1,
            false, false, false, false, false);
    public static final RefillCartridge REFILL_CARTRIDGE_2 = new RefillCartridge(ID_REFILL_CARTRIDGE_2, DATE_REFILL_2,
            false, false, false, false, false);
    public static final RefillCartridge REFILL_CARTRIDGE_3 = new RefillCartridge(3, DATE_REFILL_3,
            true, true, true, true, true);
    public static final RefillCartridge REFILL_CARTRIDGE_4 = new RefillCartridge(4, DATE_REFILL_4,
            false, false, false, false, false);
    public static final RefillCartridge REFILL_CARTRIDGE_5 = new RefillCartridge(ID_REFILL_CARTRIDGE_5, DATE_REFILL_5,
            false, false, false, false, false);

    public static final List<RefillCartridge> REFILLS_CARTRIDGES = List.of(
            REFILL_CARTRIDGE_1, REFILL_CARTRIDGE_2, REFILL_CARTRIDGE_3, REFILL_CARTRIDGE_4, REFILL_CARTRIDGE_5);

    public static final List<LocalDate> REFILLS_DATES = List.of(
            DATE_REFILL_1, DATE_REFILL_2, DATE_REFILL_3, DATE_REFILL_4, DATE_REFILL_5);

    public static RefillCartridge getNew(){
        RefillCartridge refillCartridge = new RefillCartridge(LocalDate.of(2020, 10, 10),
                false, false, false, false, false);
        refillCartridge.setDepartment(DEPARTMENT_2);
        return refillCartridge;
    }

    public static RefillCartridge getCopy(RefillCartridge value){
        RefillCartridge refillCartridge = new RefillCartridge(value.getId(),
                value.getDate(),
                value.isUseDrum(),
                value.isUseMagneticShaft(),
                value.isUsePrimaryChargeShaft(),
                value.isUseCleaningBlade(),
                value.isUseDispensingBlade());
        refillCartridge.setDepartment(value.getDepartment());
        return refillCartridge;
    }

    public static RefillCartridge getUpdated(RefillCartridge value){
        RefillCartridge refillCartridge = getCopy(value);
        refillCartridge.setDate(LocalDate.of(2020, 12, 12));
        refillCartridge.setUseDrum(true);
        refillCartridge.setUseMagneticShaft(false);
        refillCartridge.setUsePrimaryChargeShaft(true);
        refillCartridge.setUseCleaningBlade(false);
        refillCartridge.setUseDispensingBlade(true);
        return refillCartridge;
    }
}
