import { globalConfig } from "../global";

class DepartmentsConfig {

    constructor(){
        this._name = i18n.nameDepartment;

        this._all_departments = "/ui/department";
    }

    getName(){
        return this._name;
    }

    getAllAjax(){
        return globalConfig.getServerUrl() + this._all_departments;
    }

    getOneAjax(id){
        return globalConfig.getServerUrl() + this._all_departments + "/" + id;
    }

    getDeleteAjax(id){
        return globalConfig.getServerUrl() + this._all_departments + "/" + id;
    }

    getAddAjax(){
        return globalConfig.getServerUrl() + this._all_departments;
    }

}

export const departmentsConfig = new DepartmentsConfig();