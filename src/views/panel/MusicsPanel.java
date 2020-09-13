/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.panel;

import DatabaseQuery.PlaylistQuery;
import controllers.AudioController;
import controllers.ListPanelController;
import controllers.SongDetailController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import models.Playlist;
import models.Singer;
import models.Song;
import views.MainFrame;
import views.panel.SubMusicPanel.SongMusicPanel;

/**
 *
 * @author linhd
 */
public class MusicsPanel extends javax.swing.JPanel {

    /**
     * Creates new form MusicPanel
     *
     * @param frame
     * @param listSong
     * @throws java.sql.SQLException
     */
    public MusicsPanel(MainFrame frame, ArrayList<Song> listSong) throws SQLException {
        initComponents();
        setLayout(new BorderLayout());
        JScrollPane sc = new JScrollPane(createListMusic(frame, listSong));
        add(sc);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 427, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 282, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    private JPanel createListMusic(MainFrame frame, ArrayList<Song> listSong) throws SQLException {
        JPanel panel = new JPanel();

        // list song panel
        ArrayList<SongMusicPanel> list = new ArrayList<>();

        // init song panel 
        // add to list
        for (Song song : listSong) {
            SongMusicPanel s = new SongMusicPanel(frame);
            s.setTextName(song.getName());

            // set Singer
            StringBuilder str = new StringBuilder();
            for (Singer singer : song.getSinger()) {
                str.append(singer.getName()).append(", ");
            }
            if (str.length() != 0) {
                s.setSinger(str.substring(0, str.length() - 2));
            } else {
                s.setSinger("");
            }

            list.add(s);

        }

        panel.setLayout(new GridLayout(listSong.size(), 1, 0, 0));

        
        // add list song to main panel
        // add event general list panel
        for (int i = 0; i < list.size(); ++i) {

            // add song panel to main panel
            SongMusicPanel p1 = list.get(i);
            if (i % 2 == 0) {
                p1.setBackground(new Color(41, 41, 39));

            }
            panel.add(p1);

            // add event list panel
            ListPanelController lpc = new ListPanelController(frame, p1);
            lpc.init();

            PlayButttonClickEvent e = new PlayButttonClickEvent(frame, listSong, listSong.get(i), i);
            p1.getPlay().addMouseListener(e);

            MouseDoubleClickEvent pme = new MouseDoubleClickEvent(frame, listSong, listSong.get(i), i);
            p1.addMouseListener(pme);
        }

        // list menubar optional list
        ArrayList<JMenuBar> listMenu = new ArrayList<>();

        // set event add song to playlist
        for (int i = 0; i < list.size(); ++i) {

            SongMusicPanel p1 = list.get(i);
            Song s = listSong.get(i);
            // get playlists by user
            PlaylistQuery pq = new PlaylistQuery();
            ArrayList<Playlist> playlists = pq.getPlaylistsByUser(frame.getUser());

            // set event add playlist   
            Color c = p1.getBackground();
            JPanel add_playlist = p1.getAdd_playlist();
            JPanel chooseList = p1.getMenu();
            add_playlist.setBackground(c);
            chooseList.setBackground(c);

            /// menu optional list
            JMenuBar menubar = new JMenuBar();
            listMenu.add(menubar);
            p1.add(menubar);
            menubar.setVisible(false);

            // set view optional list when click button add playlist
            add_playlist.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    add_playlist.setOpaque(true);
                    add_playlist.setBackground(Color.gray);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    add_playlist.setOpaque(false);
                }

                @Override
                public void mouseClicked(MouseEvent e) {

                    // load playing song while add playlist
                                        
                    // if playlists not empty
                    if (list != null && !list.isEmpty()) {
                        // set visible menubar when clicked add btn
                        menubar.setVisible(true);
                        menubar.removeAll();
                        menubar.setBounds(p1.getMenu().getBounds());

                        JMenu menu = new JMenu("Choose");
                        menubar.add(menu);

                        for (Playlist p : playlists) {
                            JMenuItem menuItem = new JMenuItem(p.getName());
                            menu.add(menuItem);
                            // query database when clicked item list
                            menuItem.addActionListener((ActionEvent e1) -> {
                                menubar.setVisible(false);
                                PlaylistQuery query = new PlaylistQuery();
                                try {
                                    boolean ok = query.addSongToPlayList(p, s);
                                    
                                    if (!ok) {
                                        JOptionPane.showMessageDialog(frame.getView_panel(), "Bài hát đã tồn tại trong playlist "
                                                + p.getName());
                                    } else {
                                        AudioController audio = AudioController.getIntance();                                        
                                        if (audio.getKind().equals("Playlist") && audio.getId_playlist() == p.getId()) {
                                            audio.addSong(s);
                                        }
                                        JOptionPane.showMessageDialog(frame.getView_panel(), "Thêm bài hát thành công");
                                    }
                                } catch (SQLException ex) {
                                }
                            });

                        }
                    }
                }
            });
        }

        for (int i = 0; i < list.size(); ++i) {
            SongMusicPanel p = list.get(i);
            // hidden all menubar when clicked main panel
            p.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    for (JMenuBar menubar : listMenu) {
                        menubar.setVisible(false);
                    }
                }
            });
        }
        for (int i = 0; i < list.size(); ++i) {
            SongMusicPanel p = list.get(i);
            int j = i;
            // hidden all menubar when clicked main panel
            p.getAdd_playlist().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    for (int k = 0; k < listMenu.size(); ++k) {
                        if (k != j) {
                            listMenu.get(k).setVisible(false);
                        }
                    }
                }
            });
        }
        return panel;

    }

    private class PlayButttonClickEvent extends MouseAdapter {

        private MainFrame frame;
        private ArrayList<Song> listSong;
        private Song song;
        private int cur;

        public PlayButttonClickEvent(MainFrame frame, ArrayList<Song> listSong, Song s, int cur) {
            this.frame = frame;
            this.listSong = listSong;
            this.song = s;
            this.cur = cur;
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            JPanel p = (JPanel) e.getSource();
            p.setOpaque(true);
            p.setBackground(Color.gray);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            JPanel p = (JPanel) e.getSource();
            p.setOpaque(false);
        }

        @Override
        public void mouseClicked(MouseEvent e) {

            frame.getSong_playing_panel().setVisible(true);                                    
            
            AudioController audio = AudioController.getIntance();                        
            audio.setPlaylist(listSong);
            audio.setCurrent_song(song);
            audio.setIndex(cur);
            audio.setKind("Music");
            audio.setId_playlist(-1);
            audio.play();
            
            frame.getPlay_label().setIcon(new ImageIcon(getClass().getResource("/images/icons8_pause_30px.png")));
            
            Map <String, String> songDetail = SongDetailController.getSongDetail(song);
            
            frame.getSong_playing_name().setText(songDetail.get("title"));
            frame.getSong_playing_singer().setText(songDetail.get("author"));
        }
    }

    private class MouseDoubleClickEvent extends MouseAdapter {

        private MainFrame frame;
        private ArrayList<Song> listSong;
        private Song song;
        private int cur;

        public MouseDoubleClickEvent(MainFrame frame, ArrayList<Song> listSong, Song s, int cur) {
            this.frame = frame;
            this.listSong = listSong;
            this.song = s;
            this.cur = cur;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() == 2) {

                frame.getSong_playing_panel().setVisible(true);                                    
            
                AudioController audio = AudioController.getIntance();                        
                audio.setPlaylist(listSong);
                audio.setCurrent_song(song);
                audio.setIndex(cur);
                audio.setKind("Music");
                audio.setId_playlist(-1);
                audio.play();

                frame.getPlay_label().setIcon(new ImageIcon(getClass().getResource("/images/icons8_pause_30px.png")));

                Map <String, String> songDetail = SongDetailController.getSongDetail(song);

                frame.getSong_playing_name().setText(songDetail.get("title"));
                frame.getSong_playing_singer().setText(songDetail.get("author"));
            }
        }
    }
}
