import { Overflow } from "%blocks%/components/overflow/overflow";
import { configuration } from "%config%/config_fabric";
import {request} from "%api%/rest";
import { departmentsConfig } from "%config%/model/department";
import {tonersConfig} from "%config%/model/parts/toners";
import {drumsConfig} from "%config%/model/parts/drums";
import {magneticShaftsConfig} from "%config%/model/parts/magnetic-shaft";
import {primaryChargeShaftsConfig} from "%config%/model/parts/primary-charge-shaft";
import {cleaningBladesConfig} from "%config%/model/parts/cleaning-blade";
import {dispensingBladesConfig} from "%config%/model/parts/dispensing-blade";

export class RefillingsEditor {
    constructor(){
        this._modal = document.querySelector(".refillings-editor");
        this._overflow = new Overflow();
        //
        // // btns
        // this._apply_btn = this._modal.querySelector(".btn_modal");
        // this._cancel_btn = this._modal.querySelector(".btn_modal-cancel");
        //
        // // fields
        // this._title = this._modal.querySelector(".modal-title");
        // this._id = this._modal.querySelector(".modal-id");
        // this._name_field = this._modal.querySelector("#modal-field-name");
        // this._name_field_error = this._modal.querySelector("#modal-field-error-name");
        // this._coef_field = this._modal.querySelector("#modal-field-coef");
        // this._coef_field_error = this._modal.querySelector("#modal-field-error-coef");
        // this._select_toner = this._modal.querySelector("#modal-select-toner");
        // this._toner_field_error = this._modal.querySelector("#modal-field-error-toner");
        // this._select_drum = this._modal.querySelector("#modal-select-drum");
        // this._drum_field_error = this._modal.querySelector("#modal-field-error-drum");
        // this._select_magneticshaft = this._modal.querySelector("#modal-select-magneticshaft");
        // this._magneticshaft_field_error = this._modal.querySelector("#modal-field-error-magneticshaft");
        // this._select_primarychargeshaft = this._modal.querySelector("#modal-select-primarychargeshaft");
        // this._primarychargeshaft_field_error = this._modal.querySelector("#modal-field-error-primarychargeshaft");
        // this._select_cleaningblade = this._modal.querySelector("#modal-select-cleaningblade");
        // this._cleaningblade_field_error = this._modal.querySelector("#modal-field-error-cleaningblade");
        // this._select_dispencingblade = this._modal.querySelector("#modal-select-dispensingblade");
        // this._dispensingblade_field_error = this._modal.querySelector("#modal-field-error-dispensingblade");
        //
        // // data
        // this._isNew = true;
        // this._table = null;
        // this._data = null;
        // this._listToners = null;
        // this._listDrums = null;
        // this._listMagneticShafts = null;
        // this._listPrimaryChargeShafts = null;
        // this._listCleaningBlabes = null;
        // this._listDispencingBlades = null;
        //
        // this._createOption = this._createOption.bind(this);
        // this._loadToners = this._loadToners.bind(this);
        // this._loadDrums = this._loadDrums.bind(this);
        // this._loadMagneticShafts = this._loadMagneticShafts.bind(this);
        // this._loadPrimaryChargeShafts = this._loadPrimaryChargeShafts.bind(this);
        // this._loadCleaningBlades = this._loadCleaningBlades.bind(this);
        // this._loadDispencingBlades = this._loadDispencingBlades.bind(this);
        // this._failValidationForm = this._failValidationForm.bind(this);
        this._createDepartmentsList = this._createDepartmentsList.bind(this);
    }

