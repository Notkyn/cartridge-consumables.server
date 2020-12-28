import { tonersConfig } from "%config%/model/parts/toners";
import { modalMode } from "%config%/modes/modal";
import { constants } from "%config%/constants";


class FabricConfig {
    constructor(){
        this._isPartsMode = false;


        this._isToner = false;
    }

    _refreshConfig(){
        this._isPartsMode = false;

        modalMode.refresh();

        this._isToner = false;
    }

    getInstanse(){
        if(this._isToner){
            return tonersConfig;
        }
    }

    getConstants(){
        return constants;
    }

    getModalMode(){
        return modalMode;
    }

    isParts(){
        return this._isPartsMode;
    }

    _setPartsMode(value){
        this._isPartsMode = value;
    }

    setTonerConfig(){
        this._refreshConfig();
        this._isToner = true;
        this._setPartsMode(true);
    }
}

export const configuration = new FabricConfig();