var listUserInGroup;
var listUserNotInGroup;
var handleConfigurationGroupServletUrl;
var cofigurationGroupRequest;
var roomNewNameInput;

function initHandleConfigurationGroupClicked() {
    listUserInGroup = document.getElementById("listUserInGroup");
    listUserNotInGroup = document.getElementById("listUserNotInGroup");
    roomNewNameInput = document.getElementById("roomNewNameInput");
    handleConfigurationGroupServletUrl = "HandleConfigurationGroup";
    
    document.getElementById("HandleConfigurationGroupForm").setAttribute("action", "ConfiigurationRoomController?roomID="+roomID);
}

function configurationRoomClicked() {
    var parameters = "roomID="+roomID;
    cofigurationGroupRequest = initXMLHttpRequest();
    cofigurationGroupRequest.open("POST", handleConfigurationGroupServletUrl, true);
    cofigurationGroupRequest.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            handleConfigurationRoomResponse(this.responseXML);
        }
    };
    cofigurationGroupRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    cofigurationGroupRequest.send(parameters);
}

function handleConfigurationRoomResponse(dataXML) {
    clearDisplayConfiguration();
    
    if (dataXML == null) {
        return false;
    } else {
        var roomNewName = dataXML.getElementsByTagName("roomname");
        roomNewNameInput.setAttribute("value", roomNewName[0].textContent);
        
        var usersInGroup = dataXML.getElementsByTagName("usersinroom");
        console.log(usersInGroup);
        for (i = 0; i < usersInGroup.length; i++) {
            var user = usersInGroup[i];
                appendUserInGroup(user);
        }
        
        var usersNotInGroup = dataXML.getElementsByTagName("usersnotinroom");
        for (i = 0; i < usersNotInGroup.length; i++) {
            var user = usersNotInGroup[i];
                appendUserNotInGroup(user);
        }
    }
}

function clearDisplayConfiguration() {
    listUserInGroup.innerHTML = "";
    listUserNotInGroup.innerHTML = "";
    roomNewNameInput.setAttribute("value", "");
    roomNewNameInput.setAttribute("placeholder", "Enter room's new name");
}

function appendUserInGroup(user) {
    var userName = user.getElementsByTagName("username")[0].textContent;
    var userID = user.getElementsByTagName("userid")[0].textContent;
    var gender = user.getElementsByTagName("gender")[0].textContent;
    var dateOfBirth = user.getElementsByTagName("dateofbirth")[0].textContent;
    var row;
    row = document.createElement("label");
    row.innerHTML = "<input type=\"checkbox\" name=\"" + userID + "\" checked=\"checked\">" + userName + " | " + gender + " | " + dateOfBirth;
    listUserInGroup.appendChild(row);
    listUserInGroup.appendChild(document.createElement("br"));
}

function appendUserNotInGroup(user) {
    var userName = user.getElementsByTagName("username")[0].textContent;
    var userID = user.getElementsByTagName("userid")[0].textContent;
    var gender = user.getElementsByTagName("gender")[0].textContent;
    var dateOfBirth = user.getElementsByTagName("dateofbirth")[0].textContent;
    var row;
    row = document.createElement("label");
    row.innerHTML = "<input type=\"checkbox\" name=\"" + userID + "\">" + userName + " | " + gender + " | " + dateOfBirth;
    listUserNotInGroup.appendChild(row);
    listUserNotInGroup.appendChild(document.createElement("br"));
}
