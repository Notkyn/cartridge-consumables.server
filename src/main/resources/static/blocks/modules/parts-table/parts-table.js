import { request } from '%api%/rest';
import { configuration } from "%config%/config_fabric";
import { sort } from "%api%/sort";

export class PartsTable {

    constructor(classContentContainer) {
        this._classContainer = classContentContainer;
        this._switchSort = true;
        this._indexValue = 1;

        this._createBody = this._createBody.bind(this);
        this._refreshTable = this._refreshTable.bind(this);
        this._addOrUpdateItem = this._addOrUpdateItem.bind(this);
    }

    build(modal){
        this._container = document.querySelector(`div.${this._classContainer}`);
        this._table = this._container.querySelector(".parts-table");

        this._modal = modal;
        this._modal.setTable(this._table);
        this._table.addEventListener(configuration.getConstants().eventModalAddBtn, this._addOrUpdateItem);

        this._refreshTable();
    }

    _refreshTable(){
        this._clearTable();

        this._table.appendChild(this._createHeader());

        this._renderBody();
    }

    _addOrUpdateItem(){
        request.post(configuration.getInstanse().getAddAjax(), this._modal.getData(), this._refreshTable);
    }

    _clearTable(){
        while(this._table.firstChild){
            this._table.removeChild(this._table.firstChild);
        }
    }

    _createHeader(){
        let indexSection = document.createElement("div");
        indexSection.classList.add("parts-table-header-index");
        indexSection.innerText = "#";
        let editSection = document.createElement("div");
        editSection.classList.add("parts-table-header-edit");
        let deleteSection = document.createElement("div");
        deleteSection.classList.add("parts-table-header-delete");

        let arrowUp = document.createElement("div");
        arrowUp.classList.add("parts-table-arrows-up");
        this._arrowUp = arrowUp;
        let arrowDown = document.createElement("div");
        arrowDown.classList.add("parts-table-arrows-down");
        this._arrowDown = arrowDown;

        let arrows = document.createElement("div");
        arrows.classList.add("parts-table-arrows");
        arrows.appendChild(arrowUp);
        arrows.appendChild(arrowDown);

        let name = document.createElement("span");
        name.innerText = i18n.nameName;

        let nameSection = document.createElement("div");
        nameSection.classList.add("parts-table-header-name");
        nameSection.appendChild(arrows);
        nameSection.appendChild(name);
        nameSection.addEventListener("click", () => {
            this._switchSort = !this._switchSort;

            this._refreshTable();
            this._setSort(this._switchSort);
        });

        let header = document.createElement("div");
        header.classList.add("parts-table-header");
        header.appendChild(indexSection);
        header.appendChild(nameSection);
        header.appendChild(editSection);
        header.appendChild(deleteSection);

        return header;
    }

    _renderBody(){
        request.get(configuration.getInstanse().getAllAjax(), this._createBody);
    }

    _createBody(data) {
        this._indexValue = 1;

        if(this._switchSort){
            sort.byNameAsc(data);
        } else {
            sort.byNameDesc(data);
        }

        let body = document.createElement("div");
        body.classList.add("parts-table-body");

        data.forEach((element) => {
            body.appendChild(this._crateRowBody(element));
        });

        this._table.appendChild(body);
    }

    _crateRowBody(element) {
        let index = document.createElement("div");
        index.classList.add("parts-table-row-index");
        index.innerText = this._indexValue;
        this._indexValue++;

        let name = document.createElement("div");
        name.classList.add("parts-table-row-name");
        name.innerText = element.name;

        let editBtn = this._createBtnBody("edit");
        editBtn.addEventListener(configuration.getConstants().eventClick, () => {
            console.log("ID: " + element.id + " - EDIT!");
        });

        let deleteBtn = this._createBtnBody("delete");
        deleteBtn.addEventListener(configuration.getConstants().eventClick, () => {
            request.delete(configuration.getInstanse().getDeleteAjax(element.id), this._refreshTable);
        });

        let row = document.createElement("div");
        row.classList.add("parts-table-row");
        if(this._indexValue % 2 === 0) {
            row.classList.add("odd");
        } else {
            row.classList.add("even");
        }
        row.appendChild(index);
        row.appendChild(name);
        row.appendChild(editBtn);
        row.appendChild(deleteBtn);

        return row;
    }

    _createBtnBody(nameBtn){
        let imgDiv = document.createElement("div");
        imgDiv.classList.add(`parts-table-row-${nameBtn}-img`);

        let btn = document.createElement("div");
        btn.classList.add(`parts-table-row-${nameBtn}`);

        btn.appendChild(imgDiv);

        return btn;
    }

    _setSort(sort){
        if(sort){
            this._arrowUp.classList.remove("parts-table-arrows-up-active");
            this._arrowUp.classList.add("parts-table-arrows-up-inactive");
            this._arrowDown.classList.add("parts-table-arrows-down-active");
            this._arrowDown.classList.remove("parts-table-arrows-down-inactive");
        } else {
            this._arrowUp.classList.add("parts-table-arrows-up-active");
            this._arrowUp.classList.remove("parts-table-arrows-up-inactive");
            this._arrowDown.classList.remove("parts-table-arrows-down-active");
            this._arrowDown.classList.add("parts-table-arrows-down-inactive");
        }
    }
}