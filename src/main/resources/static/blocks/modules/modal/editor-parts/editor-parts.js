import { Overflow } from "%blocks%/components/overflow/overflow";
import { configuration } from "%config%/config_fabric";

export class EditorParts {
    constructor(){
        this._modal = document.querySelector(".editor-parts");
        this._overflow = new Overflow();
        this._title = this._modal.querySelector(".modal-title");
        this._id = this._modal.querySelector(".modal-id");
        this._apply_btn = this._modal.querySelector(".btn_modal");
        this._cancel_btn = this._modal.querySelector(".btn_modal-cancel");
        this._name_field = this._modal.querySelector("#modal-field-name");

        this._close();
    }

    show(data){
        this._configure();

        if(data !== undefined && data !== null){
            this._name_field.value = data.name;
            this._id.textContent = data.id;
        } else {
            this._name_field.value = "";
            this._id.textContent = "";
        }
        this._overflow.show();
        this._modal.classList.remove(configuration.getConstants().classHide);
    }

    _close(){
        this._overflow.hide();
        this._modal.classList.add(configuration.getConstants().classHide);
    }

    getData(){
        let resultData = {};

        if(this._id.innerHTML.length > 0){
            resultData.id = this._id.innerHTML;
        }
        resultData.name = this._name_field.value;

        return resultData;
    }

    _configure(){
        this._title.innerHTML = configuration.getInstanse().getName();

        if(configuration.getModalMode().isNewModalMode()){
            this._apply_btn.innerHTML = i18n.modalAddBtn;
        }
        if(configuration.getModalMode().isEditModalMode()){
            this._apply_btn.innerHTML = i18n.modalUpdateBtn;
        }

        this._cancel_btn.addEventListener(configuration.getConstants().eventClick, () => {
            this._close();
        })
    }

    setTable(table){
        this._apply_btn.addEventListener(configuration.getConstants().eventClick, () => {

            this._close();

            table.dispatchEvent(new CustomEvent(configuration.getConstants().eventModalAddBtn, {
                bubbles: true
            }));
        })
    }
}