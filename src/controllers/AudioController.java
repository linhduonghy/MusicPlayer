/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;


import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jlgui.basicplayer.BasicController;
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;
import models.Song;

/**
 *
 * @author LinhDV
 * @reference: http://www.it.uu.se/edu/course/homepage/devgui/vt07/material/api/basicplayer3.0/javazoom/jlgui/basicplayer/BasicPlayer.html
 */
public class AudioController extends BasicPlayer {

    // music stream throughout 
    // static 
    private static AudioController instance = null;

    private BasicController controller;

    private ArrayList<Song> playlist = new ArrayList<>();

    private int index = -1;

    private Song current_song;

    private boolean stoped = true;

    private boolean paused = false;

    private boolean opened = false;

    private String kind = "";
    
    public int id_playlist = -1;
    
    private long total_time;
    
    public AudioController() {
        super();
        controller = (BasicController) this;
    }

    public static AudioController getIntance() {
        if (instance == null) {
            instance = new AudioController();
        }
        return instance;
    }   
    
    @Override
    public void play() {
        
        if (playlist.isEmpty() && !isStop()) {
            stop();
            return;
        }
        current_song = playlist.get(index);                
        
        String path = System.getProperty("user.dir") + "\\src\\musics\\" + current_song.getUrl() + ".mp3";
        File file = new File(path);
        
        try {
            open(file);
            opened = true;
            paused = false;
            stoped = false;
            total_time = (Long) SongDetailController.getSongDetail(current_song).get("duration");
            super.play();
        } catch (BasicPlayerException ex) {
            Logger.getLogger(AudioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Map <String, String> sd = SongDetailController.getSongDetail(current_song);
        System.out.println(kind + " " + id_playlist);
        System.out.println("playing " + sd.get("title") + " " + sd.get("author"));
    }

    @Override
    public void pause() {
        try {
            super.pause();
            paused = true;
            opened = false;
            stoped = false;
        } catch (BasicPlayerException ex) {
            Logger.getLogger(AudioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void resume() {
        if (paused) {
            try {
                super.resume();
                opened = true;
                paused = false;
                stoped = false;
            } catch (BasicPlayerException ex) {
                Logger.getLogger(AudioController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void stop() {
        if (opened) {
            try {
                super.stop();
                opened = false;
                paused = false;
                stoped = true;
            } catch (BasicPlayerException ex) {
                Logger.getLogger(AudioController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void addSong(Song s) {
        playlist.add(s);
    }
    
    public void removeSong(Song s) {
        playlist.remove(s);
    }

    public void removeSong(int index) {
        playlist.remove(index);
    }

    public void nextSong() {
        if (playlist.isEmpty()) {
            return;
        }
        index = (index + 1) % playlist.size();
        opened = true;
        paused = false;
        play();

    }

    public void previousSong() {
        if (playlist.isEmpty()) {
            return;
        }
        index = (index - 1 + playlist.size()) % playlist.size();
        opened = true;
        paused = false;
        play();
    }

    public void setVolume(double value) {
        try {
            controller.setGain(value);
        } catch (BasicPlayerException ex) {
            Logger.getLogger(AudioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean isOpen() {
        return opened;
    }

    public boolean isPause() {
        return paused;
    }

    public boolean isStop() {
        return stoped;
    }
    
    public ArrayList<Song> getPlaylist() {
        return playlist;
    }

    public void setPlaylist(ArrayList<Song> playlist) {
        this.playlist = playlist;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Song getCurrent_song() {
        return current_song;
    }

    public void setCurrent_song(Song current_song) {
        this.current_song = current_song;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }
    
    public String getKind() {
        return kind;
    }

    public int getId_playlist() {
        return id_playlist;
    }

    public void setId_playlist(int id_playlist) {
        this.id_playlist = id_playlist;
    } 
   

    public long getTotal_time() {
        return total_time;
    }

    public void setTotal_time(long total_time) {
        this.total_time = total_time;
    }
}
