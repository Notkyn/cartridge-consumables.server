import { CartridgesTable } from "%blocks%/modules/tables/cartridge-table/cartridge-table";
import { CartridgeEditor } from "%blocks%/modules/modal/cartridge-editor/cartridge-editor";
import { AddNewBtn } from "%blocks%/components/btn/btn";
import { configuration } from "%config%/config_fabric";

configuration.setCartridgeConfig();

const modal = new CartridgeEditor();
new AddNewBtn().build(modal);
new CartridgesTable("cartridges").build(modal);