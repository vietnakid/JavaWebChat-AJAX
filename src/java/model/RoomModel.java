/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.DatabaseDAO;
import entity.Rooms;
import entity.Users;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author KiD
 */
public class RoomModel {
    DatabaseDAO databaseDAO = new DatabaseDAO();
    
    public int createNewRoomWithName(String roomName) {
        int roomID = databaseDAO.createNewRoomWithName(roomName);
        databaseDAO.createNewMessage(1, roomID, "Created room at " + new Date(), new Date());
        return roomID;
    }
    
    public void addUserToRoom(int userID, int roomID) {
        databaseDAO.addUserToRoom(userID, roomID);
    }
    
    public void deleteUserFromRoom(int userID, int roomID) {
        databaseDAO.deleteUserFromRoom(userID, roomID);
    }
    
    public void deleteUsersFromRoom(int roomID) {
        databaseDAO.deleteUsersFromRoom(roomID);
    }
    
    public void changeNameOfRoom(int roomID, String roomName) {
        databaseDAO.changeNameOfRoom(roomID, roomName);
    }
    
    public ArrayList<Integer> getRoomIDsWithUserID(int userID) {
        return databaseDAO.getRoomIDsWithUserID(userID);
    }
    
    public Rooms getRoomInfoByRoomID(int roomID) {
        return databaseDAO.getRoomInfoByRoomID(roomID);
    }
    
    public boolean inUserIdInRoomId(int userID, int RoomID) {
        if (RoomID == 0) return true;
        return databaseDAO.inUserIdInRoomId(userID, RoomID);
    }
    
    public ArrayList<Users> getUsersInRoom(int roomID) {
        UserModel userModel = new UserModel();
        ArrayList<Users> allUserInServer = userModel.getAllUsers();
        ArrayList<Users> res = new ArrayList<>();
        for (Users user : allUserInServer) {
            if (inUserIdInRoomId(user.getUserID(), roomID))
                res.add(user);
        }
        return res;
    }
    
    public ArrayList<Users> getUsersNotInRoom(int roomID) {
        UserModel userModel = new UserModel();
        ArrayList<Users> allUserInServer = userModel.getAllUsers();
        ArrayList<Users> res = new ArrayList<>();
        for (Users user : allUserInServer) {
            if (!inUserIdInRoomId(user.getUserID(), roomID))
                res.add(user);
        }
        return res;
    }
}
