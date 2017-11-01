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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.UserModel;

/**
 *
 * @author TanTN
 */
public class RegisterController extends HttpServlet {
    private UserModel usermodel;
    public RegisterController() {
        super();
        usermodel = new UserModel();
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
            try {
                startDate = df.parse(startDateStr);
                
                usermodel.Register(username, startDate, pw, Gender);
            } catch (ParseException e) {
                e.printStackTrace();
            }
           response.sendRedirect("login.html");
            
        
        
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
