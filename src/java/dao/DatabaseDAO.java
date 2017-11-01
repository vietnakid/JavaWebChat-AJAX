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
import java.sql.Statement;
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
            ResultSet rs = statement.executeQuery(query);
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
            String query = "SELECT * FROM rooms WHERE room_id = 1";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, roomID);
            ResultSet rs = statement.executeQuery(query);
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
    
     public List<Users> getAllAccount() {
        List<Users> ListUser = new ArrayList<Users>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from Users");
            while (rs.next()) {
                Users users = new Users();
                users.setUserID(rs.getInt("userid"));
                users.setUserName(rs.getString("username"));
                users.setDateOfBirth(rs.getDate("date_of_birth"));
                users.setPassword(rs.getString("pw"));
                users.setSex(rs.getString("sex"));
                ListUser.add(users);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ListUser;
    }
    
    
    public Users getAccountById(String username) {
        Users user = new Users();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from users where username=?");
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
               user.setUserID(rs.getInt("userid"));
                user.setUserName(rs.getString("username"));
                user.setDateOfBirth(rs.getDate("date_of_birth"));
                user.setPassword(rs.getString("pw"));
                user.setSex(rs.getString("sex"));
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
    
    
    public Boolean CheckAuthen(String userID, String pw){
        for (int i = 0; i < getAllAccount().size(); i++) {
            String userdb = getAllAccount().get(i).getUserName().trim();
            String pwdb = getAllAccount().get(i).getPassword().trim();
            if(userID.equals(userdb) && pw.equals(pwdb)){
                return true;
            }
        }
        return false;
    }
    
    public void Register(String username,Date DateOfBirth, String pw, String sex) {
        try {
            
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into Users(username,date_of_birth,pw,sex) values (?, ?, ?, ?)");
            // Parameters start with 1
            preparedStatement.setString(1, username);
            preparedStatement.setDate(2, new java.sql.Date(DateOfBirth.getTime()));
            preparedStatement.setString(3, pw);
            preparedStatement.setString(4, sex);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void AddNewBannerWord(String word){
        PreparedStatement pre;
        try {
            pre = connection.prepareStatement("insert into BannedWords(bannedWord) values (?)");
             pre.setString(1, word);
             pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    public List<String> getAllBannerWordst() {
        List<String> ListWord = new ArrayList<String>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from BannedWords");
            while (rs.next()) {
                ListWord.add(rs.getString("bannedWord"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ListWord;
    }
}
