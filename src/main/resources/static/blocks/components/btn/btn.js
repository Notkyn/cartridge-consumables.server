import { configuration } from "%config%/config_fabric";

export class AddNewBtn {
    constructor() {
        this._addNewBtn = document.querySelector(".btn_add");
    }

    build(modal){
        this._modal = modal;
        this._addNewBtn.addEventListener("click", () => {
            configuration.getModalMode().setNewModalMode();
            this._showModal();
        });
    }

    _showModal(){
        this._modal.show();
    }
}
