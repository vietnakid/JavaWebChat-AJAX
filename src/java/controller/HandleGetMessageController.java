/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Messages;
import entity.Users;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.MessageModel;
import model.UserModel;

/**
 *
 * @author KiD
 */
public class HandleGetMessageController extends HttpServlet {
    MessageModel messageModel = new MessageModel();
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
        
        String roomIDPlain = request.getParameter("roomID");
        int roomID = Integer.parseInt(roomIDPlain);

        ArrayList<Messages> messages =  messageModel.getAllMessageInRoom(roomID);
        
        StringBuffer messageXML = new StringBuffer();
        for (Messages message : messages) {
            messageXML.append("<message>");
            String content = sovleBanedWords(message.getContent());
            messageXML.append("<content>" + content + "</content>");
            //@Todo: edit userId
            messageXML.append("<userid>" + message.getUserID() + "</userid>");
            Users user = userModel.getUserInfo(message.getUserID());
            messageXML.append("<username>" + user.getUserName() + "</username>");
            messageXML.append("<timeUploaded>" + message.getTimeUploaded() + "</timeUploaded>");
            messageXML.append("</message>");
        }
        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        response.getWriter().write("<messages>");
        response.getWriter().write(messageXML.toString());
        response.getWriter().write("</messages>");
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

    protected String sovleBanedWords(String content) {
        String res = "";
        List<String> listBannedWords = messageModel.getAllBannerWords();
        String[] words = content.split(" ");
        for (String word : words) {
            boolean isBannedWord = false;
            for(String bannedWord : listBannedWords) {
                if (word.equalsIgnoreCase(bannedWord)) {
                    isBannedWord = true;
                    break;
                }
            }
            
            if (isBannedWord) {
                res += "*** ";
            } else {
                res += word + " ";
            }
        }
        return res;
    }
}
