/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author linhd
 */
public class User {
    private String username;
    private String password;
    private String unname;
    private boolean isVip;
    ArrayList<Playlist> myPlaylists;

    public User() {
    }

    public User(String username, String password, String unname, boolean isVip, ArrayList<Playlist> myPlaylists) {
        this.username = username;
        this.password = password;
        this.unname = unname;
        this.isVip = isVip;
        this.myPlaylists = myPlaylists;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUnname() {
        return unname;
    }

    public void setUnname(String unname) {
        this.unname = unname;
    }

    public boolean isIsVip() {
        return isVip;
    }

    public void setIsVip(boolean isVip) {
        this.isVip = isVip;
    }

    public ArrayList<Playlist> getMyPlaylists() {
        return myPlaylists;
    }

    public void setMyPlaylists(ArrayList<Playlist> myPlaylists) {
        this.myPlaylists = myPlaylists;
    }
    
    @Override
    public String toString() {
        return username;
    }
    
}
