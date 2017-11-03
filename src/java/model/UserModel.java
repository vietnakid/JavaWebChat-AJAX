/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.DatabaseDAO;
import entity.Users;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author KiD
 */
public class UserModel {
    DatabaseDAO databaseDAO = new DatabaseDAO();
    
    public void createNewUser(String userName, Date dateOfBirth, String password, String sex) {
        databaseDAO.createNewUser(userName, dateOfBirth, password, sex);
    }
    
    public Users getUserInfo(int userID) {
        return databaseDAO.getUserInfo(userID);
    }
    
    public ArrayList<Users> getAllUsers() {
        return databaseDAO.getAllUsers();
    }
    
    public List<Users> getAllAccount() {
        return databaseDAO.getAllAccount();
    }
     public Users getAccountById(String username) {
         return databaseDAO.getAccountById(username);
     }
    public Boolean CheckAuthen(String userID, String pw){
        return databaseDAO.CheckAuthen(userID, pw);
    }
    public int Register(String username, Date DateOfBirth, String pw, String sex) {
        return databaseDAO.Register(username, DateOfBirth, pw, sex);
    }
    
    public int getUserIdFromCookie(ServletRequest request) {
        Cookie[] cookies = ((HttpServletRequest)request).getCookies();
        int userID = 0;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equalsIgnoreCase("userID")) {
                    userID = Integer.parseInt(cookie.getValue());
                }
            }
        }
        return userID;
    }
}
