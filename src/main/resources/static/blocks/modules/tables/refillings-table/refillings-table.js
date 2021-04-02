import { request } from '%api%/rest';
import { configuration } from "%config%/config_fabric";
import { dateHelper } from "%api%/date-helper";
import { noteHandler } from "%api%/note-handler";

export class RefillingsTable {

    constructor(classContentContainer) {
        this._classContainer = classContentContainer;

        this._refreshTable = this._refreshTable.bind(this);
        this._createBody = this._createBody.bind(this);
        this._renderModal = this._renderModal.bind(this);
    }

    build(modal){
        this._container = document.querySelector(`div.${this._classContainer}`);
        this._table = this._container.querySelector(".refillings-table");

        this._modal = modal;
        this._modal.setTable(this._table);

        this._table.addEventListener(configuration.getConstants().eventModalAddBtn, this._refreshTable);

        this._refreshTable();
    }

    _renderBody(){
        request.get(configuration.getInstanse().getAllDatesAjax(),
            this._createBody,
            noteHandler.failLoad);
    }

    _renderModal(data){
        configuration.getModalMode().setEditModalMode();
        this._modal.show(data);
    }

    _refreshTable(){
        this._clearTable();

        // this._table.appendChild(this._createHeader());

        this._renderBody();
    }

    _clearTable(){
        while(this._table.firstChild){
            this._table.removeChild(this._table.firstChild);
        }
    }

    _createBody(data) {
        dateHelper.setDates(data);
        dateHelper.getYears().forEach(item => {
            dateHelper.setYear(item);
            this._table.appendChild(this._createRowBody(item));
        });
    }

    _createRowBody(year) {
        let yearTitle = document.createElement("div");
        yearTitle.classList.add("refillings-table-row-year-title");
        yearTitle.innerText = year;
        let yearRow = document.createElement("div");
        yearRow.classList.add("refillings-table-row-year");
        yearRow.appendChild(yearTitle);

        let contentSlider = document.createElement("div");
        contentSlider.classList.add("refillings-table-row-slider-content");
        dateHelper.getMonthsByYear().forEach(item => {
            contentSlider.appendChild(this._createMonth(item));
        });
        let sliderRow = document.createElement("div");
        sliderRow.classList.add("refillings-table-row-slider");
        sliderRow.appendChild(this._createArrow("previous"));
        sliderRow.appendChild(contentSlider);
        sliderRow.appendChild(this._createArrow("next"));

        let row = document.createElement("div");
        row.classList.add("refillings-table-row");
        row.appendChild(yearRow);
        row.appendChild(sliderRow);

        return row;
    }

    _createArrow(arrowName){
        let arrow = document.createElement("div");
        arrow.classList.add(`refillings-table-row-slider-arrow-${arrowName}`);
        let arrowContainer = document.createElement("div");
        arrowContainer.classList.add("refillings-table-row-slider-arrow");
        arrowContainer.appendChild(arrow);

        return arrowContainer;
    }

    _createMonth(monthNumber){
        let monthTitle = document.createElement("div");
        monthTitle.classList.add("refillings-table-row-month-title");
        monthTitle.innerText = i18n.months[monthNumber];

        let days = document.createElement("div");
        days.classList.add("refillings-table-row-month-days");
        dateHelper.getDaysByYearAndMonth(monthNumber).forEach(item => {
            let day = document.createElement("div");
            day.classList.add("refillings-table-row-month-days-item");
            day.innerText = item;
            day.addEventListener(configuration.getConstants().eventClick, () => {
                console.log(dateHelper.getFormatDate(monthNumber, item));
                // !!!!!
            });
            days.appendChild(day);
        });

        let month = document.createElement("div");
        month.classList.add("refillings-table-row-month");
        month.appendChild(monthTitle);
        month.appendChild(days);

        return month;
    }
}