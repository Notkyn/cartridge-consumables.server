import { tonersConfig } from "%config%/model/parts/toners";
import { drumsConfig } from "%config%/model/parts/drums";
import { magneticShaftsConfig } from "%config%/model/parts/magnetic-shaft";
import { primaryChargeShaftsConfig } from "%config%/model/parts/primary-charge-shaft";
import { cleaningBladesConfig } from "%config%/model/parts/cleaning-blade";
import { modalMode } from "%config%/modes/modal";
import { constants } from "%config%/constants";


class FabricConfig {
    constructor(){
        this._isPartsMode = false;


        this._isToner = false;
        this._isDrum = false;
        this._isMagneticShaft = false;
        this._isPrimaryChargeShaft = false;
        this._isCleaningBlade = false;
    }

    _refreshConfig(){
        this._isPartsMode = false;

        modalMode.refresh();

        this._isToner = false;
        this._isDrum = false;
        this._isMagneticShaft = false;
        this._isPrimaryChargeShaft = false;
        this._isCleaningBlade = false;
    }

    getInstanse(){
        switch (true) {
            case this._isToner:
                return tonersConfig;
            case this._isDrum:
                return drumsConfig;
            case this._isMagneticShaft:
                return magneticShaftsConfig;
            case this._isPrimaryChargeShaft:
                return primaryChargeShaftsConfig;
            case this._isCleaningBlade:
                return cleaningBladesConfig;
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

    setMagneticShaftConfig(){
        this._refreshConfig();
        this._isMagneticShaft = true;
        this._setPartsMode(true);
    }

    setPrimaryChargeShaftConfig(){
        this._refreshConfig();
        this._isPrimaryChargeShaft = true;
        this._setPartsMode(true);
    }

    setCleaningBladeConfig(){
        this._refreshConfig();
        this._isCleaningBlade = true;
        this._setPartsMode(true);
    }
}

export const configuration = new FabricConfig();