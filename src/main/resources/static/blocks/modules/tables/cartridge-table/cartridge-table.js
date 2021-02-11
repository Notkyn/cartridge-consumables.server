import { request } from '%api%/rest';
import { configuration } from "%config%/config_fabric";
import { sort } from "%api%/sort";
import { noteHandler } from "%api%/note-handler";

export class CartridgesTable {

    constructor(classContentContainer) {
        this._classContainer = classContentContainer;
        this._switchSort = true;
        this._indexValue = 1;

        this._createBody = this._createBody.bind(this);
        this._refreshTable = this._refreshTable.bind(this);
        this._renderModal = this._renderModal.bind(this);
        this._successDeteleItem = this._successDeteleItem.bind(this);
    }

    build(modal){
        this._container = document.querySelector(`div.${this._classContainer}`);
        this._table = this._container.querySelector(".cartridge-table");

        this._modal = modal;
        this._modal.setTable(this._table);

        this._table.addEventListener(configuration.getConstants().eventModalAddBtn, this._refreshTable);

        this._refreshTable();
    }

    _renderBody(){
        request.get(configuration.getInstanse().getAllAjax(),
            this._createBody,
            noteHandler.failLoad);
    }

    _renderModal(data){
        configuration.getModalMode().setEditModalMode();
        this._modal.show(data);
    }

    _refreshTable(){
        this._clearTable();

        this._table.appendChild(this._createHeader());

        this._renderBody();
    }

    _clearTable(){
        while(this._table.firstChild){
            this._table.removeChild(this._table.firstChild);
        }
    }

    _createHeader(){
        let indexSection = document.createElement("div");
        indexSection.classList.add("cartridge-table-header-index");
        indexSection.innerText = "#";
        let partsSection = document.createElement("div");
        partsSection.classList.add("cartridge-table-header-parts");
        partsSection.innerText = i18n.nameParts;

        let arrowUp = document.createElement("div");
        arrowUp.classList.add("cartridge-table-arrows-up");
        this._arrowUp = arrowUp;
        let arrowDown = document.createElement("div");
        arrowDown.classList.add("cartridge-table-arrows-down");
        this._arrowDown = arrowDown;

        let arrows = document.createElement("div");
        arrows.classList.add("cartridge-table-arrows");
        arrows.appendChild(arrowUp);
        arrows.appendChild(arrowDown);

        let name = document.createElement("span");
        name.innerText = i18n.nameName;

        let nameSection = document.createElement("div");
        nameSection.classList.add("cartridge-table-header-name");
        nameSection.appendChild(arrows);
        nameSection.appendChild(name);
        nameSection.addEventListener("click", () => {
            this._switchSort = !this._switchSort;

            this._refreshTable();
            this._setSort(this._switchSort);
        });

        let header = document.createElement("div");
        header.classList.add("cartridge-table-header");
        header.appendChild(indexSection);
        header.appendChild(nameSection);
        header.appendChild(partsSection);

        return header;
    }

    _createBody(data) {
        this._indexValue = 1;

        if(this._switchSort){
            sort.byNameAsc(data);
        } else {
            sort.byNameDesc(data);
        }

        let body = document.createElement("div");
        body.classList.add("cartridge-table-body");

        data.forEach((element) => {
            body.appendChild(this._createRowBody(element));
        });

        this._table.appendChild(body);
    }

