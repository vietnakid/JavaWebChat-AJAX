/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.MessageModel;

/**
 *
 * @author TanTN
 */
public class BannedWordController extends HttpServlet {
    private MessageModel messagemodel;
    public BannedWordController(){
        super();
        messagemodel = new MessageModel();
               
    }
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       String word = request.getParameter("word");
       messagemodel.AddNewBannerWord(word);
       response.sendRedirect("/Chat/BannedWord.jsp");
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
