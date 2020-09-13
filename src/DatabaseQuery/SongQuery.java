/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseQuery;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import models.Playlist;
import models.Singer;
import models.Song;

/**
 *
 * @author linhd
 */
public class SongQuery {

    private DBQuery dbQuery;
    
    public SongQuery() {
        dbQuery = new DBQuery();
    }

    public SongQuery(DBQuery dbQuery) {
        this.dbQuery = dbQuery;
    }
                
    public ArrayList<Song> getAllSongs() throws SQLException {                              
        
        ResultSet resultSet = dbQuery.query("select * from tbl_song");        
        ArrayList <Song> list = new ArrayList<>();
        
        while (resultSet.next()) {
            Song s = new Song();
            s.setId(resultSet.getInt(1));
            s.setName(resultSet.getString(2));           
            s.setUrl(resultSet.getString(3));
            // set Singer
            SingerQuery singerQuery = new SingerQuery(dbQuery);
            s.setSinger(singerQuery.getSingersBySong(s));            
            
            list.add(s);
        }
        
        return list;
        
    }

    public ArrayList<Song> getSongsBySinger(Singer singer) throws SQLException {
        
        String sql = "select * from tbl_song S, tbl_sing_this_song as STS"
                + " where S.IDsong = STS.IDsong and STS.IDsinger = " + String.valueOf(singer.getId());
        ResultSet resultSet = dbQuery.query(sql);
        ArrayList <Song> list = new ArrayList<>();
        
        while (resultSet.next()) {
            Song s = new Song();
            s.setId(resultSet.getInt(1));
            s.setName(resultSet.getString(2));                       
            s.setUrl(resultSet.getString(3));
            // set Singer
            SingerQuery singerQuery = new SingerQuery(dbQuery);
            s.setSinger(singerQuery.getSingersBySong(s));            
            
            list.add(s);
        }
        return list;
    }
    
    public ArrayList<Song> getSongsByPlaylist(Playlist playlist) throws SQLException {
        
        String sql = "select * from tbl_song S, tbl_mylist_this_song LS "
                + "where S.IDsong = LS.IDsong and LS.idlist = " + String.valueOf(playlist.getId());
        
        ResultSet resultSet = dbQuery.query(sql);
        
        ArrayList <Song> listSong = new ArrayList<>();
        
        while (resultSet.next()) {
            Song s = new Song();
            s.setId(resultSet.getInt(1));
            s.setName(resultSet.getString(2));
            s.setUrl(resultSet.getString(3));                                    
            // set Singer
            SingerQuery singerQuery = new SingerQuery(dbQuery);
            s.setSinger(singerQuery.getSingersBySong(s));            
            
            listSong.add(s);
        }
        
        return listSong;
    }
    
    public ArrayList<Song> getSongByLikeName(String name) throws SQLException {
        
        String sql = "select * from tbl_song S "
                + "where S.name_of_song like " + "'%" + name + "%'";
        ResultSet resultSet = dbQuery.query(sql);
        ArrayList <Song> listSong = new ArrayList<>();
        
        while (resultSet.next()) {
            Song s = new Song();
            s.setId(resultSet.getInt(1));
            s.setName(resultSet.getString(2));
            s.setUrl(resultSet.getString(3));
            // set Singer
            SingerQuery singerQuery = new SingerQuery(dbQuery);
            s.setSinger(singerQuery.getSingersBySong(s));            
            
            listSong.add(s);
        }
        
        return listSong;
    }

    public Song getSongById(int id) throws SQLException {
        
        String sql = "select * from tbl_song S "
                + "where S.IDsong = " + String.valueOf(id);
        
        ResultSet resultSet = dbQuery.query(sql);
        
        Song s = new Song();
        while (resultSet.next()) {
            s.setId(resultSet.getInt(1));
            s.setName(resultSet.getString(2));
            s.setUrl(resultSet.getString(3));
            // set Singer
            SingerQuery singerQuery = new SingerQuery(dbQuery);
            s.setSinger(singerQuery.getSingersBySong(s));            
        }        
        
        return s;
    }    
}