    show(data){
        this._clearRootElement(this._modal);

        let column1 = document.createElement("div");
        column1.classList.add("refillings-editor-column");

        this._departments = document.createElement("div");
        this._departments.classList.add("refillings-editor-departments");

        let column2 = document.createElement("div");
        column2.classList.add("refillings-editor-column");
        column2.appendChild(this._createSearchBar());
        column2.appendChild(this._departments);
        request.get(departmentsConfig.getAllAjax(), this._createDepartmentsList);


        let wrap = document.createElement("div");
        wrap.classList.add("refillings-editor-wrap");
        wrap.appendChild(column1);
        wrap.appendChild(column2);

        this._modal.appendChild(wrap);

        // this._title.innerHTML = configuration.getInstanse().getName();
        //
        // if(configuration.getModalMode().isNewModalMode()){
        //     this._apply_btn.innerHTML = i18n.modalAddBtn;
        // }
        // if(configuration.getModalMode().isEditModalMode()){
        //     this._apply_btn.innerHTML = i18n.modalUpdateBtn;
        // }
        //
        // this._resetErrorFields();
        //
        // this._coef_field.addEventListener('input',
        //     function(e){
        //         this.value = this.value.replace(/[^\d]/g, '');
        //     }
        // );
        //
        // if(data !== undefined && data !== null){
        //     this._data = data;
        //     this._isNew = false;
        //     this._name_field.value = data.name;
        //     this._id.textContent = data.id;
        //     this._coef_field.value = data.coefToner;
        // } else {
        //     this._isNew = true;
        //     this._name_field.value = "";
        //     this._id.textContent = "";
        //     this._coef_field.value = 0;
        // }
        //
        // request.get(tonersConfig.getAllAjax(), this._loadToners);
        // request.get(drumsConfig.getAllAjax(), this._loadDrums);
        // request.get(magneticShaftsConfig.getAllAjax(), this._loadMagneticShafts);
        // request.get(primaryChargeShaftsConfig.getAllAjax(), this._loadPrimaryChargeShafts);
        // request.get(cleaningBladesConfig.getAllAjax(), this._loadCleaningBlades);
        // request.get(dispensingBladesConfig.getAllAjax(), this._loadDispencingBlades);

        this._overflow.show();
        this._modal.classList.remove(configuration.getConstants().classHide);
    }

    _clearRootElement(root) {
        while(root.firstChild){
            root.removeChild(root.firstChild);
        }
    }

    _createSearchBar(){
        let input = document.createElement("input");
        input.setAttribute("id", "refillings-editor-search-input");
        input.setAttribute("type", "text");

        let label = document.createElement("label");
        label.setAttribute("for", "refillings-editor-search-input");
        label.appendChild(input);

        let searchBar = document.createElement("div");
        searchBar.classList.add("refillings-editor-search");
        searchBar.appendChild(label);

        return searchBar;
    }

    _createDepartmentsList(list){
        this._clearRootElement(this._departments);

        list.forEach(e => {
            console.log(e);
            console.log(`id department: ${e.id}`);
            console.log(`name departments: ${e.name}`);
            console.log(`cartridge name: ${e.cartridge.name}`);

            this._departments.appendChild(this._createDepartmentItem(e));



        });
    }

    _createDepartmentItem(department){
        let plus = this._createBaseElement("refillings-editor-departments-item-plus");

        let title = this._createBaseElement("refillings-editor-departments-item-info-title");
        title.innerText = department.name;
        let subtitle = this._createBaseElement("refillings-editor-departments-item-info-subtitle");
        subtitle.innerText = department.cartridge.name;
        let info = this._createBaseElement("refillings-editor-departments-item-info");
        info.appendChild(title);
        info.appendChild(subtitle);

        let item = this._createBaseElement("refillings-editor-departments-item");
        item.appendChild(plus);
        item.appendChild(info);

        return item;
    }

