/*user*/
$(".btnDeleteUser").on("click", function() {
	var $row = $(this).closest("tr");
	var $text = $row.find(".idUser").text();
	$.confirm({
	    title: "Confirm",
	    content: "Delete User?",
	    buttons: {
	        confirm: function () {
	            $.ajax({
	        		type : "DELETE",
	        		url : "/fenglish/admin/users/" + $text,
	        		success : function(response) {
	        			window.location.replace("/fenglish/admin/");
	        		}
	        	});
	        },
	        cancel: function () {
	            $.alert("Canceled!");
	        }
	    }
	});
});