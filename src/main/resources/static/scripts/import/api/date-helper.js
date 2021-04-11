class DateHelper {
    constructor(){
    }

    setDates(data){
        this._dates = data;
    }

    setYear(year){
        this._year = year;
    }

    getFormatDate(month, day){
        let tempMonth = month + 1;
        if(tempMonth < 10){
            tempMonth = `0${tempMonth}`;
        }
        let tempDay = day;
        if(tempDay < 10){
            tempDay = `0${day}`;
        }

        return `${this._year}-${tempMonth}-${tempDay}`;
    }

    getYears(){
        let years = this._dates.map(item => new Date(item).getFullYear());
        years = Array.from(new Set(years)).sort();

        return years;
    }

    getMonthsByYear(){
        let months = this._dates.map(item => new Date(item))
            .filter(item => item.getFullYear() === this._year)
            .map(item => item.getMonth());
        months = Array.from(new Set(months)).sort();

        return months;
    }

    getDaysByYearAndMonth(month){
        let days = this._dates.map(item => new Date(item))
            .filter(item => item.getFullYear() === this._year && item.getMonth() === month)
            .map(item => item.getDate());
        days = Array.from(new Set(days)).sort();

        return days;
    }

    getFormatCurrentDate(){
        let currentDate = new Date();

        let tempYear = currentDate.getFullYear();

        let tempMonth = currentDate.getMonth() + 1;
        if(tempMonth < 10){
            tempMonth = `0${tempMonth}`;
        }
        let tempDay = currentDate.getDate();
        if(tempDay < 10){
            tempDay = `0${tempDay}`;
        }

        return `${tempDay}-${tempMonth}-${tempYear}`;
    }
}

export const dateHelper = new DateHelper();