export class Overflow {
    constructor(){
        this._overflow = document.querySelector(".overflow");
    }

    show(){
        this._overflow.classList.remove("hide");
    }

    hide(){
        this._overflow.classList.add("hide");
    }
}