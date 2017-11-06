<%-- 
    Document   : chat
    Created on : Oct 31, 2017, 2:35:18 PM
    Author     : KiD
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>${room.roomName}</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script type="text/javascript" src="javascript/initalWhenChatPageLoad.js?n=2"></script>
        <script type="text/javascript" src="javascript/handleSendMessageRequest.js?n=2"></script>
        <script type="text/javascript" src="javascript/handleGetMessageRequest.js?n=2"></script>
        <script type="text/javascript" src="javascript/handleGetRoomRequest.js?n=5"></script>
        <script type="text/javascript" src="javascript/handleGetAllUsersRequest.js?n=2"></script>
        <script type="text/javascript" src="javascript/handleConfigurationGroupClicked.js?n=3"></script>
        <link rel="stylesheet" type="text/css" href="style.css?n=6">

        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    </head>
    <body onload="initalPage()">

        <script>
            roomID = "${not empty param.roomID ? param.roomID : 0}";
        </script>

        <div class="viewWrapper">
            <div class="roomchat">
                <div class="yourroom">Your Rooms</div>

                <div class="addRoom">
                    <button type="button" class="btn btn-success btn-lg" data-toggle="modal" data-target="#createRoomModal" onclick="getAllUsers()">New</button>
                </div>
                <div class="logOut">
                    <a href="logout.jsp"><button type="button" class="btn btn-danger btn-lg">Logout</button></a>
                </div>
                <ol class="rooms" id="displayRoomArea">

                </ol>
            </div>
            <div class="chatview">
                <div class="menu">
                    <div class="back"><i class="fa fa-chevron-left"></i> <img src="https://i.imgur.com/DY6gND0.png" draggable="false"/></div>
                    <div class="name">${room.roomName}</div>
                    <div class="last">18:09</div>
                    <div class="configurationBtn"> <button type="button" class="btn btn-warning btn-lg" data-toggle="modal" data-target="#configurationRoomModal" onclick="configurationRoomClicked()">Configuration Room</button> </div>
                </div>

                <ol class="chat" id="displayMessageArea">

                </ol>
                <input class="textarea" id ="chatField" type="text" placeholder="Type here!"/><div class="emojis"></div>
            </div>
        </div>


        <!-- Modal Create Room -->
        <div class="modal fade" id="createRoomModal" role="dialog">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Create Room Chat</h4>
                    </div>
                    <div class="modal-body">
                        <form action="CreateRoomChat" method="POST">
                            <div class="form-group">
                                <label for="roomName">Room name:</label>
                                <input class="form-control" id="roomNameInput" placeholder="Enter room name" name="roomNameInput">
                            </div>
                            <div class="checkbox" id="listUserNameCheckBox">
                                <label><input type="checkbox" name="remember"> Remember me</label>
                            </div>

                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-success floatleft">Create</button>
                        </form>
                        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                    </div>
                </div>
                <!-- End Modal content-->
            </div>
        </div>
        <!-- End Modal Create Room -->

        <!-- Modal Configuration Room -->
        <div class="modal fade" id="configurationRoomModal" role="dialog">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Configuration Room Chat: <b>${room.roomName}</b></h4>
                    </div>
                    <div class="modal-body">
                        <form id="HandleConfigurationGroupForm" action="ConfiigurationRoomController?roomID=" method="POST">
                            <div class="form-group">
                                <label for="roomName">Room's new name:</label>
                                <input class="form-control" id="roomNewNameInput" placeholder="Enter room's new name" name="roomNewNameInput">
                            </div>
                            </br>
                            <label for="roomName">Members of group <i>${room.roomName}</i></label>
                            <div class="checkbox" id="listUserInGroup">
                                <label><input type="checkbox" name="remember"> User name</label>
                            </div>
                            </br>
                            <label for="roomName">Add new user to group <i>${room.roomName}</i></label>
                            <div class="checkbox" id="listUserNotInGroup">
                                <label><input type="checkbox" name="remember"> User name</label>
                            </div>

                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-success floatleft">Apply</button>
                        </form>
                            <button type="button" class="btn btn-warning floatleft" onclick="leaveRoom()">Leave Room</button>
                        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                    </div>
                </div>
                <!-- End Modal content-->
            </div>
        </div>
        <!-- End Modal Configuration Room -->
    </body>
</html>
