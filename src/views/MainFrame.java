package views;

import DatabaseQuery.SongQuery;
import controllers.AudioController;
import controllers.FrameDisplayController;
import controllers.MenuController;
import controllers.SongDetailController;
import controllers.Utils;
import controllers.ViewController;
import java.awt.Color;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javazoom.jlgui.basicplayer.BasicController;
import javazoom.jlgui.basicplayer.BasicPlayerEvent;
import javazoom.jlgui.basicplayer.BasicPlayerListener;
import models.Song;
import models.User;

/**
 *
 * @author Linh Duong Van
 */
public class MainFrame extends javax.swing.JFrame {
    
    private User user; 
    private AudioController audio;
    /**
     * Creates new form MainFrame
     * @param user
     */
    public MainFrame(User user) throws SQLException  {
        
        initComponents();
        setLocationRelativeTo(null);
        
        this.user = user;                      
        audio = AudioController.getIntance();       
        
        FrameDisplayController fdec = new FrameDisplayController(this);                
        
        MenuController event = new MenuController(this);        
        event.init();           
             
        ViewController view = new ViewController(this);
        SongQuery query = new SongQuery();
        view.setMusics(query.getAllSongs());                         
        
        PlayingPanelEvent e = new PlayingPanelEvent();        
        song_playing_play.addMouseListener(e);
        song_playing_next.addMouseListener(e);
        song_playing_previous.addMouseListener(e);        
        dispose_playing_panel.addMouseListener(e);
        
        audio.setVolume(0.5);
        // add event volume
        volume_slider.addChangeListener((ChangeEvent e1) -> {
            JSlider volume = (JSlider) e1.getSource();
            double value = (double) volume.getValue() / 100;
            audio.setVolume(value);
        });  
        
        duration.setMaximum(1000000000);
        
        // add event audio
        audio.addBasicPlayerListener(new PlayerEvent()); 
        
        
        song_playing_panel.setVisible(false);
    }
    
    public class PlayerEvent implements BasicPlayerListener {
        @Override
        public void opened(Object o, Map map) {

        }

        @Override
        public void progress(int i, long microsecond, byte[] bytes, Map map) {
            time.setText(Utils.getMinutesRapp(microsecond) + "/" + Utils.getMinutesRapp(audio.getTotal_time()));
            long total_time = audio.getTotal_time();
            double value = (double) microsecond / total_time * 1000000000;
            duration.setValue((int) value);
        }

        @Override
        public void stateUpdated(BasicPlayerEvent e) {
            // action play, pause, next, prv, eom
            if (e.getCode() == BasicPlayerEvent.EOM) {
                audio.nextSong();
                Song s = audio.getCurrent_song();
                Map songDetail = SongDetailController.getSongDetail(s);
                song_playing_name.setText((String) songDetail.get("title"));
                song_playing_singer.setText((String) songDetail.get("author"));
                System.out.println("auto next song");
            }
        }

