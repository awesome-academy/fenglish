var websocket = new WebSocket("ws://localhost:8080/fenglish/chat");

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
	textAreaMessage.value += "Server connect... \n";
}
function processMessage(message) {
	textAreaMessage.value += message.data + " \n";
}
function processClose(message) {
	textAreaMessage.value += "Server Disconnect... \n";
}
function processError(message) {
	textAreaMessage.value += "Error... " + message + " \n";
}

function sendMessage() {
	if (typeof websocket != 'undefined'
			&& websocket.readyState == WebSocket.OPEN) {
		websocket.send(textMessage.value);
		textMessage.value = "";
	}
}