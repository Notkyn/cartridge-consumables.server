import { globalConfig } from "../../global";

class TonersConfig {

    constructor(){
        this._name = i18n.nameToner;

        this._all_toners = "/ui/parts/toner";
    }

    getName(){
        return this._name;
    }

    getAllAjax(){
        return globalConfig.getServerUrl() + this._all_toners;
    }

    getDeleteAjax(id){
        return globalConfig.getServerUrl() + this._all_toners + "/" + id;
    }

    getAddAjax(){
        return globalConfig.getServerUrl() + this._all_toners;
    }

}

export const tonersConfig = new TonersConfig();