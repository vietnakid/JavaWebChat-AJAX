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
        <title>JSP Page</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script type="text/javascript" src="javascript/initalWhenMessagePageLoad.js"> </script>
        <script type="text/javascript" src="javascript/handleSendMessageRequest.js"> </script>
        <script type="text/javascript" src="javascript/handleGetMessageRequest.js?n=1"> </script>
    </head>
    <body onload="initalPage()">
        
        <table> 
            <tr>
                <td>
                    <table id="displayMessageTable"> 

                    </table>
                </td>
            </tr>
            <tr id="enterMessageArea">
                <input type="text" size="40" id="chatField">
            </tr>
        </table>
        
    </body>
</html>
