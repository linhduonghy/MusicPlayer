/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseQuery;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Playlist;
import models.User;

/**
 *
 * @author linhd
 */
public class UserQuery {

    private DBQuery dbQuery;
    
    public UserQuery() {
        dbQuery = new DBQuery();
    }

    public UserQuery(DBQuery dbQuery) {
        this.dbQuery = dbQuery;
    }
    
    public User getUser(String username, String password)  {
        String sql = "select * from tbl_user "
                    + "where usr = ? and pwd = ?";
        try {
            PreparedStatement pst = dbQuery.getConnection().prepareStatement(sql);
        
            pst.setString(1, username);
            pst.setString(2, password);
            ResultSet resultSet = pst.executeQuery();
                
            // no rows
            if (resultSet.isBeforeFirst() == false) 
                return null;
            
            User user = new User();

            PlaylistQuery pq = new PlaylistQuery(dbQuery);
            
            while (resultSet.next()) {
                user.setUsername(resultSet.getString(1));
                user.setPassword(resultSet.getString(2));
                user.setUnname(resultSet.getString(3));
                user.setIsVip((resultSet.getInt(4) == 1));
                user.setMyPlaylists(pq.getPlaylistsByUser(user));
            }
            
            return user;
        } catch (SQLException ex) {
            Logger.getLogger(UserQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    } 
    public User getUserByPlaylist(Playlist p) throws SQLException {
        
        String sql = "select * from tbl_user "
                + "where usr = " + "'" + p.getUser().getUsername() + "'";
        ResultSet resultSet = dbQuery.query(sql);
        
        User user = new User();
        
        PlaylistQuery pq = new PlaylistQuery(dbQuery);
        while (resultSet.next()) {
            user.setUsername(resultSet.getString(1));
            user.setPassword(resultSet.getString(2));
            user.setUnname(resultSet.getString(3));
            user.setIsVip((resultSet.getInt(4) == 1));
            user.setMyPlaylists(pq.getPlaylistsByUser(user));
        }
        
        return user;
    }   
    
    public User registerUser(String usr, String pass, String name, boolean isVip) {        
        
        if(getUser(usr, pass) != null) {
            return null;
        } 
        
        String sql = "insert into tbl_user(usr,pwd,uname, isVip) values(?,?,?,?)";
                
        try {
            
            PreparedStatement ps = dbQuery.getConnection().prepareStatement(sql);
            System.out.println(usr + " " + pass + " " + name);
            ps.setString(1, usr);
            ps.setString(2, pass);
            ps.setString(3, name);
            ps.setInt(4, isVip ? 1 : 0);
            
            ps.executeUpdate();
            
            User user = new User();
            
            PlaylistQuery pq = new PlaylistQuery(dbQuery);
            user.setUsername(usr);
            user.setPassword(pass);
            user.setUnname(name);
            user.setIsVip(isVip);
            user.setMyPlaylists(pq.getPlaylistsByUser(user));
            
            return user;
            
        } catch (SQLException ex) {
            Logger.getLogger(UserQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
        
    }
    
}
