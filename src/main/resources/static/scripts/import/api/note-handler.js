import { Note } from "%blocks%/modules/note/note";

class NoteHandler {
    constructor(){
        this._note = new Note();

        this.failDeleteObject = this.failDeleteObject.bind(this);
        this.failLoad = this.failLoad.bind(this);
    }

    // failed

    failDeleteObject(error){
        this._note.fail(i18n.noteFailDelete, error.typeMessage);
    }

    failLoad(error){
        this._note.fail(i18n.noteFailLoad, error.typeMessage);
    }


    // success

    successDeleteObject(){
        this._note.success(i18n.noteSuccessDelete);
    }
}

export const noteHandler = new NoteHandler();