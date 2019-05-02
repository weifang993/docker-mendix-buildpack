var x =  document.getElementById("calendar-dropdown");

var i;
var option = document.createElement("option");
option.text = "Select";
x.appendChild(option);

for (i = 0; i < document.getElementsByClassName("calendar-mobile-data").length; i++) {
var option = document.createElement("option");
calendarMobileData = document.getElementsByClassName("calendar-mobile-data")[i].innerHTML;
calendarMobileData = calendarMobileData.split(","); 
console.log('Day of Week: ' + calendarMobileData[0] + ', Month: ' + calendarMobileData[1] + ', Date: ' + calendarMobileData[2] + ', Holiday: ' + calendarMobileData[3] + ', Value: ' + calendarMobileData[4]);

option.text = calendarMobileData[0] + ', ' + calendarMobileData[1] + ' ' + calendarMobileData[2];
/*option.text = option.text + ', ' + document.getElementsByClassName("date")[i].innerHTML;
option.text = option.text + ' ' + document.getElementsByClassName("month")[i].innerHTML;*/
//console.log(i + ': ' + option.text);

	if ( calendarMobileData[3] === 'Yes' ){
		option.setAttribute('disabled', '');
		option.setAttribute('value', calendarMobileData[4]);
	}else{
		option.setAttribute('value', calendarMobileData[4]);
	}
	
	document.getElementById("calendar-dropdown").appendChild(option);
}

