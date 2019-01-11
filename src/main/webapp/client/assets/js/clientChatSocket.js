var websocket;
$(document).ready(function() {
	var chatSocketUrl = $("#chatSocketUrl").val();
	websocket = new WebSocket(chatSocketUrl);
	websocket.onopen = function(message) {
		processOpen(message);
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

	function processOpen(message) {
		textAreaMessage.value += "Đã kết nối. Hãy chat với chúng tôi... \n";
	}
	function processMessage(message) {
		textAreaMessage.value += message.data + " \n";
	}
	function processClose(message) {
		textAreaMessage.value += "Mất kết nối... \n";
	}
	function processError(message) {
		textAreaMessage.value += "Error... " + message + " \n";
	}

});
function sendMessage() {
	if (typeof websocket != 'undefined'
			&& websocket.readyState == WebSocket.OPEN) {
		websocket.send(textMessage.value);
		textAreaMessage.value += "you: " + textMessage.value + "\n";
		textMessage.value = "";
	}
}

$(document).ready(function() {
	$('#chatButonMeassage').click(function(e) {
		if ($('#chatAreamessage:visible').length == 0) {
			$('#chatAreamessage').show();
		} else {
			$('#chatAreamessage').hide();
		}
	});
});
