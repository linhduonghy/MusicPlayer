/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javazoom.jlgui.basicplayer.BasicController;
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;
import models.Singer;
import models.Song;
import views.MainFrame;

/**
 *
 * @author linhd
 * BasicPlayer
 * https://github.com/whamtet/BasicPlayer/blob/master/src/javazoom/jlgui/basicplayer/BasicPlayer.java
 */
public class PlayingController {
    private MainFrame frame;
    private ArrayList <Song> listSong;
    private Song current_song;
    private JLabel name, artist, play_label, next_label, previous_label;
    JPanel play_song, next_song, previous_song;
    private int cur;
    private boolean flag;    
    private BasicPlayer player;
    private BasicController con;
    private FileInputStream fis;
    private BufferedInputStream buffer;
    private File file;
    private String kind;
    
    public PlayingController() {
    
    }
    
    public PlayingController(MainFrame frame/*, BasicPlayer player*/) {
        this.frame = frame;
        this.player = player;
        
        this.name = frame.getSong_playing_name();
        this.artist = frame.getSong_playing_singer();        
        this.play_label = frame.getPlay_label();        
        this.play_song = frame.getSong_playing_play();
        this.next_song = frame.getSong_playing_next();
        this.previous_song = frame.getSong_playing_previous();        
        this.flag = false;   
        
        SongPlayingEvent spe = new SongPlayingEvent();
        play_song.addMouseListener(spe);
        next_song.addMouseListener(spe);
        previous_song.addMouseListener(spe); 
    } 
    
    /*public PlayingController(MainFrame frame, ArrayList<Song> listSong, Song current_song, int cur,
            BasicPlayer player) {
        this.frame = frame;
        this.listSong = listSong;        
        this.current_song = current_song;  
        this.cur = cur;
        
        this.name = frame.getSong_playing_name();
        this.artist = frame.getSong_playing_singer();
        
        this.play_label = frame.getPlay_label();
        
        this.play_song = frame.getSong_playing_play();
        this.next_song = frame.getSong_playing_next();
        this.previous_song = frame.getSong_playing_previous();
        
        this.flag = false;   
        this.player = player;
        
        SongPlayingEvent spe = new SongPlayingEvent();
        play_song.addMouseListener(spe);
        next_song.addMouseListener(spe);
        previous_song.addMouseListener(spe); 
        
    }    */    
    
