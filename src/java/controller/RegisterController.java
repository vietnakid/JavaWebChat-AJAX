/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.MessageModel;
import model.RoomModel;
import model.UserModel;

/**
 *
 * @author TanTN
 */
public class RegisterController extends HttpServlet {
    private UserModel usermodel;
    private RoomModel roomModel;
    private MessageModel messageModel;
    public RegisterController() {
        super();
        usermodel = new UserModel();
        roomModel = new RoomModel();
        messageModel = new MessageModel();
    }

   
     
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            String username = request.getParameter("id");
            String pw = request.getParameter("password");
            String Gender = request.getParameter("gender");
            String startDateStr = request.getParameter("DateOfBirth");
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
            Date startDate;
            usermodel.checkUserName(username);
            if(usermodel.checkUserName(username)){
                try {
                    startDate = df.parse(startDateStr);

                    int userID = usermodel.Register(username, startDate, pw, Gender);

                    int roomID = roomModel.createNewRoomWithName(username);
                    roomModel.addUserToRoom(userID, roomID);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
               response.sendRedirect("login.jsp");
            }else{
                PrintWriter out = response.getWriter();
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Username already exists!!!');");
                out.println("location='register.jsp';");
                out.println("</script>");
            }
        
        
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
