var dataPoints =[];
var chart
window.onload = function() {
	var statisticTitle = document.getElementById("statisticTitleId").value;
	var axisXTitle = document.getElementById("axisXTitleId").value;
	var axisYTitle = document.getElementById("axisYTitleId").value;
	var contextUrl = $("#contextUrl").val();
	$.getJSON(contextUrl+'/admin/statistic/exercises', addData);
	
	function addData(data) {
		for (var i = 0; i < data.length; i++) {
			dataPoints.push({
				y: data[i].y,
				label: data[i].label
			});
		}
		chart.render();
	}
	
	var chart = new CanvasJS.Chart("chartContainer", {
		theme : "light2",
		title : {
			text : statisticTitle
		},
		axisX : {
			title : axisXTitle
		},
		axisY : {
			title : axisYTitle
		},
		data : [{
			type: "line",
			yValueFormatString: axisYTitle+ " #,##0 ",
			dataPoints : dataPoints
		}]
	});
}