    // _resetErrorFields(){
    //     this._validatedField(this._name_field, this._name_field_error, true);
    //     this._validatedField(this._coef_field, this._coef_field_error, true);
    //     this._validatedField(this._select_toner, this._toner_field_error, true);
    //     this._validatedField(this._select_drum, this._drum_field_error, true);
    //     this._validatedField(this._select_magneticshaft, this._magneticshaft_field_error, true);
    //     this._validatedField(this._select_primarychargeshaft, this._primarychargeshaft_field_error, true);
    //     this._validatedField(this._select_cleaningblade, this._cleaningblade_field_error, true);
    //     this._validatedField(this._select_dispencingblade, this._dispensingblade_field_error, true);
    // }
    //
    // _clearChild(parent){
    //     while(parent.firstChild){
    //         parent.removeChild(parent.firstChild);
    //     }
    // }
    //
    // _createDefaultOption(){
    //     let option = document.createElement("option");
    //     option.setAttribute("selected", "selected");
    //     option.setAttribute("hidden", "hidden");
    //     option.setAttribute("disabled", "disabled");
    //     option.setAttribute("value", "");
    //     option.innerText = i18n.modalSelect;
    //     return option;
    // }

    setTable(table){
        this._table = table;

        // this._apply_btn.addEventListener(configuration.getConstants().eventClick, () => {
        //
        //     request.post(configuration.getInstanse().getAddAjax(),
        //         this._getData(),
        //         this._createEvent,
        //         this._failValidationForm);
        // });
        //
        // this._cancel_btn.addEventListener(configuration.getConstants().eventClick, () => {
        //     this._createEvent();
        // });
    }

    _createBaseElement(clazz) {
        let item = document.createElement("div");
        item.classList.add(clazz);
        return item;
    }

