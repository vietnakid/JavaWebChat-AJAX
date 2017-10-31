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
public class Messages {
    private int messageID;
    private int roomID;
    private int userID;
    private String content;
    private Date timeUploaded;

    public Messages() {
    }

    public Messages(int messageID, int roomID, int userID, String content, Date timeUploaded) {
        this.messageID = messageID;
        this.roomID = roomID;
        this.userID = userID;
        this.content = content;
        this.timeUploaded = timeUploaded;
    }

    public int getMessageID() {
        return messageID;
    }

    public void setMessageID(int messageID) {
        this.messageID = messageID;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTimeUploaded() {
        return timeUploaded;
    }

    public void setTimeUploaded(Date timeUploaded) {
        this.timeUploaded = timeUploaded;
    }

    
    
}
