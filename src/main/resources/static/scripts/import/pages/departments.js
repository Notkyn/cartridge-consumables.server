import { DepartmentsTable } from "%blocks%/modules/tables/department-table/department-table";
import { DepartmentEditor } from "%blocks%/modules/modal/department-editor/department-editor";
import { AddNewBtn } from "%blocks%/components/btn/btn";
import { configuration } from "%config%/config_fabric";

configuration.setDepartmentConfig();

const modal = new DepartmentEditor();
new AddNewBtn().build(modal);
new DepartmentsTable("departments").build(modal);