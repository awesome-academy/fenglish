$(document).ready(function() {
	var contextUrl = $("#contextUrl").val();
	var myCompanyLocation = [21.0164657, 105.7817772];
	
	tomtom.setProductInfo('Fenglish', 'Maps API');
	var map = tomtom.L.map('map', {
		key : 'XdapoooqlcVYGWjDjDttS508DcgtaNcg',
		basePath : contextUrl + 'client/assets/sdk',
		center: myCompanyLocation,
		zoom: 17
	});
	
	var marker = tomtom.L.marker(myCompanyLocation, {
		icon: tomtom.L.icon({
			iconUrl: contextUrl + 'client/assets/images/marker.png',
			iconSize: [40, 40]
		})
	}).addTo(map);
	
	var companyImage = contextUrl + "client/assets/images/handico.jpg";
	var customPopup = '<p class="popup_map">' +
		'<img src="' + companyImage + '"' +
	    'alt="Fenglish" class="company_image">' +
	    '<div class="my_company">' +
	    '<span class="company_name">Fenglish</span></br></br>' +
	    '<span>11F, Handico Building, Pham Hung, Me Tri Ha, Me Tri, Nam Tu Liem, Ha Noi.</br>VN</span></br>' +
	    '</p>';
	
	marker.bindPopup(customPopup).openPopup();
});