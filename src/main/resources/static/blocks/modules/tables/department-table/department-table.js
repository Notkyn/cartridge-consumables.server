import { request } from '%api%/rest';
import { configuration } from "%config%/config_fabric";
import { sort } from "%api%/sort";
import { noteHandler } from "%api%/note-handler";

export class DepartmentsTable {

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
        this._table = this._container.querySelector(".department-table");

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
        while(this._table.firstChild){
            this._table.removeChild(this._table.firstChild);
        }

        this._table.appendChild(this._createHeader());

        this._renderBody();
    }

    _createHeader(){
        let indexSection = document.createElement("div");
        indexSection.classList.add("department-table-header-index");
        indexSection.innerText = "#";

        let cartridgeSection = document.createElement("div");
        cartridgeSection.classList.add("department-table-header-cartridge");
        cartridgeSection.innerText = i18n.nameCartridge;

        let arrowUp = document.createElement("div");
        arrowUp.classList.add("department-table-arrows-up");
        this._arrowUp = arrowUp;
        let arrowDown = document.createElement("div");
        arrowDown.classList.add("department-table-arrows-down");
        this._arrowDown = arrowDown;

        let arrows = document.createElement("div");
        arrows.classList.add("department-table-arrows");
        arrows.appendChild(arrowUp);
        arrows.appendChild(arrowDown);

        let name = document.createElement("span");
        name.innerText = i18n.nameName;

        let nameSection = document.createElement("div");
        nameSection.classList.add("department-table-header-name");
        nameSection.appendChild(arrows);
        nameSection.appendChild(name);
        nameSection.addEventListener("click", () => {
            this._switchSort = !this._switchSort;

            this._refreshTable();
            this._setSort(this._switchSort);
        });

        let editSection = document.createElement("div");
        editSection.classList.add("department-table-header-edit");
        let deleteSection = document.createElement("div");
        deleteSection.classList.add("department-table-header-delete");

        let header = document.createElement("div");
        header.classList.add("department-table-header");
        header.appendChild(indexSection);
        header.appendChild(nameSection);
        header.appendChild(cartridgeSection);
        header.appendChild(editSection);
        header.appendChild(deleteSection);

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
        body.classList.add("department-table-body");

        data.forEach((element) => {
            body.appendChild(this._createRowBody(element));
        });

        this._table.appendChild(body);
    }

    _createRowBody(element) {
        let index = document.createElement("div");
        index.classList.add("department-table-row-index");
        index.innerText = this._indexValue;
        this._indexValue++;

        let name = document.createElement("div");
        name.classList.add("department-table-row-name");
        name.innerText = element.name;

        let cartridge = document.createElement("div");
        cartridge.classList.add("department-table-row-cartridge");
        cartridge.innerText = element.cartridge.name;

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

        let row = document.createElement("div");
        row.classList.add("department-table-row");
        if(this._indexValue % 2 === 0) {
            row.classList.add("department-table-row_odd");
        } else {
            row.classList.add("department-table-row_even");
        }

        row.appendChild(index);
        row.appendChild(name);
        row.appendChild(cartridge);
        row.appendChild(editBtn);
        row.appendChild(deleteBtn);

        return row;
    }

    _successDeteleItem(){
        noteHandler.successDeleteObject();
        this._refreshTable();
    }

    _createBtnBody(nameBtn){
        let imgDiv = document.createElement("div");
        imgDiv.classList.add(`department-table-row-${nameBtn}-img`);

        let btn = document.createElement("div");
        btn.classList.add(`department-table-row-${nameBtn}`);

        btn.appendChild(imgDiv);

        return btn;
    }

    _setSort(sort){
        if(sort){
            this._arrowUp.classList.remove("department-table-arrows-up-active");
            this._arrowUp.classList.add("department-table-arrows-up-inactive");
            this._arrowDown.classList.add("department-table-arrows-down-active");
            this._arrowDown.classList.remove("department-table-arrows-down-inactive");
        } else {
            this._arrowUp.classList.add("department-table-arrows-up-active");
            this._arrowUp.classList.remove("department-table-arrows-up-inactive");
            this._arrowDown.classList.remove("department-table-arrows-down-active");
            this._arrowDown.classList.add("department-table-arrows-down-inactive");
        }
    }
}