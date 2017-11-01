var displayMessageArea;
var getMessageServletUrl;
var getMessageRequest;

function initHandleGetMessageRequest() {
    displayMessageArea = document.getElementById("displayMessageArea");
    getMessageServletUrl = "HandleGetMessageController";
    
    getMessages();
}

function getMessages() {
    var parameters = "roomID="+roomID;
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
        for (i = 0; i < messages.length; i++) {
            var message = messages[i];
            appendMessage(message);
        }
    }
    setTimeout(getMessages, 1000);
}

function clearDisplayMessageArea() {
    displayMessageArea.innerHTML = "";
}

function appendMessage(message) {
    var content = message.getElementsByTagName("content")[0].textContent;
    var userID = message.getElementsByTagName("userid")[0].textContent;
    var userName = message.getElementsByTagName("username")[0].textContent;
    var timeUploaded = message.getElementsByTagName("timeUploaded")[0].textContent;
    
    var row;
    var cell;
    row = document.createElement("li");
    if (isOtherUser(userID)) 
        row.setAttribute("class", "other");
    else
        row.setAttribute("class", "self");
    cell = document.createElement("div");
    cell.setAttribute("class", "msg");
    cell.innerHTML = "<h4><i style=\"color: blue;\">" + userName +"</i></h4>";
    cell.innerHTML += "<p>" + content + "</p>";
    cell.innerHTML += "<time>" + timeUploaded + "</time>";
    row.appendChild(cell);
    displayMessageArea.appendChild(row);
}

function isOtherUser(userID) {
    if (userID.trim() !== getSelfUserId().trim()) {
        return true;
    }
    return false;
}