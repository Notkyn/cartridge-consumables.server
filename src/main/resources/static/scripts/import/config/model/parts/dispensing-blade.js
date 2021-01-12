import { globalConfig } from "../../global";

class DispensingBladesConfig {

    constructor(){
        this._name = i18n.nameDispensingBlade;

        this._all_dispensing_blades = "/ui/parts/dispensing_blade";
    }

    getName(){
        return this._name;
    }

    getAllAjax(){
        return globalConfig.getServerUrl() + this._all_dispensing_blades;
    }

    getOneAjax(id){
        return globalConfig.getServerUrl() + this._all_dispensing_blades + "/" + id;
    }

    getDeleteAjax(id){
        return globalConfig.getServerUrl() + this._all_dispensing_blades + "/" + id;
    }

    getAddAjax(){
        return globalConfig.getServerUrl() + this._all_dispensing_blades;
    }

}

export const dispensingBladesConfig = new DispensingBladesConfig();