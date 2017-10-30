var displayMessageTable;
var getMessageServletUrl;
var getMessageRequest;

function initHandleGetMessageRequest() {
    displayMessageTable = document.getElementById("displayMessageTable");
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
    if (messageXML == null) {
        return false;
    } else {
        var messages = messageXML.getElementsByTagName("message");
        
        var innerdisplayMessageTable = "";
        for (i = 0; i < messages.length; i++) {
            var message = messages[i].getElementsByTagName("content")[0].textContent;
            innerdisplayMessageTable += "<tr> <td> " + message + " </td> </tr>"
        }
        displayMessageTable.innerHTML = innerdisplayMessageTable;
    }
    setTimeout(getMessage, 1000);
}

function appendMessage(message) {
    var row;
    var cell;
    displayMessageTable.style.display = 'table';
    row = document.createElement("tr");
    cell = document.createElement("td");
    row.appendChild(cell);
    cell.appendChild(message);
    displayMessageTable.appendChild(row);
}