    _createRowBody(element) {
        let index = document.createElement("div");
        index.classList.add("cartridge-table-row-btns-index");
        index.innerText = this._indexValue;
        this._indexValue++;

        let editBtn = this._createBtnBody("edit");
        editBtn.addEventListener(configuration.getConstants().eventClick, () => {
            request.get(configuration.getInstanse().getOneAjax(element.id),
                this._renderModal,
                noteHandler.failLoad);
        });

        let deleteBtn = this._createBtnBody("delete");
        deleteBtn.addEventListener(configuration.getConstants().eventClick, () => {
            request.delete(configuration.getInstanse().getDeleteAjax(element.id),
                this._successDeteleItem,
                noteHandler.failDeleteObject);
        });

        let sectionBtns = document.createElement("div");
        sectionBtns.classList.add("cartridge-table-row-btns");
        sectionBtns.appendChild(index);
        sectionBtns.appendChild(editBtn);
        sectionBtns.appendChild(deleteBtn);


        let infoTitle = document.createElement("div");
        infoTitle.classList.add("cartridge-table-row-info-title");
        infoTitle.innerText = element.name;
        let infoCoef = document.createElement("div");
        infoCoef.classList.add("cartridge-table-row-info-footer-coef");
        infoCoef.innerText = `${i18n.nameCoef}:`;
        let infoValue = document.createElement("div");
        infoValue.classList.add("cartridge-table-row-info-footer-value");
        infoValue.innerText = element.coefToner;
        let infoToner = document.createElement("div");
        infoToner.classList.add("cartridge-table-row-info-footer-toner");
        infoToner.innerText = `${i18n.nameToner}:`;
        let infoName = document.createElement("div");
        infoName.classList.add("cartridge-table-row-info-footer-name");
        infoName.innerText = element.toner.name;
        let infoFooter = document.createElement("div");
        infoFooter.classList.add("cartridge-table-row-info-footer");
        infoFooter.appendChild(infoCoef);
        infoFooter.appendChild(infoValue);
        infoFooter.appendChild(infoToner);
        infoFooter.appendChild(infoName);
        let sectionInfo = document.createElement("div");
        sectionInfo.classList.add("cartridge-table-row-info");
        sectionInfo.appendChild(infoTitle);
        sectionInfo.appendChild(infoFooter);

        let sectionParts = document.createElement("div");
        sectionParts.classList.add("cartridge-table-row-parts");
        sectionParts.appendChild(this._createPartsItem(`${i18n.nameDrum}:`, 1, element.drum.name));
        sectionParts.appendChild(this._createPartsItem(`${i18n.nameMagneticShaft}:`, 2, element.magneticShaft.name));
        sectionParts.appendChild(this._createPartsItem(`${i18n.namePrimaryChargeShaft}:`, 3, element.primaryChargeShaft.name));
        sectionParts.appendChild(this._createPartsItem(`${i18n.nameCleaningBlade}:`, 2, element.cleaningBlade.name));
        sectionParts.appendChild(this._createPartsItem(`${i18n.nameDispensingBlade}:`, 1, element.dispensingBlade.name));

        let row = document.createElement("div");
        row.classList.add("cartridge-table-row");
        row.appendChild(sectionBtns);
        row.appendChild(sectionInfo);
        row.appendChild(sectionParts);

        return row;
    }

    _createPartsItem(name, indexColor, value){
        let partsName = document.createElement("div");
        partsName.classList.add("cartridge-table-row-parts-item-name");
        partsName.innerText = name;
        let partsValue = document.createElement("div");
        partsValue.classList.add("cartridge-table-row-parts-item-value");
        partsValue.innerText = value;
        let partsItem = document.createElement("div");
        partsItem.classList.add("cartridge-table-row-parts-item");
        partsItem.classList.add(`cartridge-table-row-parts-item-bg-${indexColor}`);
        partsItem.appendChild(partsName);
        partsItem.appendChild(partsValue);

        return partsItem;
    }

    _successDeteleItem(){
        noteHandler.successDeleteObject();
        this._refreshTable();
    }

    _createBtnBody(nameBtn){
        let imgDiv = document.createElement("div");
        imgDiv.classList.add(`cartridge-table-row-btns-${nameBtn}-img`);

        let btn = document.createElement("div");
        btn.classList.add(`cartridge-table-row-btns-${nameBtn}`);

        btn.appendChild(imgDiv);

        return btn;
    }

    _setSort(sort){
        if(sort){
            this._arrowUp.classList.remove("cartridge-table-arrows-up-active");
            this._arrowUp.classList.add("cartridge-table-arrows-up-inactive");
            this._arrowDown.classList.add("cartridge-table-arrows-down-active");
            this._arrowDown.classList.remove("cartridge-table-arrows-down-inactive");
        } else {
            this._arrowUp.classList.add("cartridge-table-arrows-up-active");
            this._arrowUp.classList.remove("cartridge-table-arrows-up-inactive");
            this._arrowDown.classList.remove("cartridge-table-arrows-down-active");
            this._arrowDown.classList.add("cartridge-table-arrows-down-inactive");
        }
    }
}