        @Override
        public void setController(BasicController bc) {

        }
    };
    private class PlayingPanelEvent extends MouseAdapter {
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
            AudioController audio = AudioController.getIntance();
            if (e.getSource().equals(dispose_playing_panel)) {
                song_playing_panel.setVisible(false);
            } else if (e.getSource().equals(song_playing_play)) {                
                if (audio.isOpen()) {                    
                    audio.pause();                    
                    play_label.setIcon(new ImageIcon(getClass().getResource("/images/icons8_play_30px_1.png")));                    
                } else if (audio.isPause()) {
                    audio.resume();
                    play_label.setIcon(new ImageIcon(getClass().getResource("/images/icons8_pause_30px.png")));
                }
            } else if (e.getSource().equals(song_playing_next)) {
                audio.nextSong();
                System.out.println("next song");
                
            } else {
                audio.previousSong();     
                System.out.println("previous song");
            }
            Song s = audio.getCurrent_song();
            Map songDetail = SongDetailController.getSongDetail(s);
            song_playing_name.setText((String) songDetail.get("title"));
            song_playing_singer.setText((String) songDetail.get("author"));
        }
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JPanelRoot = new javax.swing.JPanel();
        menu_panel = new javax.swing.JPanel();
        menu_display_panel = new javax.swing.JPanel();
        menu_display_icon_panel = new javax.swing.JPanel();
        menu_display_label = new javax.swing.JLabel();
        menu_display_name_panel = new javax.swing.JPanel();
        music_menu_panel = new javax.swing.JPanel();
        music_icon_panel = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        music_name_panel = new javax.swing.JPanel();
        music_name_label = new javax.swing.JLabel();
        playlist_menu_panel = new javax.swing.JPanel();
        playlist_icon_panel = new javax.swing.JPanel();
        playlist_icon = new javax.swing.JLabel();
        playlist_name_panel = new javax.swing.JPanel();
        playlist_name_label = new javax.swing.JLabel();
        singer_menu_panel = new javax.swing.JPanel();
        singer_icon_panel = new javax.swing.JPanel();
        singer_icon = new javax.swing.JLabel();
        singer_name_panel = new javax.swing.JPanel();
        singer_name_label = new javax.swing.JLabel();
        search_menu_panel = new javax.swing.JPanel();
        search_icon_panel = new javax.swing.JPanel();
        search_icon = new javax.swing.JLabel();
        search_text_panel = new javax.swing.JPanel();
        search_text_label = new javax.swing.JTextField();
        s_label = new javax.swing.JLabel();
        logout = new javax.swing.JPanel();
        logout_text = new javax.swing.JPanel();
        singer_name_label2 = new javax.swing.JLabel();
        logout_icon = new javax.swing.JPanel();
        singer_icon2 = new javax.swing.JLabel();
        view_panel = new javax.swing.JPanel();
        control_display_panel = new javax.swing.JPanel();
        exit_panel = new javax.swing.JPanel();
        exit_label = new javax.swing.JLabel();
        maximize_panel = new javax.swing.JPanel();
        maximize_label = new javax.swing.JLabel();
        minimize_panel = new javax.swing.JPanel();
        minimize_label = new javax.swing.JLabel();
        dragFrame_panel = new javax.swing.JPanel();
        title_panel = new javax.swing.JPanel();
        title_label = new javax.swing.JLabel();
        song_playing_panel = new javax.swing.JPanel();
        song_playing_control = new javax.swing.JPanel();
        song_playing_play = new javax.swing.JPanel();
        play_label = new javax.swing.JLabel();
        song_playing_next = new javax.swing.JPanel();
        next_label = new javax.swing.JLabel();
        song_playing_previous = new javax.swing.JPanel();
        previous_label = new javax.swing.JLabel();
        song_playing_name = new javax.swing.JLabel();
        song_playing_singer = new javax.swing.JLabel();
        dispose_playing_panel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        volume_slider = new javax.swing.JSlider();
        duration = new javax.swing.JProgressBar();
        time = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("Music Player"); // NOI18N
        setUndecorated(true);

        JPanelRoot.setBackground(new java.awt.Color(51, 51, 51));
        JPanelRoot.setForeground(new java.awt.Color(255, 255, 255));

        menu_panel.setBackground(new java.awt.Color(51, 51, 51));

        menu_display_panel.setBackground(new java.awt.Color(51, 51, 51));

        menu_display_icon_panel.setBackground(new java.awt.Color(51, 51, 51));

        menu_display_label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_menu_30px.png"))); // NOI18N

