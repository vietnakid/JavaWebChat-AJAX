/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Users;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.RoomModel;
import model.UserModel;

/**
 *
 * @author KiD
 */
public class HandleConfigurationGroup extends HttpServlet {
    RoomModel roomModel = new RoomModel();
    UserModel userModel = new UserModel();

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
        int roomID = Integer.parseInt(request.getParameter("roomID"));
        ArrayList<Users> usersInRoom = roomModel.getUsersInRoom(roomID);
        ArrayList<Users> usersNotInRoom = roomModel.getUsersNotInRoom(roomID);
        String roomName = roomModel.getRoomInfoByRoomID(roomID).getRoomName();
        
        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        
        response.getWriter().write("<data>");
        
        response.getWriter().write("<roomname>");
        response.getWriter().write(roomName);
        response.getWriter().write("</roomname>");
        
        StringBuffer dataXML = new StringBuffer();
        for (Users userInRoom : usersInRoom) {
            dataXML.append("<usersinroom>");
            dataXML.append("<username>" +userInRoom.getUserName().trim() + "</username>");
            dataXML.append("<userid>" + userInRoom.getUserID() + "</userid>");
            dataXML.append("<gender>" + userInRoom.getSex()+ "</gender>");
            dataXML.append("<dateofbirth>" + userInRoom.getDateOfBirth()+ "</dateofbirth>");
            dataXML.append("</usersinroom>");
        }
        response.getWriter().write("<usersinrooms>");
        response.getWriter().write(dataXML.toString());
        response.getWriter().write("</usersinrooms>");
        
        dataXML = new StringBuffer();
        for (Users userNotInRoom : usersNotInRoom) {
            dataXML.append("<usersnotinroom>");
            dataXML.append("<username>" +userNotInRoom.getUserName().trim() + "</username>");
            dataXML.append("<userid>" + userNotInRoom.getUserID() + "</userid>");
            dataXML.append("<gender>" + userNotInRoom.getSex()+ "</gender>");
            dataXML.append("<dateofbirth>" + userNotInRoom.getDateOfBirth()+ "</dateofbirth>");
            dataXML.append("</usersnotinroom>");
        }
        response.getWriter().write("<usersnotinrooms>");
        response.getWriter().write(dataXML.toString());
        response.getWriter().write("</usersnotinrooms>");
        
        response.getWriter().write("</data>");
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
