var displayRoomArea;
var getRoomServletUrl;
var getRoomRequest;

function initHandleGetRoomRequest() {
    displayRoomArea = document.getElementById("displayRoomArea");
    getRoomServletUrl = "HandleGetRoomController";
    
    getRooms();
}

function getRooms() {
    var parameters = "";
    getRoomRequest = initXMLHttpRequest();
    getRoomRequest.open("POST", getRoomServletUrl, true);
    getRoomRequest.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            handleGetRoomRespone(this.responseXML);
        }
    };
    getRoomRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    getRoomRequest.send(parameters);
}

function handleGetRoomRespone(roomXML) {
    clearDisplayRoomArea();
    
    if (roomXML == null) {
        return false;
    } else {
        var rooms = roomXML.getElementsByTagName("room");
        for (i = 0; i < rooms.length; i++) {
            var room = rooms[i];
            appendRoom(room);
        }
    }
    setTimeout(getRooms, 1000);
}

function clearDisplayRoomArea() {
    displayRoomArea.innerHTML = "";
}

function appendRoom(room) {
    var roomName = room.getElementsByTagName("name")[0].textContent;
    var roomID = room.getElementsByTagName("id")[0].textContent;
    
    var row;
    var cell;
    row = document.createElement("li");
    cell = document.createElement("div");
    var innerHTML;
    innerHTML = "</br><a href=\"chat.jsp?roomID=" + roomID + "\">";
    innerHTML += "<img src=\"https://i.imgur.com/DY6gND0.png\" height=\"40px\" width=\"40px\" draggable=\"false\">";
    innerHTML += "<div class=\"roomName\">" + roomName + "</div></a></br>";
    cell.innerHTML = innerHTML;
    row.appendChild(cell);
    displayRoomArea.appendChild(row);
}