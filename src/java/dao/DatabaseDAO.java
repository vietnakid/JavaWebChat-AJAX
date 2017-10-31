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
        try {
            String query = "INSERT INTO RoomMembers (room_id, userid) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, roomID);
            statement.setInt(2, userID);
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteUserFromRoom(int userID, int roomID) {
        //@ Todo: add code here
    }
    
    public void createNewMessage(int userId, int RoomId, String content, Date dateUploaded) {
        try {
            String query = "INSERT INTO Messages(userid, room_id, text_content, TimeStamps) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            statement.setInt(2, RoomId);
            statement.setString(3, content);
            statement.setDate(4, new java.sql.Date(dateUploaded.getTime()));
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void createNewUser(String userName, Date dateOfBirth, String password, String sex) {
        //@ Todo: add code here
    }
    
    public Users getUserInfo(int userID) {
        Users user = new Users();
        
        try {
            String query = "SELECT * FROM Users WHERE userid = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userID);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                user.setUserID(rs.getInt("userid"));
                user.setUserName(rs.getString("username"));
                user.setDateOfBirth(rs.getDate("date_of_birth"));
                user.setSex(rs.getString("sex"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return user;
    }
    
    public ArrayList<Integer> getRoomIDsWithUserID(int userID) {
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
    
    public ArrayList<Messages> getAllMessageInRoom(int roomID) {
        ArrayList<Messages> messages = new ArrayList<>();
        
        try {
            String query = "SELECT * FROM Messages WHERE room_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, roomID);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Messages message = new Messages();
                message.setMessageID(rs.getInt("Messages_id"));
                message.setRoomID(rs.getInt("room_id"));
                message.setUserID(rs.getInt("userid"));
                message.setContent(rs.getString("text_content"));
                message.setTimeUploaded(rs.getDate("TimeStamps"));
                
                messages.add(message);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return messages;
    }
    
    public ArrayList<Users> getAllUsers() {
        ArrayList<Users> users = new ArrayList<>();
        
        try {
            String query = "SELECT * FROM Users";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Users user = new Users();
                user.setUserID(rs.getInt("userid"));
                user.setUserName(rs.getString("username"));
                user.setDateOfBirth(rs.getDate("date_of_birth"));
                user.setSex(rs.getString("sex"));
                users.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return users;
    }
    
    public boolean inUserIdInRoomId(int userID, int RoomID) {
        try {
            String query = "SELECT * FROM RoomMembers WHERE (room_id = ? AND userid = ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, RoomID);
            statement.setInt(2, userID);
            
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
