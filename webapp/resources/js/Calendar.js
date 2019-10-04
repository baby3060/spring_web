let calArr = new Array();

let Calendar = {
    _target : '',

    __MakeCalendar : function() {
        let calDiv = document.createElement("div");
        let calContent = document.createTextNode("Hello");
        calDiv.appendChild(calContent);
        
        document.body.appendChild(calDiv);
    }
};

function _Calendar_On(t) {
    if( findTarget(t) ) {
        
    } else {
        let cal = new Calendar();
        cal._target = t;
        calArr.push(cal);
    }
}

function findTarget(t) {
    if( carArr.length === 0 ) {
        return false;
    } else {
        // target을 찾음. 결과가 있다면 이미 있다는 의미. 없다면 문제없음
        const result = calArr.find(cal => cal._target === t);
        return (result === null);
    }
}