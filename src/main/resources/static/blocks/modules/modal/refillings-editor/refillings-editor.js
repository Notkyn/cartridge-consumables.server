import { Overflow } from "%blocks%/components/overflow/overflow";
import { configuration } from "%config%/config_fabric";
import { dateHelper } from "%api%/date-helper";
import { request } from "%api%/rest";
import { departmentsConfig } from "%config%/model/department";
import {constants} from "%config%/constants";
import { sort } from "%api%/sort";


export class RefillingsEditor {
    constructor(){
        this._modal = document.querySelector(".refillings-editor");
        this._overflow = new Overflow();
        this._listDepartments = [];

        this._loadDepartments = this._loadDepartments.bind(this);
    }

    show(data){
        console.log("data: " + data);

        this._clearRootElement(this._modal);

        this._date = document.createElement("div");
        this._date.classList.add("refillings-editor-date");
        this._date.innerText = dateHelper.getFormatCurrentDate();

        this._listRefillings = document.createElement("div");
        this._listRefillings.classList.add("refillings-editor-refillings");

        let applyBtn = document.createElement("button");
        applyBtn.classList.add("btn");
        applyBtn.classList.add("btn_modal");
        applyBtn.innerText = i18n.modalAddBtn;

        applyBtn.addEventListener(configuration.getConstants().eventClick, () => {
            let date = "";

            let refillingsToSave = [];
            let refillings = this._listRefillings.querySelectorAll(".refillings-editor-refillings-item");
            refillings.forEach(e => {
                console.log(e);
                let refilling = {};
                let title = e.querySelector(".refillings-editor-refillings-item-refill-title");

                this._listDepartments.forEach(e => {
                    if(e.name.includes(title.innerText)){
                        refilling.department = e;
                    }
                });

                let points = this._listRefillings.querySelectorAll(".refillings-editor-refillings-item-info-point");
                points.forEach(point => {
                   console.log(point);
                   //
                });

                console.log(refilling);
                refillingsToSave.push(refilling);
            });

            // to save
        });

        let cancelBtn = document.createElement("button");
        cancelBtn.classList.add("btn");
        cancelBtn.classList.add("btn_modal-cancel");
        cancelBtn.innerText = i18n.modalCancelBtn;

        cancelBtn.addEventListener(configuration.getConstants().eventClick, () => {
            this._createEvent();
        });

        let buttons = document.createElement("div");
        buttons.classList.add("modal-field-btns");
        buttons.appendChild(applyBtn);
        buttons.appendChild(cancelBtn);

        let columnRefillings = document.createElement("div");
        columnRefillings.classList.add("refillings-editor-column");
        columnRefillings.appendChild(this._date);
        columnRefillings.appendChild(this._listRefillings);
        columnRefillings.appendChild(buttons);

        this._departments = document.createElement("div");
        this._departments.classList.add("refillings-editor-departments");

        let columnDepartments = document.createElement("div");
        columnDepartments.classList.add("refillings-editor-column");
        columnDepartments.appendChild(this._createSearchBar());
        columnDepartments.appendChild(this._departments);
        request.get(departmentsConfig.getAllAjax(), this._loadDepartments);

        let wrap = document.createElement("div");
        wrap.classList.add("refillings-editor-wrap");
        wrap.appendChild(columnRefillings);
        wrap.appendChild(columnDepartments);

        this._modal.appendChild(wrap);

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
        input.addEventListener(constants.eventInput, () => {
                let tempList = [];

                this._listDepartments.forEach(e => {
                    if(e.name.includes(input.value)){
                        tempList.push(e);
                    }
                });

                this._createDepartmentsList(tempList);
        });

        let label = document.createElement("label");
        label.setAttribute("for", "refillings-editor-search-input");
        label.appendChild(input);

        let searchBar = document.createElement("div");
        searchBar.classList.add("refillings-editor-search");
        searchBar.appendChild(label);

        return searchBar;
    }

    _loadDepartments(departments){
        this._listDepartments = departments;
        this._createDepartmentsList(departments);
    }

    _createDepartmentsList(list){
        this._clearRootElement(this._departments);

        sort.byNameAsc(list);

        list.forEach(e => {
            this._departments.appendChild(this._createDepartmentItem(e));
        });
    }

    _createDepartmentItem(department){
        let plus = this._createBaseElement("refillings-editor-departments-item-plus");
        plus.addEventListener(constants.eventClick, () => {
            this._listRefillings.appendChild(this._createRefilling(department));
        });

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

    _createRefilling(department){
        let refTitle = document.createElement("div");
        refTitle.classList.add("refillings-editor-refillings-item-refill-title");
        refTitle.innerText = department.name;
        let refDeleteBtn = document.createElement("div");
        refDeleteBtn.classList.add("refillings-editor-refillings-item-refill-btn");

        let refill = document.createElement("div");
        refill.classList.add("refillings-editor-refillings-item-refill");
        refill.appendChild(refTitle);
        refill.appendChild(refDeleteBtn);

        let refInfo = document.createElement("div");
        refInfo.classList.add("refillings-editor-refillings-item-info");
        refInfo.classList.add(constants.classHide);
        refInfo.appendChild(this._createOnePointRefilling(i18n.nameDrum));
        refInfo.appendChild(this._createOnePointRefilling(i18n.nameMagneticShaft));
        refInfo.appendChild(this._createOnePointRefilling(i18n.namePrimaryChargeShaft));
        refInfo.appendChild(this._createOnePointRefilling(i18n.nameCleaningBlade));
        refInfo.appendChild(this._createOnePointRefilling(i18n.nameDispensingBlade));

        let refillItem = document.createElement("div");
        refillItem.classList.add("refillings-editor-refillings-item");
        refillItem.appendChild(refill);
        refillItem.appendChild(refInfo);

        refill.addEventListener(constants.eventClick, () => {
            if(refTitle.classList.contains("refillings-editor-refillings-item-refill-title-active")){
                refTitle.classList.remove("refillings-editor-refillings-item-refill-title-active")
                refInfo.classList.add(constants.classHide);
            } else {
                refTitle.classList.add("refillings-editor-refillings-item-refill-title-active")
                refInfo.classList.remove(constants.classHide);
            }
        });

        refDeleteBtn.addEventListener(constants.eventClick, () => {
           this._listRefillings.removeChild(refillItem);
        });

        return refillItem;
    }

    _createOnePointRefilling(title){
        let titlePoint = document.createElement("div");
        titlePoint.classList.add("refillings-editor-refillings-item-info-point-title");
        titlePoint.innerText = title;

        let point = document.createElement("div");
        point.classList.add("refillings-editor-refillings-item-info-point");
        point.appendChild(titlePoint);

        point.addEventListener(constants.eventClick, () => {
            if(point.classList.contains("refillings-editor-refillings-item-info-point-active")){
                point.classList.remove("refillings-editor-refillings-item-info-point-active");
            } else {
                point.classList.add("refillings-editor-refillings-item-info-point-active");
            }
        });

        return point;
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
}