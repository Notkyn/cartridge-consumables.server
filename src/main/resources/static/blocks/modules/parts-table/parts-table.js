import { request } from '%api%/rest';
import { sort } from "%api%/sort";

export class PartsTable {
    constructor(classContentContainer) {
        this._container = document.querySelector(`div.${classContentContainer}`);
        this._table = this._container.querySelector(".parts-table");
        this._body = this._table.querySelector(".parts-table-body");
        this._indexValue = 1;

        this._nameColumn = this._table.querySelector(".parts-table-header-name");
        this._arrows = this._nameColumn.querySelector(".parts-table-arrows");
        this._arrowUp = this._arrows.querySelector(".parts-table-arrows-up");
        this._arrowDown = this._arrows.querySelector(".parts-table-arrows-down");
        this._switchSort = true;

        this._renderTable = this._renderTable.bind(this);
        this._refreshTable = this._refreshTable.bind(this);
        this._sortByName = this._sortByName.bind(this);

        this._ajaxUrl = null;
    }

    aplly(){
        this._refreshTable();
        this._refreshSort();
    }

    setAjaxConfig(ajaxUrl){
        this._ajaxUrl = ajaxUrl;
    }

    _refreshTable() {
        this._indexValue = 1;
        while (this._body.firstChild) {
            this._body.removeChild(this._body.firstChild);
        }

        request.get(this._ajaxUrl.all(), this._renderTable);
    }

    _renderTable(data) {
        this._sortByName(data);

        data.forEach((element) => {
            this._addRow(element);
        });
    }

    _addRow(element) {
        let row = this._createRow();
        row.appendChild(this._createIndexDiv());
        row.appendChild(this._createNameDiv(element.name));

        let editBtn = this._createBtn("edit");
        editBtn.addEventListener("click", () => {
            console.log("ID: " + element.id + " - EDIT!");
        });
        row.appendChild(editBtn);

        let deleteBtn = this._createBtn("delete");
        deleteBtn.addEventListener("click", () => {
            request.delete(this._ajaxUrl.delete(element.id), this._refreshTable);
        });
        row.appendChild(deleteBtn);

        this._body.appendChild(row);
    }

    _createRow(){
        let divRow = document.createElement("div");
        divRow.classList.add("parts-table-row");
        if(this._indexValue % 2 === 0) {
            divRow.classList.add("odd");
        } else {
            divRow.classList.add("even");
        }
        return divRow;
    }

    _createIndexDiv() {
        let index = document.createElement("div");
        index.classList.add("parts-table-row-index");
        index.innerText = this._indexValue;
        this._indexValue++;

        return index;
    }

    _createNameDiv(name){
        let nameDiv = document.createElement("div");
        nameDiv.classList.add("parts-table-row-name");
        nameDiv.innerText = name;
        return nameDiv;
    }

    _createBtn(nameBtn){
        let imgDiv = document.createElement("div");
        imgDiv.classList.add(`parts-table-row-${nameBtn}-img`);

        let btn = document.createElement("div");
        btn.classList.add(`parts-table-row-${nameBtn}`);

        btn.appendChild(imgDiv);

        return btn;
    }

    _refreshSort(){

        this._nameColumn.addEventListener("click", () => {
            this._switchSort = !this._switchSort;

            this._setSort(this._switchSort);
            this._refreshTable();
        });

        this._switchSort = true;
        this._arrowUp.classList.remove("parts-table-arrows-up-active");
        this._arrowUp.classList.remove("parts-table-arrows-up-inactive");
        this._arrowDown.classList.remove("parts-table-arrows-down-active");
        this._arrowDown.classList.remove("parts-table-arrows-down-inactive");
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

    _sortByName(data){
        if(this._switchSort){
            sort.byNameAsc(data);
        } else {
            sort.byNameDesc(data);
        }
    }
}