<%-- 
    Document   : logout
    Created on : Nov 3, 2017, 2:29:37 PM
    Author     : KiD
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <% 
        Cookie userIDCookie = new Cookie("userID", "");
        userIDCookie.setMaxAge(0);
        response.addCookie(userIDCookie);
        response.sendRedirect("login.jsp");
    %>
</html>
