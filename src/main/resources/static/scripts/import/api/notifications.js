export class Note{
    constructor(){
        this._rootView = document.querySelector("body");
    }

    show(){
        console.log(this._rootView);
    }
}