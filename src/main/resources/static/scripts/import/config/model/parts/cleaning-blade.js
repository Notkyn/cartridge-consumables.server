import { globalConfig } from "../../global";

class CleaningBladesConfig {

    constructor(){
        this._name = i18n.nameCleaningBlade;

        this._all_cleaning_blades = "/ui/parts/cleaning_blade";
    }

    getName(){
        return this._name;
    }

    getAllAjax(){
        return globalConfig.getServerUrl() + this._all_cleaning_blades;
    }

    getOneAjax(id){
        return globalConfig.getServerUrl() + this._all_cleaning_blades + "/" + id;
    }

    getDeleteAjax(id){
        return globalConfig.getServerUrl() + this._all_cleaning_blades + "/" + id;
    }

    getAddAjax(){
        return globalConfig.getServerUrl() + this._all_cleaning_blades;
    }

}

export const cleaningBladesConfig = new CleaningBladesConfig();