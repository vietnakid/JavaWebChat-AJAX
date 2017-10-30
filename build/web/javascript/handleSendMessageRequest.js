var chatField;
var sendMessageRequest;
var sendMessageServletUrl;

function initHandleSendMessageRequest() {
    chatField = document.getElementById("chatField");
    sendMessageServletUrl = "HandleSendMessageController";
    
    //events
    detectSendMessageByPressEnter();
}

function detectSendMessageByPressEnter() {
    chatField.onkeypress = function(event) {
        if (!event) e = window.event;
        var keyCode = event.keyCode || event.which;
        var enterKeyCode = '13';
        if (keyCode == enterKeyCode){
          var message = chatField.value;
          sendMessge(message);
          clearChatField();
        }
    };
}

function clearChatField() {
    chatField.value = "";
}

function sendMessge(message) {
    var parameters = "message="+message;
//    console.log("message handleSendMessageRequest = " + message);
    sendMessageRequest = initXMLHttpRequest();
    sendMessageRequest.open("POST", sendMessageServletUrl, true);
    sendMessageRequest.onreadystatechange = handleSendMessageRespone();
    sendMessageRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    sendMessageRequest.setRequestHeader("Accept-Language", "en-US,en,vi;q=0.5");
    sendMessageRequest.send(parameters);
}

function handleSendMessageRespone() {
    
}

