/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseQuery;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import models.Singer;
import models.Song;
/**
 *
 * @author linhd
 */
public class SingerQuery {

    private DBQuery dbQuery;
    
    public SingerQuery() {
        dbQuery = new DBQuery();
    }

    public SingerQuery(DBQuery dbQuery) {
        this.dbQuery = dbQuery;
    }
        
    public ArrayList <Singer> getAllSingers() throws SQLException {
        
        String sql = "select * from tbl_singer";
        
        ResultSet resultSet = dbQuery.query(sql);
        
        ArrayList<Singer> listSinger = new ArrayList<>();
        SongQuery s = new SongQuery(dbQuery);
        
        while (resultSet.next()) {
            Singer singer =  new Singer();
            singer.setId(resultSet.getInt(1));
            singer.setName(resultSet.getString(2));
            singer.setCompany(resultSet.getString(3));
            singer.setCountry(resultSet.getString(4));
            singer.setDob(resultSet.getString(5));
            singer.setListSong(s.getSongsBySinger(singer));
            listSinger.add(singer);
        }
        
        return listSinger;
    }
    
    public ArrayList <Singer> getSingersBySong(Song s) throws SQLException{
        
        String sql = "select * from tbl_singer S, tbl_sing_this_song STS"
                + " where S.IDsinger = STS.IDsinger and STS.IDSong = " + String.valueOf(s.getId());
        ResultSet resultSet = dbQuery.query(sql);
        
        ArrayList<Singer> listSinger = new ArrayList<>();
                
        SongQuery query = new SongQuery(dbQuery);
        
        while (resultSet.next()) {
            Singer singer =  new Singer();
            singer.setId(resultSet.getInt(1));
            singer.setName(resultSet.getString(2));
            singer.setCompany(resultSet.getString(3));
            singer.setCountry(resultSet.getString(4));
            
            //singer.setListSong(query.getSongsBySinger(singer.getId()));
            
            singer.setDob(resultSet.getString(5));
            
            listSinger.add(singer);
        }
        return listSinger;      
        
    }
    
    public ArrayList <Singer> getSingerLikeName(String name) throws SQLException {
        
        String sql = "select * from tbl_singer S "
                + "where S.name_of_singer like " + "'%" + name + "%'";
        
        ResultSet resultSet = dbQuery.query(sql);
        
        ArrayList<Singer> listSinger = new ArrayList<>();
        SongQuery s = new SongQuery(dbQuery);
        
        while (resultSet.next()) {
            Singer singer =  new Singer();
            singer.setId(resultSet.getInt(1));
            singer.setName(resultSet.getString(2));
            singer.setCompany(resultSet.getString(3));
            singer.setCountry(resultSet.getString(4));
            singer.setDob(resultSet.getString(5));
            singer.setListSong(s.getSongsBySinger(singer));
            listSinger.add(singer);
        }
        return listSinger;
    }
}
