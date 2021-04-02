import { globalConfig } from "../global";

class RefillingsConfig {

    constructor(){
        this._all_cartridges = "/ui/refill_cartridges";
        this._all_dates = "/dates";
        this._filter = "/filter";
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

    getAllDatesAjax(){
        return globalConfig.getServerUrl() + this._all_cartridges + this._all_dates;
    }

    getFilterRefillingsByDateAjax(date){
        return globalConfig.getServerUrl() + this._all_cartridges + this._filter + "&date=" + date;
    }
}

export const refillingsConfig = new RefillingsConfig();