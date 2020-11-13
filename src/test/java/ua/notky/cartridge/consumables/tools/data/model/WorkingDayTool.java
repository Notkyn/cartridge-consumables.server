package ua.notky.cartridge.consumables.tools.data.model;

import ua.notky.cartridge.consumables.model.WorkingDay;
import ua.notky.cartridge.consumables.tools.data.AbstractModelTool;

import java.time.LocalDate;
import java.util.List;

import static ua.notky.cartridge.consumables.tools.data.model.DepartmentTool.DEPARTMENT_2;
import static ua.notky.cartridge.consumables.tools.data.model.DepartmentTool.DEPARTMENT_3;

public class WorkingDayTool extends AbstractModelTool {
    public static final int ID_WORKING_DAY_2 = 2;
    public static final int ID_WORKING_DAY_5 = 5;

    public static final WorkingDay WORKING_DAY_1 = new WorkingDay(1,
            LocalDate.of(2015, 05, 01));
    public static final WorkingDay WORKING_DAY_2 = new WorkingDay(ID_WORKING_DAY_2,
            LocalDate.of(2015, 05, 02));
    public static final WorkingDay WORKING_DAY_3 = new WorkingDay(3,
            LocalDate.of(2015, 05, 03));
    public static final WorkingDay WORKING_DAY_4 = new WorkingDay(4,
            LocalDate.of(2015, 05, 04));
    public static final WorkingDay WORKING_DAY_5 = new WorkingDay(ID_WORKING_DAY_5,
            LocalDate.of(2015, 05, 05));

    public static final List<WorkingDay> WORKING_DAYS = List.of(WORKING_DAY_1,
            WORKING_DAY_2, WORKING_DAY_3, WORKING_DAY_4, WORKING_DAY_5);

    public static WorkingDay getNew(){
        WorkingDay workingDay = new WorkingDay(LocalDate.of(2020, 10, 10));
        workingDay.setDepartments(List.of(DEPARTMENT_2, DEPARTMENT_3));
        return workingDay;
    }

    public static WorkingDay getCopy(WorkingDay value){
        WorkingDay workingDay = new WorkingDay(value.getId(), value.getDate());
        workingDay.setDepartments(value.getDepartments());
        return workingDay;
    }

    public static WorkingDay getUpdated(WorkingDay value){
        WorkingDay workingDay = getCopy(value);
        workingDay.setDate(LocalDate.of(2020, 12, 12));
        return workingDay;
    }
}
