/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import DatabaseQuery.PlaylistQuery;
import DatabaseQuery.SingerQuery;
import DatabaseQuery.SongQuery;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import models.Singer;
import models.Song;
import views.MainFrame;


/**
 *
 * @author linhd
 */
public class MenuController {
   
    private MainFrame frame;
    
    private final int oldWidth;
    private Point oldPointSearch;
    private ViewController view;
    
    public MenuController(MainFrame frame) {
        this.frame = frame;        
        oldWidth = frame.getMenu_panel().getWidth();
        oldPointSearch = frame.getSearch_text_panel().getLocation();    
        view = new ViewController(frame);
    }
                   
    // main events 
    public void init() {
        setActionDisPlayMenu();
        setActionSearchPanel();
        setMainViewPanel();
        
    }
    
    public boolean isSmallMenu() {
        return frame.getMenu_panel().getWidth() == oldWidth;
    }    
    // set menu width smaller
    public void setSmallMenu() {   
        JPanel menu_panel = frame.getMenu_panel();
        menu_panel.setSize(oldWidth, menu_panel.getHeight());   
        frame.getSearch_text_label().setText(" Search");
        frame.getSearch_text_label().setCaretPosition(1);
        menu_panel.revalidate();                
    }
    // set menu size wider 
    public void setMediumMenu() { 
        JPanel menu_panel = frame.getMenu_panel();
        JPanel sub_menu_name_panel = frame.getMenu_display_name_panel();
        menu_panel.setSize(oldWidth + sub_menu_name_panel.getWidth(), menu_panel.getHeight());  
    }        
    
    // change view while click menu icon    
    private void setActionDisPlayMenu() { 
        JPanel sub_menu_icon_panel = frame.getMenu_display_icon_panel();
        sub_menu_icon_panel.addMouseListener(new MenuColorListener(sub_menu_icon_panel));
        sub_menu_icon_panel.addMouseListener(new MouseAdapter() {        
            @Override
            public void mouseClicked(MouseEvent e){
                if (isSmallMenu())                    
                    setMediumMenu();                    
                else
                    setSmallMenu();                                                   
            }
        });        
    }
    // change view while click search icon
    private void setActionSearchPanel() {
        JPanel search_icon_panel = frame.getSearch_icon_panel();
        
        search_icon_panel.addMouseListener(new MenuColorListener(search_icon_panel));
        
        search_icon_panel.addMouseListener(new MouseAdapter() {            
            @Override
            public void mouseClicked(MouseEvent e) {                                
                setMediumMenu();                
            }
        });      
        
        JTextField search_text = frame.getSearch_text_label();
        search_text.setCaretPosition(1);
        search_text.addKeyListener(new KeyAdapter() {            
            @Override
            public void keyPressed(KeyEvent e) {
                if (search_text.getText().trim().equals("Search")) {
                    search_text.setText(" ");
                }  
            }
            @Override
             public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    String str = frame.getSearch_text_label().getText().trim();
                    
                    if (!str.equals("Search") && !str.equals("")) {
                        setSmallMenu();
                        
                        try {
                            view.setSearchPanel(str);
                        } catch (SQLException ex) {
                            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                    }
                }
             }
        });
        
        JLabel s_icon = frame.getS_label();
        s_icon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                s_icon.setOpaque(true);                
                s_icon.setBackground(new Color(96, 110, 100));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                s_icon.setOpaque(false); 
                s_icon.setBackground(null);
                search_icon_panel.setBackground(new Color(51,51,51));
            }
            @Override
            public void mouseClicked(MouseEvent e) {       
                String str = search_text.getText().trim();
                if (!str.equals("Search") && !str.equals("")) {
                    setSmallMenu();
                                
                    try {    
                        view.setSearchPanel(str);
                    } catch (SQLException ex) {
                        Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
            }
        });
        
        
    }

    
    // class change color of menu
    private class MenuColorListener extends MouseAdapter {
        
        private JPanel root;

        public MenuColorListener(JPanel root) {
            this.root = root;
        }
        
        @Override
        public void mouseEntered(MouseEvent e) {
            root.setBackground(new Color(96, 110, 100));
        }
        @Override
        public void mouseExited(MouseEvent e) {
            root.setBackground(new Color(51,51,51));
        }
    }
    
    private void setMainViewPanel() {
        MenuEvent menuEvent = new MenuEvent();
        Map<String, ArrayList<JPanel>> menu = frame.getMain_views();
        for (Map.Entry m : menu.entrySet()) { 
            
            menuEvent.setAction(m);               
        }
    }
    
    private class MenuEvent {
        public void setAction(Map.Entry<String, ArrayList<JPanel>> entry) {
            JPanel main_panel = entry.getValue().get(0);
            JPanel sub_panel1 = entry.getValue().get(1);
            JPanel sub_panel2 = entry.getValue().get(2);
            String panel_name = entry.getKey();
            
            MenuListener menuListener = new MenuListener(main_panel, sub_panel1, sub_panel2, panel_name);
            main_panel.addMouseListener(menuListener);
        }        
    }
    
    private class MenuListener extends MouseAdapter {
        
        private JPanel main_panel, p1, p2; // main, sub1, sub2
        private String panel_name;                 
        
        
        public MenuListener(JPanel main_panel, JPanel p1, JPanel p2, String panel_name) {
            this.main_panel = main_panel;
            this.p1 = p1;
            this.p2 = p2;
            this.panel_name = panel_name;
            
        }                
        @Override
        public void mouseEntered(MouseEvent e) {
            main_panel.setBackground(new Color(96, 110, 100));
            p1.setOpaque(false);
            p2.setOpaque(false);
        } 
        @Override
        public void mouseExited(MouseEvent e) {
            main_panel.setBackground(new Color(51,51,51));
            p1.setOpaque(false);
            p2.setOpaque(false);
        }
        @Override
        public void mouseClicked(MouseEvent e) {
            setSmallMenu();
            try {                
                switch (panel_name) {
                    case "music_panel":
                        SongQuery songQuery = new SongQuery();        
                        ArrayList<Song> listSong = songQuery.getAllSongs();  
                        view.setMusics(songQuery.getAllSongs());
                        break;
                    case "playlist_panel":
                        PlaylistQuery query = new PlaylistQuery();
                        view.setPlayLists(query.getPlaylistsByUser(frame.getUser()));
                        break;
                    case "artist_panel":
                        SingerQuery query1 = new SingerQuery();
                        ArrayList <Singer> listSinger = query1.getAllSingers();
                        view.setSingers(listSinger);
                        break;
                    case "logout":
                        int opt = JOptionPane.showConfirmDialog(null, "Bạn có muốn đăng xuất ?", "Thông báo", JOptionPane.YES_NO_OPTION);
                        if (opt == 0) {
                            
                            LogoutController logoutController = new LogoutController(frame);
                        }                            
                    default:
                        break;
                }
            } catch (SQLException ex) {
                Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }   
    
    private void addEventLogout(ArrayList <JPanel> values) {
        
    }
   
}
