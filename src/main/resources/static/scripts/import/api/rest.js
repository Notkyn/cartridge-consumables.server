class Rest{
    constructor(){
        this._contentType = "application/x-www-form-urlencoded; charset=UTF-8";
        this._contentTypeJson = "application/json;charset=utf-8";

    }

    get(url, someDo){
        fetch(url, {
            method: 'get',
            headers: {
                "Content-type": this._contentType
            }
        })
            .then(this._status)
            .then(this._json)
            .then(someDo)
            .catch(function (error) {
                console.log('Request failed', error);
            });
    }

    post(url, data, someDo){
        fetch(url, {
            method: 'post',
            headers: {
                "Content-type": this._contentTypeJson
            },
            body: JSON.stringify(data)
        })
            .then(this._status)
            .then(someDo)
            .catch(function (error) {
                console.log('Request failed', error);
            });
    }

    delete(url, someDo){
        fetch(url, {
            method: 'delete',
            headers: {
                "Content-type": this._contentType
            }
        })
            .then(this._status)
            .then(someDo)
            .catch(function (error) {
                console.log('Request failed', error);
            });
    }

    _json(response){
        return response.json();
    }

    _status(response) {
        if (response.status >= 200 && response.status < 300) {
            return Promise.resolve(response);
        } else {
            return Promise.reject(new Error(response.statusText));
        }
    }
}

export const request = new Rest();