    // _getData(){
    //     let resultData = {};
    //
    //     if(this._id.innerHTML.length > 0){
    //         resultData.id = this._id.innerHTML;
    //     }
    //     resultData.name = this._name_field.value;
    //     resultData.coefToner = this._coef_field.value;
    //     resultData.toner = this._getObjectFromList(this._listToners, this._select_toner.value);
    //     resultData.drum = this._getObjectFromList(this._listDrums, this._select_drum.value);
    //     resultData.magneticShaft = this._getObjectFromList(this._listMagneticShafts, this._select_magneticshaft.value);
    //     resultData.primaryChargeShaft = this._getObjectFromList(this._listPrimaryChargeShafts, this._select_primarychargeshaft.value);
    //     resultData.cleaningBlade = this._getObjectFromList(this._listCleaningBlabes, this._select_cleaningblade.value);
    //     resultData.dispensingBlade = this._getObjectFromList(this._listDispencingBlades, this._select_dispencingblade.value);
    //
    //     return resultData;
    // }
    //
    // _getObjectFromList(list, id){
    //     for(let i = 0; i < list.length; i++){
    //         if(id === list[i].id.toString()){
    //             return list[i];
    //         }
    //     }
    // }
    //
    // _createEvent(){
    //     this._close();
    //     this._table.dispatchEvent(new CustomEvent(configuration.getConstants().eventModalAddBtn, {
    //         bubbles: true
    //     }));
    // }
    //
    // _close(){
    //     this._overflow.hide();
    //     this._modal.classList.add(configuration.getConstants().classHide);
    // }
    //
    // _failValidationForm(error){
    //     this._resetErrorFields();
    //
    //     if(error.details.name !== undefined){
    //         this._validatedField(this._name_field, this._name_field_error, false, error.details.name);
    //     }
    //     if(error.details.coefToner !== undefined){
    //         this._validatedField(this._coef_field, this._coef_field_error, false, error.details.coefToner);
    //     }
    //     if(error.details.toner !== undefined){
    //         this._validatedField(this._select_toner, this._toner_field_error, false, error.details.toner);
    //     }
    //     if(error.details.drum !== undefined){
    //         this._validatedField(this._select_drum, this._drum_field_error, false, error.details.drum);
    //     }
    //     if(error.details.magneticShaft !== undefined){
    //         this._validatedField(this._select_magneticshaft, this._magneticshaft_field_error,
    //             false, error.details.magneticShaft);
    //     }
    //     if(error.details.primaryChargeShaft !== undefined){
    //         this._validatedField(this._select_primarychargeshaft, this._primarychargeshaft_field_error,
    //             false, error.details.primaryChargeShaft);
    //     }
    //     if(error.details.cleaningBlade !== undefined){
    //         this._validatedField(this._select_cleaningblade, this._cleaningblade_field_error,
    //             false, error.details.cleaningBlade);
    //     }
    //     if(error.details.dispensingBlade !== undefined){
    //         this._validatedField(this._select_dispencingblade, this._dispensingblade_field_error,
    //             false, error.details.dispensingBlade);
    //     }
    // }
    //
    // _validatedField(field, errorField, isOk, msg){
    //     if(isOk){
    //         field.classList.remove("modal-field-value_error");
    //         errorField.classList.add(configuration.getConstants().classHide);
    //         errorField.innerHTML = "";
    //     } else {
    //         field.classList.add("modal-field-value_error");
    //         errorField.classList.remove(configuration.getConstants().classHide);
    //         errorField.innerHTML = msg;
    //     }
    // }
    //
    // _loadToners(list){
    //     this._listToners = list;
    //
    //     this._clearChild(this._select_toner);
    //
    //     if(this._isNew) {
    //         this._select_toner.appendChild(this._createDefaultOption());
    //     }
    //
    //     list.forEach(item => {
    //         this._select_toner.appendChild(this._createOption(item,
    //             this._isNew ? this._data : this._data.toner));
    //     });
    // }
    //
    // _loadDrums(list){
    //     this._listDrums = list;
    //
    //     this._clearChild(this._select_drum);
    //
    //     if(this._isNew) {
    //         this._select_drum.appendChild(this._createDefaultOption());
    //     }
    //
    //     list.forEach(item => {
    //         this._select_drum.appendChild(this._createOption(item,
    //             this._isNew ? this._data : this._data.drum));
    //     });
    // }
    //
    // _loadMagneticShafts(list){
    //     this._listMagneticShafts = list;
    //
    //     this._clearChild(this._select_magneticshaft);
    //
    //     if(this._isNew) {
    //         this._select_magneticshaft.appendChild(this._createDefaultOption());
    //     }
    //
    //     list.forEach(item => {
    //         this._select_magneticshaft.appendChild(this._createOption(item,
    //             this._isNew ? this._data : this._data.magneticShaft));
    //     });
    // }
    //
    // _loadPrimaryChargeShafts(list){
    //     this._listPrimaryChargeShafts = list;
    //
    //     this._clearChild(this._select_primarychargeshaft);
    //
    //     if(this._isNew) {
    //         this._select_primarychargeshaft.appendChild(this._createDefaultOption());
    //     }
    //
    //     list.forEach(item => {
    //         this._select_primarychargeshaft.appendChild(this._createOption(item,
    //             this._isNew ? this._data : this._data.primaryChargeShaft));
    //     });
    // }
    //
    // _loadCleaningBlades(list){
    //     this._listCleaningBlabes = list;
    //
    //     this._clearChild(this._select_cleaningblade);
    //
    //     if(this._isNew) {
    //         this._select_cleaningblade.appendChild(this._createDefaultOption());
    //     }
    //
    //     list.forEach(item => {
    //         this._select_cleaningblade.appendChild(this._createOption(item,
    //             this._isNew ? this._data : this._data.cleaningBlade));
    //     });
    // }
    //
    // _loadDispencingBlades(list){
    //     this._listDispencingBlades = list;
    //
    //     this._clearChild(this._select_dispencingblade);
    //
    //     if(this._isNew) {
    //         this._select_dispencingblade.appendChild(this._createDefaultOption());
    //     }
    //
    //     list.forEach(item => {
    //         this._select_dispencingblade.appendChild(this._createOption(item,
    //             this._isNew ? this._data : this._data.dispensingBlade));
    //     });
    // }
    //
    // _createOption(item, data){
    //     let option = document.createElement("option");
    //     option.setAttribute("value", item.id);
    //     option.innerText = item.name;
    //
    //     if(!this._isNew && item.id === data.id){
    //         option.setAttribute("selected", "selected");
    //     }
    //
    //     return option;
    // }
}