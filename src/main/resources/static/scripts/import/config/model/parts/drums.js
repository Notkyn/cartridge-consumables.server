import { globalConfig } from "../../global";

class DrumsConfig {

    constructor(){
        this._name = i18n.nameDrum;

        this._all_drums = "/ui/parts/drum";
    }

    getName(){
        return this._name;
    }

    getAllAjax(){
        return globalConfig.getServerUrl() + this._all_drums;
    }

    getOneAjax(id){
        return globalConfig.getServerUrl() + this._all_drums + "/" + id;
    }

    getDeleteAjax(id){
        return globalConfig.getServerUrl() + this._all_drums + "/" + id;
    }

    getAddAjax(){
        return globalConfig.getServerUrl() + this._all_drums;
    }

}

export const drumsConfig = new DrumsConfig();