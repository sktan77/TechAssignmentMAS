$(document).ready(function(){	
	$( "input.monthPicker" ).datepicker({ 
		dateFormat: "yy-mm",				
		showOn: "both",
		buttonImage: "resources/css/plugin/images/calendar.png",
		buttonImageOnly: true,
		numberOfMonths: 1,		
		monthNamesShort: [ "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" ],
		changeMonth: true,
        changeYear: true,
        showButtonPanel: true,
		maxDate: new Date,
		yearRange: "-20:+0",
		
		onClose: function(dateText, inst) {
            var month = $("#ui-datepicker-div .ui-datepicker-month :selected").val();
            var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
            $(this).val($.datepicker.formatDate('yy-mm', new Date(year, month, 1)));
        }
	});
	
	$("input.monthPicker").focus(function () {
        $(".ui-datepicker-calendar").hide();
        $("#ui-datepicker-div").position({
            my: "center top",
            at: "center bottom",
            of: $(this)
        });
    });
	
	alternateTableRowBackground(".tableAltColor tr", "altBackgroundColor", 6, 3);
	
	$( "#submitRequest" ).submit(function( event ) {
		var dateFromEntered = $.trim($('#fromDate').val());
		var dateToEntered = $.trim($('#toDate').val());
		
		if (dateToEntered.length == 0) {
			dateToEntered = dateFromEntered;
		}
		
		if (checkDate(dateFromEntered) &&
			checkDate(dateToEntered)) {
			retrieveDataFromMas(dateFromEntered, dateToEntered);						
		} else {
			$('#searchError').text('Please check the date is entered correctly');
			$('#searchError').show();
			event.preventDefault();
		}
	});	
});

function alternateTableRowBackground(tableElements, className, startingTr, numberOfTrSet) {
	var startAlt = false;
	var altNum = 0;
	for (i=1; i<=$(tableElements).length; i++) {		
		// start with 6 / multiple of 3
		if (i%numberOfTrSet == 0 && i >= startingTr) {	
			startAlt = true;
			// reset if previous set is set
			if (altNum == numberOfTrSet) {
				altNum = 0;
				startAlt = false;
			}
		} 
		
		if (startAlt) {
			// set alternate
			console.log($(tableElements).eq(i-1).addClass(className));
			altNum++;
		}		
	}
}

function retrieveDataFromMas(dateFrom, dateTo) {
	var data = {
		"resource_id":'5f2b18a8-0883-4769-a635-879c63d3caac',//the resource id
		"fields": 'end_of_month,banks_fixed_deposits_3m,banks_fixed_deposits_6m,banks_fixed_deposits_12m,banks_savings_deposits,fc_fixed_deposits_3m,fc_fixed_deposits_6m,fc_fixed_deposits_12m,fc_savings_deposits',
		"between[end_of_month]": dateFrom + "," + dateTo,
		"sort": 'end_of_month desc'
	};	
	
	$.ajax({
		url: 'https://eservices.mas.gov.sg/api/action/datastore/search.json',
		data:data,
		dataType:'json',
		async: false,
		success:function(data){			
			console.log("success");		
			
			var recordConcatenate = "";
			for (i=0; i<data.result.records.length; i++) {
				if (i != 0) {
					recordConcatenate += "@";
				}
				recordConcatenate += JSON.stringify(data.result.records[i]);
			}
			$('#masJsonResponse').val(recordConcatenate);
		},
		error:function(data) {
			console.log("Error " + data);
		}
	});	
	
	
}

function checkDate(dateFormat) {	
	if (dateFormat.length != 7) return false;
	
	var yearEntered = parseInt(dateFormat.substring(0,4));
	var monthEntered = parseInt(dateFormat.substring(5,7))
	
	return !isNaN(yearEntered) && !isNaN(monthEntered);
}