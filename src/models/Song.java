/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author linhd
 */
public class Song {
    
    private int id;
    private String name;
    private ArrayList<Singer> singer;
    private ArrayList<Playlist> playlists;
    private String url;
    
    public Song() {
        
    }

    public Song(int id, String name, ArrayList<Singer> singer, ArrayList<Playlist> playlists, String url) {
        this.id = id;
        this.name = name;
        this.singer = singer;
        this.playlists = playlists;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Singer> getSinger() {
        return singer;
    }

    public void setSinger(ArrayList<Singer> singer) {
        this.singer = singer;
    }

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(ArrayList<Playlist> playlists) {
        this.playlists = playlists;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Song{" + "id=" + id + ", name=" + name + ", singer=" + singer + ", playlists=" + playlists + ", url=" + url + '}';
    }

    
}
