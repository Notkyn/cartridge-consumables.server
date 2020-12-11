export class Sort {

    byNameAsc(data){
        data.sort((a, b) => {
            if(a.name < b.name) { return -1; }
            if(a.name > b.name) { return 1; }
            return 0;
        });
    }

    byNameDesc(data){
        data.sort((a, b) => {
            if(a.name > b.name) { return -1; }
            if(a.name < b.name) { return 1; }
            return 0;
        });
    }

}

export const sort = new Sort();