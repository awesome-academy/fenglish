/*user*/
$(".btnDeleteUser").on("click", function() {
	var $row = $(this).closest("tr");
	var $text = $row.find(".idUser").text();
	$.confirm({
		title : "Confirm",
		content : "Delete User?",
		buttons : {
			confirm : function() {
				$.ajax({
					type : "DELETE",
					url : "/fenglish/admin/users/" + $text,
					success : function(response) {
						window.location.replace("/fenglish/admin/");
					}
				});
			},
			cancel : function() {
				$.alert("Canceled!");
			}
		}
	});
});

/* subject */
$(".btnDeleteSubject").on("click", function() {
	var $row = $(this).closest("tr");
	var $text = $row.find(".idSubject").text();
	$.confirm({
		title : "Confirm",
		content : "Delete Subject?",
		buttons : {
			confirm : function() {
				$.ajax({
					type : "DELETE",
					url : "/fenglish/admin/subjects/" + $text,
					success : function(response) {
						window.location.replace("/fenglish/admin/subjects");
					}
				});
			},
			cancel : function() {
				$.alert("Canceled!");
			}
		}
	});
});

/* question */
$(".btnDeleteQuestion").on("click", function() {
	var $row = $(this).closest("tr");
	var $text = $row.find(".idQuestion").text();
	$.confirm({
		title : "Confirm",
		content : "Delete Question?",
		buttons : {
			confirm : function() {
				$.ajax({
					type : "DELETE",
					url : "/fenglish/admin/questions/" + $text,
					success : function(response) {
						window.location.replace("/fenglish/admin/questions");
					}
				});
			},
			cancel : function() {
				$.alert("Canceled!");
			}
		}
	});
});

/*post*/
$(".btnDeletePost").on("click", function() {
	var $row = $(this).closest("tr");
	var $text = $row.find(".idPost").text();
	$.confirm({
		title : "Confirm",
		content : "Delete Post?",
		buttons : {
			confirm : function() {
				$.ajax({
					type : "DELETE",
					url : "/fenglish/admin/posts/" + $text,
					success : function(response) {
						window.location.replace("/fenglish/admin/posts/page=1");
					}
				});
			},
			cancel : function() {
				$.alert("Canceled!");
			}
		}
	});
});

/* dynamic form */
var count = 2;

$(document).ready(function(){
	$("#plus").click(function() {
		count++;

		if (count <= 4) {
			addMoreInput();
		}
	});
});

function addMoreInput() {
	var div = document.createElement("div");
	div.className = "form-group";

	var label = document.createElement("label");
	label.className = "col-sm-3 control-label";
	label.innerHTML = getQuestionLabel(count);

	var div2 = document.createElement("div");
	div2.className = "col-sm-9";

	var input = document.createElement("input");
	input.type = "text";
	input.name = "option" + count;
	input.id = "option" + count;
	input.className = "form-control";
	
	var a = document.createElement("a");
	a.href = "javascript:void(0)";
	a.className = "btn btn-danger";
	a.innerHTML = "-";
	a.onclick = function() {
		removeInput(this);
	}

	div2.appendChild(input);
	div2.appendChild(a);
	div.appendChild(label);
	div.appendChild(div2);

	$("#dynamic_form").find(".form-group").last().after(div);
}

function getQuestionLabel(id) {
	switch (id) {
		case 3:
			return "C";
			break;
		case 4:
			return "D";
			break;
		default:
			return null;
	}
}

function removeInput(element) {
	var form_group = $(element).closest(".form-group");
	var input = form_group.find("input");
	
	if (count == 4 && input.attr("name") == "option3") {
		$('input[name="option4"]').parent().siblings("label").html("C");
		$('input[name="option4"]').attr("id", "option3").attr("name", "option3");
	}
	
	count--;
	form_group.remove();
}