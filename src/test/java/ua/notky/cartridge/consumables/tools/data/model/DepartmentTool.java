package ua.notky.cartridge.consumables.tools.data.model;

import ua.notky.cartridge.consumables.model.Department;
import ua.notky.cartridge.consumables.tools.data.AbstractModelTool;

import java.util.List;

import static ua.notky.cartridge.consumables.tools.data.model.CartridgeTool.CARTRIDGE_2;

public class DepartmentTool extends AbstractModelTool {
    public static final int ID_DEPARTMENT_2 = 2;
    public static final int ID_DEPARTMENT_5 = 5;

    public static final Department DEPARTMENT_1 = new Department(1, "department_1");
    public static final Department DEPARTMENT_2 = new Department(ID_DEPARTMENT_2, "department_2");
    public static final Department DEPARTMENT_3 = new Department(3, "department_3");
    public static final Department DEPARTMENT_4 = new Department(4, "department_4");
    public static final Department DEPARTMENT_5 = new Department(ID_DEPARTMENT_5, "department_5");

    public static List<Department> DEPARTMENTS = List.of(
            DEPARTMENT_1, DEPARTMENT_2, DEPARTMENT_3, DEPARTMENT_4, DEPARTMENT_5);

    public static Department getNew(){
        Department department = new Department("New Department");
        department.setCartridge(CARTRIDGE_2);
        return department;
    }

    private static Department getCopy(Department value){
        Department department = new Department(value.getId(), value.getName());
        department.setCartridge(value.getCartridge());
        return department;
    }

    public static Department getUpdated(Department value){
        Department updated = getCopy(value);
        updated.setName("Updated Department");
        return updated;
    }
}
