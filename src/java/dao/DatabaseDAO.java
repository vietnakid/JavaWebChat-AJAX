/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import context.DBContext;
import entity.Messages;
import entity.Rooms;
import entity.Users;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author KiD
 */
public class DatabaseDAO {
    private Connection connection;
    
    public DatabaseDAO() {
        DBContext dbcontext = new DBContext();
        try {
            this.connection = dbcontext.getConnection();
        } catch (Exception ex) {
            Logger.getLogger(DatabaseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void createNewRoomWithName(String roomName) {
        try {
            String query = "INSERT INTO Rooms (room_name) VALUES (?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, roomName);
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addUserToRoom(int userID, int roomID) {
        //@ Todo: add code here
    }
    
    public void deleteUserFromRoom(int userID, int roomID) {
        //@ Todo: add code here
    }
    
    public void createNewMessage(int userId, int RoomId, String content, Date dateUploaded) {
        //@ Todo: add code here
    }
    
    public void createNewUser(String userName, Date dateOfBirth, String password, String sex) {
        //@ Todo: add code here
    }
    
    public Users getUserInfo(int userID) {
        Users user = new Users();
        
        //@ Todo: add code here
        
        return user;
    }
    
    public List<Integer> getRoomIDsWithUserID(int userID) {
        ArrayList<Integer> rooms = new ArrayList<>();
        
        try {
            String query = "SELECT * FROM RoomMembers Where userid = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userID);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int roomID = rs.getInt("room_id");
                rooms.add(roomID);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return rooms;
    }
    
    public Rooms getRoomInfoByRoomID(int roomID) {
        Rooms room = new Rooms();
        
        try {
            String query = "SELECT * FROM rooms WHERE room_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, roomID);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                room.setRoomID(roomID);
                room.setRoomName(rs.getString("room_name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return room;
    }
    
    public List<Messages> getAllMessageInRoom(int roomID) {
        ArrayList<Messages> messages = new ArrayList<>();
        
        //@ Todo: add code here
        
        return messages;
    }
    
    public List<Users> getAllUsers() {
        ArrayList<Users> users = new ArrayList<>();
        
        return users;
    }
}
