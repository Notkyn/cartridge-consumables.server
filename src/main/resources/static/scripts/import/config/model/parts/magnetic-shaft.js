import { globalConfig } from "../../global";

class MagneticShaftsConfig {

    constructor(){
        this._name = i18n.nameMagneticShaft;

        this._all_magnetic_shafts = "/ui/parts/magnetic_shaft";
    }

    getName(){
        return this._name;
    }

    getAllAjax(){
        return globalConfig.getServerUrl() + this._all_magnetic_shafts;
    }

    getOneAjax(id){
        return globalConfig.getServerUrl() + this._all_magnetic_shafts + "/" + id;
    }

    getDeleteAjax(id){
        return globalConfig.getServerUrl() + this._all_magnetic_shafts + "/" + id;
    }

    getAddAjax(){
        return globalConfig.getServerUrl() + this._all_magnetic_shafts;
    }
}

export const magneticShaftsConfig = new MagneticShaftsConfig();