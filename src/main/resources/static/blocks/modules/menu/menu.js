class Menu {
    constructor(className = 'menu'){
        this.className = className;
        this._menu = document.querySelector(`.${className}`);
        this._menu_items = this._menu.querySelectorAll(`.${className}-item:not(.${className}-group)`);
        this._menu_subitems = this._menu.querySelectorAll(`.${className}-subitem`);
        this._page = document.querySelector('div[data-page]').getAttribute('data-page');
        this._active_item = this._menu.querySelector(`[data-page="${this._page}"]`);
    }


    apply(){
        this._refreshAllItem();
        this._refreshAllSubitem();

        this._setActiveItem(this._active_item, true);
    }

    _setActiveItem(element, key){
        if(key){
            element.classList.add(`${element.getAttribute("class")}__active`);
        } else {
            element.classList.remove(`${element.getAttribute("class")}__active`);
        }
    }

    _refreshAllItem(){
        this._menu_items.forEach((element) => {
            this._setActiveItem(element, false);
        });
    }

    _refreshAllSubitem(){
        this._menu_subitems.forEach((element) => {
            this._setActiveItem(element, false);
        });
    }
}

const menu = new Menu('menu');
menu.apply();