import { globalConfig } from "../global";

class CartridgesConfig {

    constructor(){
        this._name = i18n.nameCartridge;

        this._all_cartridges = "/ui/cartridge";
    }

    getName(){
        return this._name;
    }

    getAllAjax(){
        return globalConfig.getServerUrl() + this._all_cartridges;
    }

    getOneAjax(id){
        return globalConfig.getServerUrl() + this._all_cartridges + "/" + id;
    }

    getDeleteAjax(id){
        return globalConfig.getServerUrl() + this._all_cartridges + "/" + id;
    }

    getAddAjax(){
        return globalConfig.getServerUrl() + this._all_cartridges;
    }

}

export const cartridgesConfig = new CartridgesConfig();