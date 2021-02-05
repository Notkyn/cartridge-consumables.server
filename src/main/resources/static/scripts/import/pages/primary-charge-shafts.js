import { PartsTable } from "%blocks%/modules/tables/parts-table/parts-table";
import { EditorParts } from "%blocks%/modules/modal/editor-parts/editor-parts";
import { AddNewBtn } from "%blocks%/components/btn/btn";
import { configuration } from "%config%/config_fabric";

configuration.setPrimaryChargeShaftConfig();

const modal = new EditorParts();
new AddNewBtn().build(modal);
new PartsTable("primary-charge-shafts").build(modal);