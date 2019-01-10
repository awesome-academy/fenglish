var websocket = new WebSocket("ws://localhost:8080/fenglish/chat");
var listUser = [];
var uri = $('#uri').val();
websocket.onopen = function(message) {
};
websocket.onmessage = function(message) {
	processMessage(message);
};
websocket.onclose = function(message) {
	processClose(message);
};
websocket.onerror = function(message) {
	processError(message);
};

//function processOpen(message) {
//	textAreaMessage.value += "Server connect... \n";
//}
function openChat(evt, chatName) {
	// Declare all variables
	var i, tabcontent, tablinks;

	// Get all elements with class="tabcontent" and hide them
	tabcontent = document.getElementsByClassName("tabcontent");
	for (i = 0; i < tabcontent.length; i++) {
		tabcontent[i].style.display = "none";
	}

	// Get all elements with class="tablinks" and remove the class "active"
	tablinks = document.getElementsByClassName("tablinks");
	for (i = 0; i < tablinks.length; i++) {
		tablinks[i].className = tablinks[i].className.replace(" active", "");
	}

	// Show the current tab, and add an "active" class to the link that opened
	// the tab
	document.getElementById(chatName).style.display = "block";
	evt.currentTarget.className += " active";
}

function processMessage(message) {
	var data = jQuery.parseJSON(message.data)
	var index;
	console.log(message.data);
	if (listUser.indexOf(data.from) == -1 || listUser.length == 0) {
		listUser.push(data.from)
		index = listUser.indexOf(data.from);
		$("#buttonchat").append(`<button class="tablinks" onclick="openChat(event, '`+data.from+`')">`+data.from+`</button>`)
		var html = 
			`<div id="`+data.from+`" class="tabcontent">
				<textarea id="mess_`+index+`" rows="10" cols="50" readonly></textarea>
				<br /> <br /> <input id="textMessage_`+index+`" type="text" /> <input
					onclick="sendMessage('`+data.from+`')" value="Send Message" type="button" />
			</div>`
		$("#contentChat").append(html);
		$("#mess_"+index).append(data.content + "\n");
		openChat(event,data.from )
	} else {
		index = listUser.indexOf(data.from);
		$("#mess_"+index).append(data.content + "\n");
		openChat(event,data.from )
	}
	
}
//function processClose(message) {
//	textAreaMessage.value += "Server Disconnect... \n";
//}
//function processError(message) {
//	textAreaMessage.value += "Error... " + message + " \n";
//}

function sendMessage(userName) {
	var index;
	index = listUser.indexOf(userName);
	var box = $("#textMessage_"+index);
	if (typeof websocket != 'undefined'
			&& websocket.readyState == WebSocket.OPEN) {
		websocket.send(JSON.stringify({
			from : userName,
			content : box.val()
		}));
		$("#mess_"+index).append("you: " +box.val()+ "\n");
		box.val("");
	}
}
