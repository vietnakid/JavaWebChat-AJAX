/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;

/**
 *
 * @author KiD
 */
public class Users {
    private int userID;
    private String userName;
    private String password;
    private Date dateOfBirth;
    private String sex;

    public Users() {
    }

    public Users(int userID, String userName, String password, Date dateOfBirth, String sex) {
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.sex = sex;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
    
    
}
