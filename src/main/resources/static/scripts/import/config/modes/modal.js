class ModalMode {
    constructor() {
        this._isNewModalMode = false;
        this._isEditModalMode = false;
    }

    refresh(){
        this._isNewModalMode = false;
        this._isEditModalMode = false;
    }

    setNewModalMode(){
        this._isNewModalMode = true;
        this._isEditModalMode = false;
    }

    setEditModalMode(){
        this._isNewModalMode = false;
        this._isEditModalMode = true;
    }

    isNewModalMode(){
        return this._isNewModalMode;
    }

    isEditModalMode(){
        return this._isEditModalMode;
    }
}


export const modalMode = new ModalMode();