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

/*subject*/
$(".btnDeleteSubject").on("click", function() {
	var $row = $(this).closest("tr");
	var $text = $row.find(".idSubject").text();
	$.confirm({
	    title: "Confirm",
	    content: "Delete Subject?",
	    buttons: {
	        confirm: function () {
	            $.ajax({
	        		type : "DELETE",
	        		url : "/fenglish/admin/subjects/" + $text,
	        		success : function(response) {
	        			window.location.replace("/fenglish/admin/subjects");
	        		}
	        	});
	        },
	        cancel: function () {
	            $.alert("Canceled!");
	        }
	    }
	});
});