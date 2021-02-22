import { Overflow } from "%blocks%/components/overflow/overflow";
import { configuration } from "%config%/config_fabric";
import {request} from "%api%/rest";
import { cartridgesConfig } from "%config%/model/cartridge";

export class DepartmentEditor {
    constructor(){
        this._modal = document.querySelector(".department-editor");
        this._overflow = new Overflow();

        // btns
        this._apply_btn = this._modal.querySelector(".btn_modal");
        this._cancel_btn = this._modal.querySelector(".btn_modal-cancel");

        // fields
        this._title = this._modal.querySelector(".modal-title");
        this._id = this._modal.querySelector(".modal-id");
        this._name_field = this._modal.querySelector("#modal-field-name");
        this._name_field_error = this._modal.querySelector("#modal-field-error-name");
        this._select_cartridge = this._modal.querySelector("#modal-select-cartridge");
        this._cartridge_field_error = this._modal.querySelector("#modal-field-error-cartridge");

        // data
        this._isNew = true;
        this._table = null;
        this._data = null;
        this._listCartridges = null;

        this._createOption = this._createOption.bind(this);
        this._loadCartridges = this._loadCartridges.bind(this);
        this._failValidationForm = this._failValidationForm.bind(this);
        this._createEvent = this._createEvent.bind(this);
    }

    show(data){
        this._title.innerHTML = configuration.getInstanse().getName();

        if(configuration.getModalMode().isNewModalMode()){
            this._apply_btn.innerHTML = i18n.modalAddBtn;
        }
        if(configuration.getModalMode().isEditModalMode()){
            this._apply_btn.innerHTML = i18n.modalUpdateBtn;
        }

        this._resetErrorFields();

        if(data !== undefined && data !== null){
            this._data = data;
            this._isNew = false;
            this._name_field.value = data.name;
            this._id.textContent = data.id;
        } else {
            this._isNew = true;
            this._name_field.value = "";
            this._id.textContent = "";
        }

        request.get(cartridgesConfig.getAllAjax(), this._loadCartridges);

        this._overflow.show();
        this._modal.classList.remove(configuration.getConstants().classHide);
    }

    _resetErrorFields(){
        this._validatedField(this._name_field, this._name_field_error, true);
        this._validatedField(this._select_cartridge, this._cartridge_field_error, true);
    }

    _clearChild(parent){
        while(parent.firstChild){
            parent.removeChild(parent.firstChild);
        }
    }

    _createDefaultOption(){
        let option = document.createElement("option");
        option.setAttribute("selected", "selected");
        option.setAttribute("hidden", "hidden");
        option.setAttribute("disabled", "disabled");
        option.setAttribute("value", "");
        option.innerText = i18n.modalSelect;
        return option;
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

    _getData(){
        let resultData = {};

        if(this._id.innerHTML.length > 0){
            resultData.id = this._id.innerHTML;
        }
        resultData.name = this._name_field.value;
        resultData.cartridge = this._getObjectFromList(this._listCartridges, this._select_cartridge.value);

        return resultData;
    }

    _getObjectFromList(list, id){
        for(let i = 0; i < list.length; i++){
            if(id === list[i].id.toString()){
                return list[i];
            }
        }
    }

    _createEvent(){
        this._close();
        this._table.dispatchEvent(new CustomEvent(configuration.getConstants().eventModalAddBtn, {
            bubbles: true
        }));
    }

    _close(){
        this._overflow.hide();
        this._modal.classList.add(configuration.getConstants().classHide);
    }

    _failValidationForm(error){
        this._resetErrorFields();

        if(error.details.name !== undefined){
            this._validatedField(this._name_field, this._name_field_error, false, error.details.name);
        }
        if(error.details.cartridge !== undefined){
            this._validatedField(this._select_cartridge, this._cartridge_field_error, false, error.details.cartridge);
        }
    }

    _validatedField(field, errorField, isOk, msg){
        if(isOk){
            field.classList.remove("modal-field-value_error");
            errorField.classList.add(configuration.getConstants().classHide);
            errorField.innerHTML = "";
        } else {
            field.classList.add("modal-field-value_error");
            errorField.classList.remove(configuration.getConstants().classHide);
            errorField.innerHTML = msg;
        }
    }

    _loadCartridges(list){
        this._listCartridges = list;

        this._clearChild(this._select_cartridge);

        if(this._isNew) {
            this._select_cartridge.appendChild(this._createDefaultOption());
        }

        list.forEach(item => {
            this._select_cartridge.appendChild(this._createOption(item,
                this._isNew ? this._data : this._data.cartridge));
        });
    }

    _createOption(item, data){
        let option = document.createElement("option");
        option.setAttribute("value", item.id);
        option.innerText = item.name;

        if(!this._isNew && item.id === data.id){
            option.setAttribute("selected", "selected");
        }

        return option;
    }
}