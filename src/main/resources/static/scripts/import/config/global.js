class GlobalConfig{

    constructor(){
        this._server = "http://localhost:8080/";
        this._context = "consumables/";
    }

    getServerUrl(){
        return this._server + this._context;
    }
}

export const globalConfig = new GlobalConfig();