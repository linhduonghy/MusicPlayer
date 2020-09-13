/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import DatabaseQuery.SingerQuery;
import DatabaseQuery.SongQuery;
import java.awt.BorderLayout;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JPanel;
import models.Playlist;
import models.Singer;
import models.Song;
import models.User;
import views.MainFrame;
import views.MainPanel;
import views.panel.SingerPanel;
import views.panel.MusicsPanel;
import views.panel.MyPlayListPanel;
import views.panel.SearchPanel;
import views.panel.SubPlaylistPanel.PlaylistDetailPanel;
import views.panel.SubSingerPanel.SingerDetailPanel;
import views.panel.TitlePanel;
import views.panel.SubSingerPanel.TitleSingerDetailPanel;

/**
 *
 * @author linhd
 */
public class ViewController {
    
    private MainFrame frame;
    private JPanel main_panel;
    private MainPanel main;
    
    public ViewController(MainFrame frame) {
        this.frame = frame;
        main_panel = frame.getView_panel();
        main = new MainPanel();
    }   
    
    public void setMain_panel(JPanel p) {
        
        main_panel.removeAll();        
        main_panel.setLayout(new BorderLayout());        
        main_panel.add(p);  
        main_panel.revalidate();
        
    }
    
    public JPanel getMain_panel() {
        return main_panel;
    }
    
    public void setMusics(ArrayList<Song> listSong) throws SQLException {
        
        TitlePanel p = new TitlePanel("Bài Hát", "Phát tất cả", frame, listSong, null);
        main.setView(p, new MusicsPanel(frame, listSong));
        
        setMain_panel(main);
        
        System.out.println("add musics panel");
        
    }
    
    public void setSingers(ArrayList <Singer> listSinger) throws SQLException {
        
        TitlePanel p = new TitlePanel("Ca sĩ"); 
        main.setView(p, new SingerPanel(frame, listSinger));
        
        setMain_panel(main);
        
        System.out.println("add singer panel");
    }
    
    public void setSingerDetail(Singer singer) throws SQLException {
        
        TitleSingerDetailPanel tsdp = new TitleSingerDetailPanel();
        
        tsdp.setNamee(singer.getName()); tsdp.setDob(singer.getDob());
        tsdp.setCountry(singer.getCountry());
        
        main.setView(tsdp, new SingerDetailPanel(frame, singer.getListSong()));
        
        setMain_panel(main);
        
        System.out.println("add singer detail panel");
    }

    public void setPlayLists(ArrayList<Playlist> playlists) {
        
        User user = frame.getUser();                   
        
        TitlePanel p = new TitlePanel(frame, user, "My PlayLists");
        main.setView(p, new MyPlayListPanel(user, frame, playlists));
        
        setMain_panel(main);
        
        System.out.println("add playlists panel");
    }
    
    public void setPlayListDetail(Playlist playlist) throws SQLException {
        
        SongQuery query = new SongQuery();
        ArrayList <Song> listSong = query.getSongsByPlaylist(playlist);
        
        TitlePanel p = new TitlePanel("Bài Hát Playlist", "Phát tất cả", frame, listSong, playlist);
        main.setView(p, new PlaylistDetailPanel(frame, playlist, listSong));
                
        setMain_panel(main);
        
        System.out.println("add playlist detail panel");
    }
    
    public void setPlayListDetail(Playlist playlist, ArrayList <Song> listSong) {
        
        
        TitlePanel p = new TitlePanel("Bài Hát Playlist", "Phát tất cả", frame, listSong, playlist);
        main.setView(p, new PlaylistDetailPanel(frame, playlist, listSong));
                
        setMain_panel(main);
        
        System.out.println("add playlist detail panel");
    }
    public void setSearchPanel(String str) throws SQLException {
        
        SongQuery sq = new SongQuery();
        SingerQuery sq1 = new SingerQuery();
        
        ArrayList<Song> listSong = sq.getSongByLikeName(str);
        ArrayList<Singer> listSinger = sq1.getSingerLikeName(str);
        
        TitlePanel p = new TitlePanel("Search result");
        main.setView(p, new SearchPanel(frame, listSong, listSinger));
        setMain_panel(main);
        
        System.out.println("add search panel");
    }
    public void setAllSearchPanel(ArrayList <Object> list, String kind) {
        
        if (kind.equals("Song")) {
            
            ArrayList <Song> listSong = new ArrayList<>();
            for (int i = 0; i < list.size(); ++i) 
                listSong.add((Song) list.get(i));        
                TitlePanel p = new TitlePanel("Search result"); 
                main.setView(p , new SearchPanel(frame, listSong, null));
                setMain_panel(main);
        } else {
            ArrayList <Singer> listSinger = new ArrayList<>();
            for (int i = 0; i < list.size(); ++i) 
                listSinger.add((Singer) list.get(i));
                TitlePanel p = new TitlePanel("Search result"); 
                main.setView(p, new SearchPanel(frame, null, listSinger));
                setMain_panel(main);
        }
    }
}