        javax.swing.GroupLayout menu_display_icon_panelLayout = new javax.swing.GroupLayout(menu_display_icon_panel);
        menu_display_icon_panel.setLayout(menu_display_icon_panelLayout);
        menu_display_icon_panelLayout.setHorizontalGroup(
            menu_display_icon_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menu_display_icon_panelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(menu_display_label)
                .addGap(10, 10, 10))
        );
        menu_display_icon_panelLayout.setVerticalGroup(
            menu_display_icon_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menu_display_icon_panelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(menu_display_label)
                .addGap(10, 10, 10))
        );

        menu_display_name_panel.setBackground(new java.awt.Color(51, 51, 51));
        menu_display_name_panel.setOpaque(false);

        javax.swing.GroupLayout menu_display_name_panelLayout = new javax.swing.GroupLayout(menu_display_name_panel);
        menu_display_name_panel.setLayout(menu_display_name_panelLayout);
        menu_display_name_panelLayout.setHorizontalGroup(
            menu_display_name_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 160, Short.MAX_VALUE)
        );
        menu_display_name_panelLayout.setVerticalGroup(
            menu_display_name_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout menu_display_panelLayout = new javax.swing.GroupLayout(menu_display_panel);
        menu_display_panel.setLayout(menu_display_panelLayout);
        menu_display_panelLayout.setHorizontalGroup(
            menu_display_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menu_display_panelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(menu_display_icon_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(menu_display_name_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        menu_display_panelLayout.setVerticalGroup(
            menu_display_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menu_display_panelLayout.createSequentialGroup()
                .addGroup(menu_display_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(menu_display_icon_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menu_display_name_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        music_menu_panel.setBackground(new java.awt.Color(51, 51, 51));

        music_icon_panel.setBackground(new java.awt.Color(51, 51, 51));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_musical_notes_30px.png"))); // NOI18N

        javax.swing.GroupLayout music_icon_panelLayout = new javax.swing.GroupLayout(music_icon_panel);
        music_icon_panel.setLayout(music_icon_panelLayout);
        music_icon_panelLayout.setHorizontalGroup(
            music_icon_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(music_icon_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        music_icon_panelLayout.setVerticalGroup(
            music_icon_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, music_icon_panelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel10)
                .addGap(10, 10, 10))
        );

        music_name_panel.setBackground(new java.awt.Color(51, 51, 51));

        music_name_label.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        music_name_label.setForeground(new java.awt.Color(255, 255, 255));
        music_name_label.setText("Bài Hát");

        javax.swing.GroupLayout music_name_panelLayout = new javax.swing.GroupLayout(music_name_panel);
        music_name_panel.setLayout(music_name_panelLayout);
        music_name_panelLayout.setHorizontalGroup(
            music_name_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(music_name_panelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(music_name_label)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        music_name_panelLayout.setVerticalGroup(
            music_name_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, music_name_panelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(music_name_label)
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout music_menu_panelLayout = new javax.swing.GroupLayout(music_menu_panel);
        music_menu_panel.setLayout(music_menu_panelLayout);
        music_menu_panelLayout.setHorizontalGroup(
            music_menu_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(music_menu_panelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(music_icon_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(music_name_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        music_menu_panelLayout.setVerticalGroup(
            music_menu_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(music_menu_panelLayout.createSequentialGroup()
                .addGroup(music_menu_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(music_icon_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(music_name_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        playlist_menu_panel.setBackground(new java.awt.Color(51, 51, 51));

        playlist_icon_panel.setBackground(new java.awt.Color(51, 51, 51));

        playlist_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_playlist_30px_1.png"))); // NOI18N

        javax.swing.GroupLayout playlist_icon_panelLayout = new javax.swing.GroupLayout(playlist_icon_panel);
        playlist_icon_panel.setLayout(playlist_icon_panelLayout);
        playlist_icon_panelLayout.setHorizontalGroup(
            playlist_icon_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(playlist_icon_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(playlist_icon)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        playlist_icon_panelLayout.setVerticalGroup(
            playlist_icon_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, playlist_icon_panelLayout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(playlist_icon)
                .addGap(10, 10, 10))
        );

        playlist_name_panel.setBackground(new java.awt.Color(51, 51, 51));

        playlist_name_label.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        playlist_name_label.setForeground(new java.awt.Color(255, 255, 255));
        playlist_name_label.setText("My Playlist");

        javax.swing.GroupLayout playlist_name_panelLayout = new javax.swing.GroupLayout(playlist_name_panel);
        playlist_name_panel.setLayout(playlist_name_panelLayout);
        playlist_name_panelLayout.setHorizontalGroup(
            playlist_name_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(playlist_name_panelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(playlist_name_label)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        playlist_name_panelLayout.setVerticalGroup(
            playlist_name_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, playlist_name_panelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(playlist_name_label, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout playlist_menu_panelLayout = new javax.swing.GroupLayout(playlist_menu_panel);
        playlist_menu_panel.setLayout(playlist_menu_panelLayout);
        playlist_menu_panelLayout.setHorizontalGroup(
            playlist_menu_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(playlist_menu_panelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(playlist_icon_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(playlist_name_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        playlist_menu_panelLayout.setVerticalGroup(
            playlist_menu_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(playlist_menu_panelLayout.createSequentialGroup()
                .addGroup(playlist_menu_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(playlist_icon_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(playlist_name_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        singer_menu_panel.setBackground(new java.awt.Color(51, 51, 51));

        singer_icon_panel.setBackground(new java.awt.Color(51, 51, 51));

        singer_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_micro_30px.png"))); // NOI18N

        javax.swing.GroupLayout singer_icon_panelLayout = new javax.swing.GroupLayout(singer_icon_panel);
        singer_icon_panel.setLayout(singer_icon_panelLayout);
        singer_icon_panelLayout.setHorizontalGroup(
            singer_icon_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(singer_icon_panelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(singer_icon)
                .addGap(10, 10, 10))
        );
        singer_icon_panelLayout.setVerticalGroup(
            singer_icon_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(singer_icon_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(singer_icon)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        singer_name_panel.setBackground(new java.awt.Color(51, 51, 51));

        singer_name_label.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        singer_name_label.setForeground(new java.awt.Color(255, 255, 255));
        singer_name_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        singer_name_label.setText("Ca Sĩ");

        javax.swing.GroupLayout singer_name_panelLayout = new javax.swing.GroupLayout(singer_name_panel);
        singer_name_panel.setLayout(singer_name_panelLayout);
        singer_name_panelLayout.setHorizontalGroup(
            singer_name_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(singer_name_panelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(singer_name_label)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        singer_name_panelLayout.setVerticalGroup(
            singer_name_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, singer_name_panelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(singer_name_label, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout singer_menu_panelLayout = new javax.swing.GroupLayout(singer_menu_panel);
        singer_menu_panel.setLayout(singer_menu_panelLayout);
        singer_menu_panelLayout.setHorizontalGroup(
            singer_menu_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(singer_menu_panelLayout.createSequentialGroup()
                .addComponent(singer_icon_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(singer_name_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        singer_menu_panelLayout.setVerticalGroup(
            singer_menu_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(singer_name_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(singer_icon_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        search_menu_panel.setBackground(new java.awt.Color(51, 51, 51));

        search_icon_panel.setBackground(new java.awt.Color(51, 51, 51));

        search_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_search_30px.png"))); // NOI18N

        javax.swing.GroupLayout search_icon_panelLayout = new javax.swing.GroupLayout(search_icon_panel);
        search_icon_panel.setLayout(search_icon_panelLayout);
        search_icon_panelLayout.setHorizontalGroup(
            search_icon_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, search_icon_panelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(search_icon)
                .addContainerGap())
        );
        search_icon_panelLayout.setVerticalGroup(
            search_icon_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, search_icon_panelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(search_icon)
                .addGap(10, 10, 10))
        );

        search_text_panel.setBackground(new java.awt.Color(51, 51, 51));
        search_text_panel.setOpaque(false);

        search_text_label.setBackground(new java.awt.Color(0, 0, 0));
        search_text_label.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        search_text_label.setForeground(new java.awt.Color(204, 204, 204));
        search_text_label.setText(" Search");
        search_text_label.setToolTipText("");
        search_text_label.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        search_text_label.setCaretPosition(1);
        search_text_label.setMargin(new java.awt.Insets(1, 1, 1, 1));

        s_label.setBackground(new java.awt.Color(0, 0, 0));
        s_label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_search_20px.png"))); // NOI18N

        javax.swing.GroupLayout search_text_panelLayout = new javax.swing.GroupLayout(search_text_panel);
        search_text_panel.setLayout(search_text_panelLayout);
        search_text_panelLayout.setHorizontalGroup(
            search_text_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(search_text_panelLayout.createSequentialGroup()
                .addComponent(search_text_label, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(s_label, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        search_text_panelLayout.setVerticalGroup(
            search_text_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(search_text_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(search_text_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(s_label, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(search_text_label, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout search_menu_panelLayout = new javax.swing.GroupLayout(search_menu_panel);
        search_menu_panel.setLayout(search_menu_panelLayout);
        search_menu_panelLayout.setHorizontalGroup(
            search_menu_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(search_menu_panelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(search_icon_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(search_text_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        search_menu_panelLayout.setVerticalGroup(
            search_menu_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(search_menu_panelLayout.createSequentialGroup()
                .addGroup(search_menu_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(search_icon_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(search_text_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        logout.setBackground(new java.awt.Color(51, 51, 51));

        logout_text.setBackground(new java.awt.Color(51, 51, 51));

        singer_name_label2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        singer_name_label2.setForeground(new java.awt.Color(255, 255, 255));
        singer_name_label2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        singer_name_label2.setText("Đăng xuất");

        javax.swing.GroupLayout logout_textLayout = new javax.swing.GroupLayout(logout_text);
        logout_text.setLayout(logout_textLayout);
        logout_textLayout.setHorizontalGroup(
            logout_textLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(logout_textLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(singer_name_label2)
                .addContainerGap(68, Short.MAX_VALUE))
        );
        logout_textLayout.setVerticalGroup(
            logout_textLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, logout_textLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(singer_name_label2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );

        logout_icon.setBackground(new java.awt.Color(51, 51, 51));

        singer_icon2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_login_rounded_right_30px_1.png"))); // NOI18N

        javax.swing.GroupLayout logout_iconLayout = new javax.swing.GroupLayout(logout_icon);
        logout_icon.setLayout(logout_iconLayout);
        logout_iconLayout.setHorizontalGroup(
            logout_iconLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(logout_iconLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(singer_icon2)
                .addGap(10, 10, 10))
        );
        logout_iconLayout.setVerticalGroup(
            logout_iconLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(logout_iconLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(singer_icon2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout logoutLayout = new javax.swing.GroupLayout(logout);
        logout.setLayout(logoutLayout);
        logoutLayout.setHorizontalGroup(
            logoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, logoutLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(logout_icon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(logout_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        logoutLayout.setVerticalGroup(
            logoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(logout_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(logout_icon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout menu_panelLayout = new javax.swing.GroupLayout(menu_panel);
        menu_panel.setLayout(menu_panelLayout);
        menu_panelLayout.setHorizontalGroup(
            menu_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(singer_menu_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(playlist_menu_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(search_menu_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(music_menu_panel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(menu_display_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(logout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        menu_panelLayout.setVerticalGroup(
            menu_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menu_panelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(menu_display_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(search_menu_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(music_menu_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(playlist_menu_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(singer_menu_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addComponent(logout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(70, Short.MAX_VALUE))
        );

        view_panel.setBackground(new java.awt.Color(153, 153, 153));
        view_panel.setBorder(new javax.swing.border.MatteBorder(null));

        javax.swing.GroupLayout view_panelLayout = new javax.swing.GroupLayout(view_panel);
        view_panel.setLayout(view_panelLayout);
        view_panelLayout.setHorizontalGroup(
            view_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 828, Short.MAX_VALUE)
        );
        view_panelLayout.setVerticalGroup(
            view_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 354, Short.MAX_VALUE)
        );

        control_display_panel.setBackground(new java.awt.Color(0, 0, 0));

        exit_panel.setBackground(new java.awt.Color(0, 0, 0));

        exit_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        exit_label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_multiply_20px.png"))); // NOI18N

        javax.swing.GroupLayout exit_panelLayout = new javax.swing.GroupLayout(exit_panel);
        exit_panel.setLayout(exit_panelLayout);
        exit_panelLayout.setHorizontalGroup(
            exit_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(exit_panelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(exit_label)
                .addGap(10, 10, 10))
        );
        exit_panelLayout.setVerticalGroup(
            exit_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(exit_panelLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(exit_label)
                .addGap(5, 5, 5))
        );

        maximize_panel.setBackground(new java.awt.Color(0, 0, 0));

        maximize_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        maximize_label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_unchecked_checkbox_20px.png"))); // NOI18N

        javax.swing.GroupLayout maximize_panelLayout = new javax.swing.GroupLayout(maximize_panel);
        maximize_panel.setLayout(maximize_panelLayout);
        maximize_panelLayout.setHorizontalGroup(
            maximize_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(maximize_panelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(maximize_label)
                .addGap(10, 10, 10))
        );
        maximize_panelLayout.setVerticalGroup(
            maximize_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(maximize_panelLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(maximize_label)
                .addGap(5, 5, 5))
        );

        minimize_panel.setBackground(new java.awt.Color(0, 0, 0));

        minimize_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        minimize_label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_minus_math_20px.png"))); // NOI18N

        javax.swing.GroupLayout minimize_panelLayout = new javax.swing.GroupLayout(minimize_panel);
        minimize_panel.setLayout(minimize_panelLayout);
        minimize_panelLayout.setHorizontalGroup(
            minimize_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(minimize_panelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(minimize_label)
                .addGap(10, 10, 10))
        );
        minimize_panelLayout.setVerticalGroup(
            minimize_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(minimize_panelLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(minimize_label)
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout control_display_panelLayout = new javax.swing.GroupLayout(control_display_panel);
        control_display_panel.setLayout(control_display_panelLayout);
        control_display_panelLayout.setHorizontalGroup(
            control_display_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(control_display_panelLayout.createSequentialGroup()
                .addGap(0, 154, Short.MAX_VALUE)
                .addComponent(minimize_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(maximize_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(exit_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        control_display_panelLayout.setVerticalGroup(
            control_display_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(control_display_panelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(control_display_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(minimize_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(exit_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(maximize_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        dragFrame_panel.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout dragFrame_panelLayout = new javax.swing.GroupLayout(dragFrame_panel);
        dragFrame_panel.setLayout(dragFrame_panelLayout);
        dragFrame_panelLayout.setHorizontalGroup(
            dragFrame_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 603, Short.MAX_VALUE)
        );
        dragFrame_panelLayout.setVerticalGroup(
            dragFrame_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        title_panel.setBackground(new java.awt.Color(0, 0, 0));

        title_label.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        title_label.setForeground(new java.awt.Color(255, 255, 255));
        title_label.setText("MUSIC PLAYER");

        javax.swing.GroupLayout title_panelLayout = new javax.swing.GroupLayout(title_panel);
        title_panel.setLayout(title_panelLayout);
        title_panelLayout.setHorizontalGroup(
            title_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(title_panelLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(title_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(479, 479, 479))
        );
        title_panelLayout.setVerticalGroup(
            title_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(title_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );

        song_playing_panel.setBackground(new java.awt.Color(102, 102, 102));

        song_playing_control.setBackground(new java.awt.Color(102, 102, 102));
        song_playing_control.setOpaque(false);

        song_playing_play.setBackground(new java.awt.Color(102, 102, 102));

        play_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        play_label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_play_30px_1.png"))); // NOI18N

        javax.swing.GroupLayout song_playing_playLayout = new javax.swing.GroupLayout(song_playing_play);
        song_playing_play.setLayout(song_playing_playLayout);
        song_playing_playLayout.setHorizontalGroup(
            song_playing_playLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(song_playing_playLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(play_label)
                .addGap(0, 0, 0))
        );
        song_playing_playLayout.setVerticalGroup(
            song_playing_playLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, song_playing_playLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(play_label))
        );

        song_playing_next.setBackground(new java.awt.Color(102, 102, 102));

        next_label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_end_30px_1.png"))); // NOI18N

        javax.swing.GroupLayout song_playing_nextLayout = new javax.swing.GroupLayout(song_playing_next);
        song_playing_next.setLayout(song_playing_nextLayout);
        song_playing_nextLayout.setHorizontalGroup(
            song_playing_nextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(song_playing_nextLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(next_label)
                .addGap(0, 0, 0))
        );
        song_playing_nextLayout.setVerticalGroup(
            song_playing_nextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(song_playing_nextLayout.createSequentialGroup()
                .addComponent(next_label)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        song_playing_previous.setBackground(new java.awt.Color(102, 102, 102));

        previous_label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_skip_to_start_30px.png"))); // NOI18N

        javax.swing.GroupLayout song_playing_previousLayout = new javax.swing.GroupLayout(song_playing_previous);
        song_playing_previous.setLayout(song_playing_previousLayout);
        song_playing_previousLayout.setHorizontalGroup(
            song_playing_previousLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(song_playing_previousLayout.createSequentialGroup()
                .addComponent(previous_label)
                .addGap(0, 0, 0))
        );
        song_playing_previousLayout.setVerticalGroup(
            song_playing_previousLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(song_playing_previousLayout.createSequentialGroup()
                .addComponent(previous_label)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout song_playing_controlLayout = new javax.swing.GroupLayout(song_playing_control);
        song_playing_control.setLayout(song_playing_controlLayout);
        song_playing_controlLayout.setHorizontalGroup(
            song_playing_controlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(song_playing_controlLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(song_playing_previous, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(song_playing_play, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(song_playing_next, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        song_playing_controlLayout.setVerticalGroup(
            song_playing_controlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(song_playing_controlLayout.createSequentialGroup()
                .addGroup(song_playing_controlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(song_playing_previous, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(song_playing_controlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(song_playing_play, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(song_playing_next, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(0, 0, 0))
        );

        song_playing_name.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        song_playing_name.setForeground(new java.awt.Color(255, 255, 255));
        song_playing_name.setText("Lối nhỏ");

        song_playing_singer.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        song_playing_singer.setForeground(new java.awt.Color(255, 255, 255));
        song_playing_singer.setText("Đen");

        dispose_playing_panel.setBackground(new java.awt.Color(102, 102, 102));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_delete_sign_18px_1.png"))); // NOI18N
        jLabel1.setText("jLabel1");

        javax.swing.GroupLayout dispose_playing_panelLayout = new javax.swing.GroupLayout(dispose_playing_panel);
        dispose_playing_panel.setLayout(dispose_playing_panelLayout);
        dispose_playing_panelLayout.setHorizontalGroup(
            dispose_playing_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dispose_playing_panelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        dispose_playing_panelLayout.setVerticalGroup(
            dispose_playing_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dispose_playing_panelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        volume_slider.setBackground(new java.awt.Color(153, 153, 153));

        time.setBackground(new java.awt.Color(102, 102, 102));
        time.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        time.setForeground(new java.awt.Color(255, 255, 255));
        time.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        time.setBorder(null);
        time.setMargin(new java.awt.Insets(0, 0, 0, 0));

        javax.swing.GroupLayout song_playing_panelLayout = new javax.swing.GroupLayout(song_playing_panel);
        song_playing_panel.setLayout(song_playing_panelLayout);
        song_playing_panelLayout.setHorizontalGroup(
            song_playing_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(song_playing_panelLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(song_playing_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(song_playing_singer, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
                    .addComponent(song_playing_name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(song_playing_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(song_playing_panelLayout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(song_playing_control, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addComponent(volume_slider, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(119, Short.MAX_VALUE))
                    .addGroup(song_playing_panelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(duration, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(time, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(dispose_playing_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        song_playing_panelLayout.setVerticalGroup(
            song_playing_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(song_playing_panelLayout.createSequentialGroup()
                .addGroup(song_playing_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dispose_playing_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(song_playing_panelLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(song_playing_name)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(song_playing_singer))
                    .addGroup(song_playing_panelLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(song_playing_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(duration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(time, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(song_playing_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(song_playing_control, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, song_playing_panelLayout.createSequentialGroup()
                                .addComponent(volume_slider, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(9, 9, 9)))))
                .addContainerGap())
        );

        javax.swing.GroupLayout JPanelRootLayout = new javax.swing.GroupLayout(JPanelRoot);
        JPanelRoot.setLayout(JPanelRootLayout);
        JPanelRootLayout.setHorizontalGroup(
            JPanelRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPanelRootLayout.createSequentialGroup()
                .addComponent(menu_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(JPanelRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(view_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(song_playing_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addComponent(title_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(JPanelRootLayout.createSequentialGroup()
                .addComponent(dragFrame_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(control_display_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        JPanelRootLayout.setVerticalGroup(
            JPanelRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanelRootLayout.createSequentialGroup()
                .addGroup(JPanelRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(dragFrame_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(control_display_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addComponent(title_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(JPanelRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(menu_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(JPanelRootLayout.createSequentialGroup()
                        .addComponent(view_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, 0)
                        .addComponent(song_playing_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(JPanelRoot, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(JPanelRoot, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JPanelRoot;
    private javax.swing.JPanel control_display_panel;
    private javax.swing.JPanel dispose_playing_panel;
    private javax.swing.JPanel dragFrame_panel;
    private javax.swing.JProgressBar duration;
    private javax.swing.JLabel exit_label;
    private javax.swing.JPanel exit_panel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JPanel logout;
    private javax.swing.JPanel logout_icon;
    private javax.swing.JPanel logout_text;
    private javax.swing.JLabel maximize_label;
    private javax.swing.JPanel maximize_panel;
    private javax.swing.JPanel menu_display_icon_panel;
    private javax.swing.JLabel menu_display_label;
    private javax.swing.JPanel menu_display_name_panel;
    private javax.swing.JPanel menu_display_panel;
    private javax.swing.JPanel menu_panel;
    private javax.swing.JLabel minimize_label;
    private javax.swing.JPanel minimize_panel;
    private javax.swing.JPanel music_icon_panel;
    private javax.swing.JPanel music_menu_panel;
    private javax.swing.JLabel music_name_label;
    private javax.swing.JPanel music_name_panel;
    private javax.swing.JLabel next_label;
    private javax.swing.JLabel play_label;
    private javax.swing.JLabel playlist_icon;
    private javax.swing.JPanel playlist_icon_panel;
    private javax.swing.JPanel playlist_menu_panel;
    private javax.swing.JLabel playlist_name_label;
    private javax.swing.JPanel playlist_name_panel;
    private javax.swing.JLabel previous_label;
    private javax.swing.JLabel s_label;
    private javax.swing.JLabel search_icon;
    private javax.swing.JPanel search_icon_panel;
    private javax.swing.JPanel search_menu_panel;
    private javax.swing.JTextField search_text_label;
    private javax.swing.JPanel search_text_panel;
    private javax.swing.JLabel singer_icon;
    private javax.swing.JLabel singer_icon2;
    private javax.swing.JPanel singer_icon_panel;
    private javax.swing.JPanel singer_menu_panel;
    private javax.swing.JLabel singer_name_label;
    private javax.swing.JLabel singer_name_label2;
    private javax.swing.JPanel singer_name_panel;
    private javax.swing.JPanel song_playing_control;
    private javax.swing.JLabel song_playing_name;
    private javax.swing.JPanel song_playing_next;
    private javax.swing.JPanel song_playing_panel;
    private javax.swing.JPanel song_playing_play;
    private javax.swing.JPanel song_playing_previous;
    private javax.swing.JLabel song_playing_singer;
    private javax.swing.JTextField time;
    private javax.swing.JLabel title_label;
    private javax.swing.JPanel title_panel;
    private javax.swing.JPanel view_panel;
    private javax.swing.JSlider volume_slider;
    // End of variables declaration//GEN-END:variables

    
    public Map<String, ArrayList<JPanel>> getMain_views() {
        Map<String, ArrayList<JPanel>> m = new HashMap<>();            
        m.put("music_panel", new ArrayList<>(Arrays.asList(music_menu_panel, music_icon_panel, music_name_panel)));
        m.put("playlist_panel", new ArrayList<>(Arrays.asList(playlist_menu_panel, playlist_icon_panel, playlist_name_panel)));        
        m.put("artist_panel", new ArrayList<>(Arrays.asList(singer_menu_panel, singer_icon_panel, singer_name_panel)));
        m.put("logout", new ArrayList<>(Arrays.asList(logout, logout_icon, logout_text)));
        return m;
    }

    public javax.swing.JPanel getJPanelRoot() {
        return JPanelRoot;
    }

    public javax.swing.JPanel getControl_display_panel() {
        return control_display_panel;
    }

    public javax.swing.JPanel getDragFrame_panel() {
        return dragFrame_panel;
    }

    public javax.swing.JLabel getExit_label() {
        return exit_label;
    }

    public javax.swing.JPanel getExit_panel() {
        return exit_panel;
    }

    public javax.swing.JLabel getjLabel10() {
        return jLabel10;
    }

    public javax.swing.JLabel getMaximize_label() {
        return maximize_label;
    }

    public javax.swing.JPanel getMaximize_panel() {
        return maximize_panel;
    }

    public javax.swing.JPanel getMenu_display_icon_panel() {
        return menu_display_icon_panel;
    }

    public javax.swing.JLabel getMenu_display_label() {
        return menu_display_label;
    }

    public javax.swing.JPanel getMenu_display_name_panel() {
        return menu_display_name_panel;
    }

    public javax.swing.JPanel getMenu_display_panel() {
        return menu_display_panel;
    }

    public javax.swing.JPanel getMenu_panel() {
        return menu_panel;
    }

    public javax.swing.JLabel getMinimize_label() {
        return minimize_label;
    }

    public javax.swing.JPanel getMinimize_panel() {
        return minimize_panel;
    }

    public javax.swing.JPanel getMusic_icon_panel() {
        return music_icon_panel;
    }

    public javax.swing.JPanel getMusic_menu_panel() {
        return music_menu_panel;
    }

    public javax.swing.JLabel getMusic_name_label() {
        return music_name_label;
    }

    public javax.swing.JPanel getMusic_name_panel() {
        return music_name_panel;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public javax.swing.JLabel getPlaylist_icon() {
        return playlist_icon;
    }

    public javax.swing.JPanel getPlaylist_icon_panel() {
        return playlist_icon_panel;
    }

    public javax.swing.JPanel getPlaylist_menu_panel() {
        return playlist_menu_panel;
    }

    public javax.swing.JLabel getPlaylist_name_label() {
        return playlist_name_label;
    }

    public javax.swing.JPanel getPlaylist_name_panel() {
        return playlist_name_panel;
    }

    public javax.swing.JLabel getS_label() {
        return s_label;
    }

    public javax.swing.JLabel getSearch_icon() {
        return search_icon;
    }

    public javax.swing.JPanel getSearch_icon_panel() {
        return search_icon_panel;
    }

    public javax.swing.JPanel getSearch_menu_panel() {
        return search_menu_panel;
    }

    public javax.swing.JTextField getSearch_text_label() {
        return search_text_label;
    }

    public javax.swing.JPanel getSearch_text_panel() {
        return search_text_panel;
    }

    public javax.swing.JLabel getSinger_icon() {
        return singer_icon;
    }

    public javax.swing.JPanel getSinger_icon_panel() {
        return singer_icon_panel;
    }

    public javax.swing.JPanel getSinger_menu_panel() {
        return singer_menu_panel;
    }

    public javax.swing.JLabel getSinger_name_label() {
        return singer_name_label;
    }

    public javax.swing.JPanel getSinger_name_panel() {
        return singer_name_panel;
    }

    public JPanel getSong_playing_next() {
        return song_playing_next;
    }

    public void setSong_playing_next(JPanel song_playing_next) {
        this.song_playing_next = song_playing_next;
    }

    public JPanel getSong_playing_panel() {
        return song_playing_panel;
    }

    public void setSong_playing_panel(JPanel song_playing_panel) {
        this.song_playing_panel = song_playing_panel;
    }

    public JPanel getSong_playing_play() {
        return song_playing_play;
    }

    public void setSong_playing_play(JPanel song_playing_play) {
        this.song_playing_play = song_playing_play;
    }

    public JPanel getSong_playing_previous() {
        return song_playing_previous;
    }

    public void setSong_playing_previous(JPanel song_playing_previous) {
        this.song_playing_previous = song_playing_previous;
    }

    public javax.swing.JLabel getTitle_label() {
        return title_label;
    }

    public javax.swing.JPanel getTitle_panel() {
        return title_panel;
    }

    public JPanel getLogout() {
        return logout;
    }

    public void setLogout(JPanel logout) {
        this.logout = logout;
    }

    public JPanel getLogout_icon() {
        return logout_icon;
    }

    public void setLogout_icon(JPanel logout_icon) {
        this.logout_icon = logout_icon;
    }

    public JPanel getLogout_text() {
        return logout_text;
    }

    public void setLogout_text(JPanel logout_text) {
        this.logout_text = logout_text;
    }   

    public javax.swing.JPanel getView_panel() {
        return view_panel;
    }

    public JPanel getDispose_playing_panel() {
        return dispose_playing_panel;
    }

    public void setDispose_playing_panel(JPanel dispose_playing_panel) {
        this.dispose_playing_panel = dispose_playing_panel;
    }

    public JLabel getjLabel1() {
        return jLabel1;
    }

    public void setjLabel1(JLabel jLabel1) {
        this.jLabel1 = jLabel1;
    }

    public JLabel getNext_label() {
        return next_label;
    }

    public void setNext_label(JLabel next_label) {
        this.next_label = next_label;
    }

    public JLabel getPlay_label() {
        return play_label;
    }

    public void setPlay_label(JLabel play_label) {
        this.play_label = play_label;
    }

    public JLabel getPrevious_label() {
        return previous_label;
    }

    public void setPrevious_label(JLabel previous_label) {
        this.previous_label = previous_label;
    }

    public JLabel getSinger_icon2() {
        return singer_icon2;
    }

    public void setSinger_icon2(JLabel singer_icon2) {
        this.singer_icon2 = singer_icon2;
    }

    public JLabel getSinger_name_label2() {
        return singer_name_label2;
    }

    public void setSinger_name_label2(JLabel singer_name_label2) {
        this.singer_name_label2 = singer_name_label2;
    }

    public JPanel getSong_playing_control() {
        return song_playing_control;
    }

    public void setSong_playing_control(JPanel song_playing_control) {
        this.song_playing_control = song_playing_control;
    }

    public JLabel getSong_playing_name() {
        return song_playing_name;
    }

    public void setSong_playing_name(JLabel song_playing_name) {
        this.song_playing_name = song_playing_name;
    }

    public JLabel getSong_playing_singer() {
        return song_playing_singer;
    }

    public void setSong_playing_singer(JLabel song_playing_singer) {
        this.song_playing_singer = song_playing_singer;
    }       
        
}