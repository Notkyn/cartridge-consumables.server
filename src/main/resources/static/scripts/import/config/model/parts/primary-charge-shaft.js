import { globalConfig } from "../../global";

class PrimaryChargeShaftsConfig {

    constructor(){
        this._name = i18n.namePrimaryChargeShaft;

        this._all_primary_charge_shafts = "/ui/parts/primary_charge_shaft";
    }

    getName(){
        return this._name;
    }

    getAllAjax(){
        return globalConfig.getServerUrl() + this._all_primary_charge_shafts;
    }

    getOneAjax(id){
        return globalConfig.getServerUrl() + this._all_primary_charge_shafts + "/" + id;
    }

    getDeleteAjax(id){
        return globalConfig.getServerUrl() + this._all_primary_charge_shafts + "/" + id;
    }

    getAddAjax(){
        return globalConfig.getServerUrl() + this._all_primary_charge_shafts;
    }
}

export const primaryChargeShaftsConfig = new PrimaryChargeShaftsConfig();