/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Rooms;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.RoomModel;

/**
 *
 * @author KiD
 */
public class HandleGetRoomController extends HttpServlet {


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
        Cookie[] cookies = request.getCookies();
        int userID = 0;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equalsIgnoreCase("userID")) {
                userID = Integer.parseInt(cookie.getValue());
            }
        }
        
        RoomModel roomModel = new RoomModel();
        ArrayList<Integer> roomIDs =  roomModel.getRoomIDsWithUserID(userID);
        ArrayList<Rooms> rooms = new ArrayList<>();
        for (Integer roomID : roomIDs) {
            Rooms room = roomModel.getRoomInfoByRoomID(roomID);
            rooms.add(room);
        }
        
        StringBuffer messageXML = new StringBuffer();
        for (Rooms room : rooms) {
            messageXML.append("<room>");
            messageXML.append("<name>" + room.getRoomName() + "</name>");
            messageXML.append("<id>" + room.getRoomID() + "</id>");
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
