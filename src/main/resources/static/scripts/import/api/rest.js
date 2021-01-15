class Rest{
    constructor(){
        this._contentType = "application/x-www-form-urlencoded; charset=UTF-8";
        this._contentTypeJson = "application/json;charset=utf-8";

    }

    get(url, succesDo, failDo){
        fetch(url, {
            method: 'get',
            headers: {
                "Content-type": this._contentType
            }
        })
            .then(this._status)
            .then(this._json)
            .then(succesDo)
            .catch(failResponse => this._error(failResponse, failDo));
    }

    post(url, data, succesDo, failDo){
        fetch(url, {
            method: 'post',
            headers: {
                "Content-type": this._contentTypeJson
            },
            body: JSON.stringify(data)
        })
            .then(this._status)
            .then(succesDo)
            .catch(failResponse => this._error(failResponse, failDo));
    }

    delete(url, succesDo, failDo){
        fetch(url, {
            method: 'delete',
            headers: {
                "Content-type": this._contentType
            }
        })
            .then(this._status)
            .then(succesDo)
            .catch(failResponse => this._error(failResponse, failDo));
    }

    test(url, data, succesDo, failDo){
        fetch(url, {
            method: 'post',
            headers: {
                "Content-type": this._contentTypeJson
            },
            body: JSON.stringify(data)
        })
            .then(this._status)
            .then(succesDo)
            .catch(failResponse => this._error(failResponse, failDo));
    }

    _json(response){
        return response.json();
    }

    _status(response) {
        if (response.status >= 200 && response.status < 300) {
            return Promise.resolve(response);
        } else {
            return Promise.reject(failResponse);
        }
    }

    _error(failResponse, failDo){
        failResponse
            .text()
            .then(textBody => JSON.parse(textBody))
            .then(failDo);
    }
}

export const request = new Rest();