var displayMessageArea;
var getMessageServletUrl;
var getMessageRequest;

function initHandleGetMessageRequest() {
    displayMessageArea = document.getElementById("displayMessageArea");
    getMessageServletUrl = "HandleGetMessageController";
    
    getMessage();
}

function getMessage() {
    var parameters = "";
    getMessageRequest = initXMLHttpRequest();
    getMessageRequest.open("POST", getMessageServletUrl, true);
    getMessageRequest.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            handleGetMessageRespone(this.responseXML);
        }
    };
    getMessageRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    getMessageRequest.send(parameters);
}

function handleGetMessageRespone(messageXML) {
    clearDisplayMessageArea();
    if (messageXML == null) {
        return false;
    } else {
        var messages = messageXML.getElementsByTagName("message");
        
        var innerdisplayMessageTable = "";
        for (i = 0; i < messages.length; i++) {
            var message = messages[i].getElementsByTagName("content")[0].textContent;
            appendMessage(message);
        }
    }
    setTimeout(getMessage, 1000);
}

function clearDisplayMessageArea() {
    displayMessageArea.innerHTML = "";
}

function appendMessage(message) {
    var row;
    var cell;
    row = document.createElement("li");
    row.setAttribute("class", "other");
    cell = document.createElement("div");
    cell.setAttribute("class", "msg");
    cell.innerHTML = "<p>" + message + "</p>";
    row.appendChild(cell);
    displayMessageArea.appendChild(row);
}