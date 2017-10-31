/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.DatabaseDAO;
import entity.Messages;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author KiD
 */
public class MessageModel {
    DatabaseDAO databaseDAO = new DatabaseDAO();
    
    public void createNewMessage(int userId, int RoomId, String content, Date dateUploaded) {
        databaseDAO.createNewMessage(userId, RoomId, content, dateUploaded);
    }
    
    public List<Messages> getAllMessageInRoom(int roomID) {
        return databaseDAO.getAllMessageInRoom(roomID);
    }
}
