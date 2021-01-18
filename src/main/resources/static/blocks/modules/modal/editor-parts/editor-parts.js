import { Overflow } from "%blocks%/components/overflow/overflow";
import { configuration } from "%config%/config_fabric";
import {request} from "%api%/rest";

export class EditorParts {
    constructor(){
        this._modal = document.querySelector(".editor-parts");
        this._overflow = new Overflow();
        this._title = this._modal.querySelector(".modal-title");
        this._id = this._modal.querySelector(".modal-id");
        this._apply_btn = this._modal.querySelector(".btn_modal");
        this._cancel_btn = this._modal.querySelector(".btn_modal-cancel");
        this._name_field = this._modal.querySelector("#modal-field-name");
        this._name_field_error = this._modal.querySelector("#modal-field-error-name");

        this._table = null;

        this._failValidationForm = this._failValidationForm.bind(this);
        this._createEvent = this._createEvent.bind(this);

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

    _getData(){
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

        this._validatedNameField(true);

    }

    _createEvent(){
        this._close();
        this._table.dispatchEvent(new CustomEvent(configuration.getConstants().eventModalAddBtn, {
            bubbles: true
        }));
    }

    _failValidationForm(error){
        if(error.details.name !== undefined){
            this._validatedNameField(false, error.details.name);
        }
    }

    _validatedNameField(isOk, msg){
        if(isOk){
            this._name_field.classList.remove("modal-field-value_error");
            this._name_field_error.classList.add("hide");
            this._name_field_error.innerHTML = "";
        } else {
            this._name_field.classList.add("modal-field-value_error");
            this._name_field_error.classList.remove("hide");
            this._name_field_error.innerHTML = msg;
        }
    }

    setTable(table){
        this._table = table;

        this._apply_btn.addEventListener(configuration.getConstants().eventClick, () => {

            request.post(configuration.getInstanse().getAddAjax(),
                this._getData(),
                this._createEvent,
                this._failValidationForm);
        });

        this._cancel_btn.addEventListener(configuration.getConstants().eventClick, () => {
            this._createEvent();
        });
    }
}