    public ArrayList<Object> getSongPlaying() {
        ArrayList<Object> ans = new ArrayList<>();
        ans.add(listSong);
        ans.add(current_song);
        ans.add(cur);
        
        return ans;
    }
    public void play()  {
        
       
        current_song = listSong.get(cur);
        name.setText(current_song.getName());
        
        // set Singer
            StringBuilder str = new StringBuilder();
            String s = "";
            for (Singer singer : current_song.getSinger())
                str.append(singer.getName()).append(", ");                         
            if (str.length() != 0)
                s = str.substring(0, str.length() - 2);    
            
            
        artist.setText(s);  
        
        con = (BasicController) player;
        String path = System.getProperty("user.dir") + "\\src\\musics\\" + current_song.getUrl() + ".mp3";
        file = new File(path);
        try {
            fis = new FileInputStream(new File(path));
            buffer = new BufferedInputStream(fis);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PlayingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /*AudioFileFormat fileFormat = AudioSystem.getAudioFileFormat(new File(path));
        if (fileFormat instanceof TAudioFileFormat) {
            Map<?, ?> properties = ((TAudioFileFormat) fileFormat).properties();
            String key = "duration";
            Long microseconds = (Long) properties.get(key);
            int mili = (int) (microseconds / 1000);
            int sec = (mili / 1000) % 60;
            int min = (mili / 1000) / 60;
            System.out.println("time = " + min + ":" + sec);
        } else {
            throw new UnsupportedAudioFileException();
        
        /*AudioFileFormat fileFormat = AudioSystem.getAudioFileFormat(new File(path));
        if (fileFormat instanceof TAudioFileFormat) {
            Map<?, ?> properties = ((TAudioFileFormat) fileFormat).properties();
            String key = "duration";
            Long microseconds = (Long) properties.get(key);
            int mili = (int) (microseconds / 1000);
            int sec = (mili / 1000) % 60;
            int min = (mili / 1000) / 60;
            System.out.println("time = " + min + ":" + sec);
        } else {
            throw new UnsupportedAudioFileException();
        }*/
        
        
        
        Timer timer = new Timer();
        
        TimerTask task = new TimerTask() {
            @Override
            public void run() {                
                try {
                    System.out.println(fis.available());
                } catch (IOException ex) {
                    Logger.getLogger(PlayingController.class.getName()).log(Level.SEVERE, null, ex);
                }
                switch (player.getStatus()) {
                    case BasicPlayer.STOPPED:
                        System.out.print("next song ");
                        cur = (cur + 1) % (listSong.size());
                        play();
                        break;
                    case BasicPlayer.PAUSED:
                        System.out.print("pause ");
                        cancel();
                        break;
                    default:
                        System.out.println("playing " + current_song.getName() + " " + kind);
                        break;
                }
            }
        };
                        
        timer.scheduleAtFixedRate(task, 0, 1000);
        
        if (player.getStatus() == BasicPlayer.PLAYING)
            try {
                con.stop();
        } catch (BasicPlayerException ex) {
            Logger.getLogger(PlayingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            
            con.open(buffer);
            
        } catch (BasicPlayerException ex) {
            Logger.getLogger(PlayingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            con.play();            
                        
        } catch (BasicPlayerException ex) {
            Logger.getLogger(PlayingController.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        play_label.setIcon(new ImageIcon(getClass().getResource("/images/icons8_pause_30px.png")));
        flag = true; 
        
    }     

    public void removeSongOfPlaylist(ArrayList<Song> listSong, Song s, int pos) {
        if (kind != null && kind.equals("playlist")) {            
            if (this.cur == pos) {
                this.cur = ((this.cur - 1) % listSong.size());
                current_song = listSong.get(this.cur);
                this.listSong = listSong;
            } else {
                this.listSong = listSong;
            }                                             
        }
    }
    public void addSongToPlaylist(Song s) {
        if (kind != null && kind.equals("playlist")) {
            this.listSong.add(s);
        }
    }
    private class SongPlayingEvent extends MouseAdapter {
        Color color;
        @Override
        public void mouseEntered(MouseEvent e){
            JPanel p = (JPanel) e.getSource(); 
            color = p.getBackground();
            p.setBackground(new Color(193, 199, 199));
        }
        
        @Override
        public void mouseExited(MouseEvent e){
            JPanel p = (JPanel) e.getSource();      
            p.setBackground(color);
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {              
            JPanel p = (JPanel) e.getSource();
            if (p.equals(play_song)) {
                if (!flag) {
                    Timer timer = new Timer();
        
                    TimerTask task = new TimerTask() {
                        @Override
                        public void run() {    
                            System.out.print("running ");
                            if (player.getStatus() == BasicPlayer.STOPPED) {
                                System.out.print("next song ");
                                cur = (cur + 1) % (listSong.size());
                                play();
                            } 
                            
                            else if (player.getStatus() == BasicPlayer.PAUSED) {
                                System.out.print("pause ");
                                cancel();
                            } 
                        }
                    };
                        
                    timer.scheduleAtFixedRate(task, 100, 1000);
                    
                    if (player.getStatus() == BasicPlayer.PAUSED) 
                        try {
                            con.resume();                            
                    } catch (BasicPlayerException ex) {
                        Logger.getLogger(PlayingController.class.getName()).log(Level.SEVERE, null, ex);
                    } else {
                        try {
                            con.play();
                        } catch (BasicPlayerException ex) {
                            Logger.getLogger(PlayingController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    play_label.setIcon(new ImageIcon(getClass().getResource("/images/icons8_pause_30px.png")));
                    flag = true;
                                                                           
                } else {                        
                    try {
                        con.pause();
                        
                    } catch (BasicPlayerException ex) {
                        Logger.getLogger(PlayingController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    play_label.setIcon(new ImageIcon(getClass().getResource("/images/icons8_play_30px_1.png")));
                    flag = false;                    
                } 
            } else if (p.equals(next_song)) {
                int id = (cur + 1) % (listSong.size());
                cur = id;
                play();                
                
            } else if (p.equals(previous_song)){
                int id = (cur - 1 + listSong.size()) % listSong.size();
                cur = id;
                play();
            } 
            frame.revalidate();            
        }
    }

    public ArrayList<Song> getListSong() {
        return listSong;
    }

    public void setListSong(ArrayList<Song> listSong) {
        this.listSong = listSong;
    }

    public Song getCurrent_song() {
        return current_song;
    }

    public void setCurrent_song(Song current_song) {
        this.current_song = current_song;
    }

    public int getCur() {
        return cur;
    }

    public void setCur(int cur) {
        this.cur = cur;
    }
    
    public void setKind(String kind) {
        this.kind = kind;
    }
    
    public String getKind() {
        return this.kind;
    }
}
 