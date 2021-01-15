export class Note{
    constructor(delay = 2000){
        this._rootView = document.querySelector("body");
        this._view = this._createView();
        this._delay = delay;

        this._hideView = this._hideView.bind(this);
    }

    success(msg, info){
        this._view.classList.remove("note_fail");

        this._show(msg, info);
    }

    fail(msg, info){
        this._view.classList.add("note_fail");

        this._show(msg, info);
    }

    _show(msg, info){
        this._clearView();

        this._createInnerDiv("msg", msg);
        this._createInnerDiv("info", info);

        this._rootView.appendChild(this._view);

        setTimeout(this._hideView, this._delay);
    }

    _hideView(){
        this._rootView.removeChild(this._view);
    }

    _createView(){
        let view = document.createElement("div");
        view.classList.add("note");

        return view;
    }

    _clearView(){
        while(this._view.firstChild){
            this._view.removeChild(this._view.firstChild);
        }
    }

    _createInnerDiv(className, msg){
        if(msg != undefined) {
            let view = document.createElement("div");
            view.classList.add(`note-${className}`);
            view.innerText = msg;
            this._view.appendChild(view);
        }
    }
}