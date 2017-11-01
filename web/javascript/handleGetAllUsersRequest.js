var displayUsersArea;
var getAllUsersServletUrl;
var getAllUsersRequest;
var roomNameInput;

function initHandleGetAllUsersRequest() {
    displayUsersArea = document.getElementById("listUserNameCheckBox");
    roomNameInput = document.getElementById("roomNameInput");
    getAllUsersServletUrl = "HandleGetAllUsersRequest";
    
    getAllUsers();
}

function getAllUsers() {
    var parameters = "";
    getAllUsersRequest = initXMLHttpRequest();
    getAllUsersRequest.open("POST", getAllUsersServletUrl, true);
    getAllUsersRequest.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            handleGetAllUsersRespone(this.responseXML);
        }
    };
    getAllUsersRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    getAllUsersRequest.send(parameters);
}

function handleGetAllUsersRespone(usersXML) {
    clearDisplayAllUsersArea();
    
    if (usersXML == null) {
        return false;
    } else {
        var users = usersXML.getElementsByTagName("user");
        for (i = 0; i < users.length; i++) {
            var user = users[i];
            appendUser(user);
        }
    }
}

function clearDisplayAllUsersArea() {
    displayUsersArea.innerHTML = "";
    roomNameInput.setAttribute("value", "");
    roomNameInput.setAttribute("placeholder", "Enter room name");
}

function appendUser(user) {
    var userName = user.getElementsByTagName("username")[0].textContent;
    var userID = user.getElementsByTagName("userid")[0].textContent;
    var gender = user.getElementsByTagName("gender")[0].textContent;
    var dateOfBirth = user.getElementsByTagName("dateofbirth")[0].textContent;
    if (!isOtherUser(userID)) return;
    var row;
    row = document.createElement("label");
    row.innerHTML = "<input type=\"checkbox\" name=\"" + userID + "\">" + userName + " | " + gender + " | " + dateOfBirth;
    displayUsersArea.appendChild(row);
    displayUsersArea.appendChild(document.createElement("br"));
}