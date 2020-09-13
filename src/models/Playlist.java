/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;


/**
 *
 * @author linhd
 */
public class Playlist {
    private int id;
    private User user;
    private String name;
    private ArrayList<Song> listSong;

    public Playlist() {
    }

    public Playlist(int id, User username, String name, ArrayList<Song> listSong) {
        this.id = id;
        this.user = username;
        this.name = name;
        this.listSong = listSong;
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

    public ArrayList<Song> getPlaylistDetail() {
        return listSong;
    }

    public void setPlaylistDetail(ArrayList<Song> listSong) {
        this.listSong = listSong;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public String toString() {
        return user.getUsername() + " " + name ;
    }
}
