/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseQuery;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Playlist;
import models.Song;
import models.User;

/**
 *
 * @author linhd
 */
public class PlaylistQuery {
    private DBQuery dbQuery;

    public PlaylistQuery() {
        dbQuery = new DBQuery();
    }

    public PlaylistQuery(DBQuery dbQuery) {
        this.dbQuery = dbQuery;
    }
    
    public ArrayList <Playlist> getPlaylistsByUser(User user) throws SQLException {
        
        String sql = "select ML.IDlist, ML.name_of_list from tbl_mylist ML where ML.usr = " + '"' + user.getUsername() + '"' ;
        ResultSet resultSet = dbQuery.query(sql);
        
        ArrayList <Playlist> list = new ArrayList<>();
        
        while (resultSet.next()) {
            Playlist p = new Playlist();
            p.setId(resultSet.getInt(1));
            p.setName(resultSet.getString(2));
            p.setUser(user);
            list.add(p);
        }        
        return list;        
    }
    
    public Playlist getPlaylistByUserAndName(User user, String name) throws SQLException {
        String sql = "select * from tbl_mylist ML"
                + " where ML.name_of_list = " + "'" + name + "'"
                + " and ML.usr = " + '"' + user.getUsername() + '"';
        
        ResultSet resultSet = dbQuery.query(sql);
                       
        if (resultSet.isBeforeFirst() == false) {
            return null;
        }                
                
        return new Playlist(resultSet.getInt(1), null, resultSet.getString(2), null);        
                
    }
    public boolean add_playlist(User user, String name) throws SQLException {
        
        if (getPlaylistByUserAndName(user, name) != null) 
            return false;
        
        String sql = "insert into tbl_mylist(usr, name_of_list) "
                + "values(?,?)";       
            PreparedStatement pst = dbQuery.getConnection().prepareStatement(sql);            
            pst.setString(1, user.getUsername());
            pst.setString(2, name);            
            pst.executeUpdate();            
            return true;
            
    }
    public Playlist getPlaylistById(int id ) throws SQLException {
        
        String sql = "select * from tbl_mylist L "
                + "where L.IDlist = " + String.valueOf(id);
        ResultSet resultSet = dbQuery.query(sql);
        
        Playlist p = new Playlist();
        
        while (resultSet.next()) {
            p.setId(resultSet.getInt(1));
            p.setUser(new User("linhdv", "linhdv", "linhdv", true, null));
            p.setName(resultSet.getString(3));
            p.setPlaylistDetail(new SongQuery(dbQuery).getSongsByPlaylist(p));
        }
        return p;
    }
    
    public boolean addSongToPlayList(Playlist p, Song s) throws SQLException { 
        
        SongQuery sq = new SongQuery(dbQuery);        
        ArrayList <Song> listSong =  sq.getSongsByPlaylist(p);
        for (Song song : listSong) {
            if (song.getId() == s.getId()) 
                return false;            
        }
        
        String sql = "insert into tbl_mylist_this_song(IDlist, IDsong) values(?,?)";
        PreparedStatement preparedStatement = dbQuery.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1, p.getId());
        preparedStatement.setInt(2, s.getId());        
        preparedStatement.executeUpdate();     
        System.out.println("add song to playlist successful");
        return true;
    }

    public boolean removeSongOfPlaylist(Playlist p, Song s) {        
        String sql = "delete from tbl_mylist_this_song "
                + "where IDlist = ? and IDsong = ?";
        PreparedStatement pst = null;
        
        try {
            pst = dbQuery.getConnection().prepareStatement(sql);
            pst.setInt(1, p.getId());
            pst.setInt(2, s.getId());
            pst.executeUpdate();
            System.out.println("delete song in playlist successful");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PlaylistQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;                
    }    
    
    public void deletePlaylist(Playlist p) throws SQLException {
        String sql = "delete from tbl_mylist where IDlist = " + String.valueOf(p.getId());
        
        PreparedStatement pst = dbQuery.getConnection().prepareStatement(sql);
        
        pst.executeUpdate();
        
    }

    public void renamePlaylist(Playlist p, String t) throws SQLException {
        String sql = "update tbl_mylist set name_of_list = '" + t
                + "' where idList = " + String.valueOf(p.getId());
        
        PreparedStatement pst = dbQuery.getConnection().prepareStatement(sql);
        
        pst.executeUpdate();
    }
}
