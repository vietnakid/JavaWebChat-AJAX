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
        <script type="text/javascript" src="javascript/initalWhenChatPageLoad.js"> </script>
        <script type="text/javascript" src="javascript/handleSendMessageRequest.js"> </script>
        <script type="text/javascript" src="javascript/handleGetMessageRequest.js"> </script>
        <script type="text/javascript" src="javascript/handleGetRoomRequest.js"> </script>
        <link rel="stylesheet" type="text/css" href="style.css">
    </head>
    <body onload="initalPage()">
        <div class="viewWrapper">
            <div class="roomchat">
                <ol class="rooms" id="displayRoomArea">

                </ol>
            </div>
            <div class="chatview">
                <div class="menu">
                    <div class="back"><i class="fa fa-chevron-left"></i> <img src="https://i.imgur.com/DY6gND0.png" draggable="false"/></div>
                    <div class="name">${room.roomName}</div>
                    <div class="last">18:09</div>
                </div>
                <ol class="chat" id="displayMessageArea">

                </ol>
                <input class="textarea" id ="chatField" type="text" placeholder="Type here!"/><div class="emojis"></div>
            </div>
        </div>
    </body>
</html>
