import { Note } from "%blocks%/modules/note/note";

class NoteHandler {
    constructor(){
        this._note = new Note();

        this.failDeleteObject = this.failDeleteObject.bind(this);
    }

    failDeleteObject(error){
        this._note.fail(i18n.noteFailDelete, error.typeMessage);
    }
}

export const noteHandler = new NoteHandler();