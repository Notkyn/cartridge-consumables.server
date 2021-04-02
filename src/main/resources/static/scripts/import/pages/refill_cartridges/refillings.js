import { RefillingsEditor } from "%blocks%/modules/modal/refillings-editor/refillings-editor";
import { AddNewBtn } from "%blocks%/components/btn/btn";
import { RefillingsTable } from "%blocks%/modules/tables/refillings-table/refillings-table";
import { configuration } from "%config%/config_fabric";

configuration.setRefillingsConfig();

const modal = new RefillingsEditor();
new AddNewBtn().build(modal);
new RefillingsTable("refillings").build(modal);