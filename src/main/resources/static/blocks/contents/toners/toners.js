import { PartsTable } from "%blocks%/modules/parts-table/parts-table";
import { tonersConfig } from "%config%/parts/toners";

const table = new PartsTable("toners");
table.setAjaxConfig(tonersConfig);
table.aplly();