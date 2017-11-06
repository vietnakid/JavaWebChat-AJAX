/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;
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
public class ConfiigurationRoomController extends HttpServlet {


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
        Map<String, String[]> parameters = request.getParameterMap();
        String roomName = request.getParameter("roomNewNameInput").trim();
        int roomID = Integer.parseInt(request.getParameter("roomID").trim());
        ArrayList<Integer> userIDs = new ArrayList<>();
        for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
            String key = entry.getKey();
            String[] value = entry.getValue();
            if (!key.equalsIgnoreCase("roomNewNameInput") && !key.equalsIgnoreCase("roomID")) {
                userIDs.add(Integer.parseInt(key));
            } 
        }
        RoomModel roomModel = new RoomModel();
        roomModel.deleteUsersFromRoom(roomID);
        for (Integer userID : userIDs) {
            roomModel.addUserToRoom(userID, roomID);
        }
        roomModel.changeNameOfRoom(roomID, roomName);
        response.sendRedirect("chat.jsp?roomID="+roomID);
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
