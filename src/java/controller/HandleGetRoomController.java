/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Messages;
import entity.Rooms;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.MessageModel;
import model.RoomModel;
import model.UserModel;

/**
 *
 * @author KiD
 */
class RoomComparator implements Comparator<Rooms> {  

    @Override
    public int compare(Rooms room1, Rooms room2) {
        MessageModel messageModel = new MessageModel();
        Messages message1 = messageModel.getNewestMessageInRoom(room1.getRoomID());
        Messages message2 = messageModel.getNewestMessageInRoom(room2.getRoomID());
        if (message1 != null && message2 != null)
            return -message1.getTimeUploaded().compareTo(message2.getTimeUploaded());
        return 0;
    }
}


public class HandleGetRoomController extends HttpServlet {

    RoomModel roomModel = new RoomModel();
    UserModel userModel = new UserModel();
    MessageModel messageModel = new MessageModel();
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int userID = userModel.getUserIdFromCookie(request); 
        
        ArrayList<Integer> roomIDs =  roomModel.getRoomIDsWithUserID(userID);
        ArrayList<Rooms> rooms = new ArrayList<>();
        for (Integer roomID : roomIDs) {
            Rooms room = roomModel.getRoomInfoByRoomID(roomID);
            rooms.add(room);
        }
        
        rooms.sort(new RoomComparator());
        
        StringBuffer messageXML = new StringBuffer();
        for (Rooms room : rooms) {
            messageXML.append("<room>");
            messageXML.append("<name>" + room.getRoomName() + "</name>");
            messageXML.append("<id>" + room.getRoomID() + "</id>");
            
            Messages message = messageModel.getNewestMessageInRoom(room.getRoomID());
            
            if (message != null) {
                messageXML.append("<newestsender>" + userModel.getUserInfo(message.getUserID()).getUserName().trim() + "</newestsender>");
                messageXML.append("<content>" + message.getContent().substring(0, Math.min(30, message.getContent().length())) + "</content>");
                messageXML.append("<timeUploaded>" + message.getTimeUploaded() + "</timeUploaded>");
                System.out.println( message.getContent() + " room ID = " + room.getRoomID());
            } else {
                messageXML.append("<newestsender>" + "Error" + "</newestsender>");
                messageXML.append("<content>" + "Error" + "</content>");
                messageXML.append("<timeUploaded>" + "Error" + "</timeUploaded>");
            }
            
            messageXML.append("</room>");
        }
        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        response.getWriter().write("<rooms>");
        response.getWriter().write(messageXML.toString());
        response.getWriter().write("</rooms>");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
