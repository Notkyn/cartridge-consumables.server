class Menu {
    constructor(className = 'menu'){
        this.className = className;
        this._menu = document.querySelector(`.${className}`);
        this._menu_items = this._menu.querySelectorAll(`.${className}-item:not(.${className}-group)`);
        this._menu_subitems = this._menu.querySelectorAll(`.${className}-subitem`);
    }


    apply(){
        this._refreshAllItem();
        this._refreshAllSubitem();

        this._setActiveItem(this._menu_items[0], true);

        this._menu_items.forEach((element) => {
            element.addEventListener('click', () => {
                this._refreshAllItem();
                this._refreshAllSubitem();
                this._setActiveItem(element, true);
            });
        });

        this._menu_subitems.forEach((element) => {
            element.addEventListener('click', () => {
                this._refreshAllItem();
                this._refreshAllSubitem();
                this._setActiveSubItem(element, true);
            });
        });
    }

    _setActiveItem(element, key){
        if(key){
            element.classList.add(`${this.className}-item__active`);
        } else {
            element.classList.remove(`${this.className}-item__active`);
        }
    }

    _setActiveSubItem(element, key){
        if(key){
            element.classList.add(`${this.className}-subitem__active`);
        } else {
            element.classList.remove(`${this.className}-subitem__active`);
        }
    }

    _refreshAllItem(){
        this._menu_items.forEach((element) => {
            this._setActiveItem(element, false);
        });
    }

    _refreshAllSubitem(){
        this._menu_subitems.forEach((element) => {
            this._setActiveSubItem(element, false);
        });
    }
}

const menu = new Menu('menu');
menu.apply();