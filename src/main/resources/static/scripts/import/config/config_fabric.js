import { tonersConfig } from "%config%/model/parts/toners";
import { drumsConfig } from "%config%/model/parts/drums";
import { modalMode } from "%config%/modes/modal";
import { constants } from "%config%/constants";


class FabricConfig {
    constructor(){
        this._isPartsMode = false;


        this._isToner = false;
        this._isDrum = false;
    }

    _refreshConfig(){
        this._isPartsMode = false;

        modalMode.refresh();

        this._isToner = false;
        this._isDrum = false;
    }

    getInstanse(){
        switch (true) {
            case this._isToner:
                return tonersConfig;
            case this._isDrum:
                return drumsConfig;
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

    setDrumConfig(){
        this._refreshConfig();
        this._isDrum = true;
        this._setPartsMode(true);
    }
}

export const configuration = new FabricConfig();