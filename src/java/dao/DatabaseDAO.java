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
    
    public int createNewRoomWithName(String roomName) {
        int roomID = 0;
        try {
            String query = "INSERT INTO Rooms (room_name) VALUES (?); SELECT SCOPE_IDENTITY()";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, roomName.trim());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            while (generatedKeys.next()) {
                roomID = generatedKeys.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return roomID;
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
    
    public void changeNameOfRoom(int roomID, String roomName) {
        try {
            String query = "UPDATE rooms SET room_name = ? WHERE room_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(2, roomID);
            statement.setString(1, roomName);
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteUserFromRoom(int userID, int roomID) {
        try {
            String query = "DELETE FROM RoomMembers WHERE room_id = ? and userid = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, roomID);
            statement.setInt(2, userID);
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteUsersFromRoom(int roomID) {
        try {
            String query = "DELETE FROM RoomMembers WHERE room_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, roomID);
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void createNewMessage(int userId, int RoomId, String content, Date dateUploaded) {
        try {
            String query = "INSERT INTO Messages(userid, room_id, text_content, TimeStamps) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            statement.setInt(2, RoomId);
            statement.setString(3, content.trim());
            statement.setTimestamp(4, new java.sql.Timestamp(dateUploaded.getTime()));
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
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
                message.setTimeUploaded(rs.getTimestamp("TimeStamps"));
                
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
    
    public int Register(String username,Date DateOfBirth, String pw, String sex) {
        int userID = 0;
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into Users(username,date_of_birth,pw,sex) values (?, ?, ?, ?); SELECT SCOPE_IDENTITY()");
            // Parameters start with 1
            preparedStatement.setString(1, username);
            preparedStatement.setDate(2, new java.sql.Date(DateOfBirth.getTime()));
            preparedStatement.setString(3, pw);
            preparedStatement.setString(4, sex);
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            while (generatedKeys.next()) {
                userID = generatedKeys.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userID;
    }
    
    public void deleteUser(int userId) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from Users where userid=?");
            // Parameters start with 1
            preparedStatement.setInt(1, userId);
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
    
    public Messages getNewestMessageInRoom(int roomID) {
        Messages res = new Messages();
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT TOP 1 * FROM Messages WHERE room_id = ? ORDER BY TimeStamps DESC");
            preparedStatement.setInt(1, roomID);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                res.setUserID(rs.getInt("userid"));
                res.setContent(rs.getString("text_content"));
                res.setTimeUploaded(rs.getTimestamp("TimeStamps"));
                res.setRoomID(rs.getInt("room_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
    }
}
