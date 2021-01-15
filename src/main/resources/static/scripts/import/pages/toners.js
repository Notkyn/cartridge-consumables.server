import { PartsTable } from "%blocks%/modules/parts-table/parts-table";
import { EditorParts } from "%blocks%/modules/modal/editor-parts/editor-parts";
import { AddNewBtn } from "%blocks%/components/btn/btn";
import { configuration } from "%config%/config_fabric";
import { Note } from "%api%/notifications";

configuration.setTonerConfig();

const modal = new EditorParts();
new AddNewBtn().build(modal);
new PartsTable("toners").build(modal);


let note = new Note();
note.show();



