import { globalConfig } from "../global";

class TonersConfig {

    constructor(){
        this._all_toners = "ui/parts/toner/";
    }

    all(){
        return globalConfig.getServerUrl() + this._all_toners;
    }

    delete(id){
        return globalConfig.getServerUrl() + this._all_toners + id;
    }
}

export const tonersConfig = new TonersConfig();