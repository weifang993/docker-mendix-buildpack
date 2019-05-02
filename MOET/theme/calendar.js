var x =  document.getElementById("calendar-dropdown");
var i;
var option = document.createElement("option");

option.text = "Select";
x.appendChild(option);

for (i = 0; i < document.getElementsByClassName("calendar-mobile-data").length; i++) {
var option = document.createElement("option");
calendarMobileData = document.getElementsByClassName("calendar-mobile-data")[i].innerHTML;
calendarMobileData = calendarMobileData.split(","); 

option.text = calendarMobileData[0] + ', ' + calendarMobileData[1] + ' ' + calendarMobileData[2];
	if ( calendarMobileData[3] === 'Yes' ){
		option.setAttribute('disabled', '');
	}else{
		option.setAttribute('value', calendarMobileData[4]);
	}
	document.getElementById("calendar-dropdown").appendChild(option);
}

