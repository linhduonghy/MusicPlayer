/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import views.MainFrame;

/**
 *
 * @author linhd
 */
public class ListPanelController {
    private MainFrame frame;
    private JPanel root;
    private Color oldColor;      
    public ListPanelController() {
    }

    public ListPanelController(MainFrame frame, JPanel root) {
        this.root = root;
        this.frame = frame;
        oldColor = root.getBackground();
    }
    
    public void init() {
        setEventClickSongPanel();
    }
    private void setEventClickSongPanel() {
        MenuController menu = new MenuController(frame);
        root.addMouseListener(new MouseAdapter() {
            @Override
                public void mouseEntered(MouseEvent e) {                
                    if (menu.isSmallMenu()) {
                        root.setBackground(Color.DARK_GRAY);                        
                    }
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    if (menu.isSmallMenu()) {
                        root.setBackground(oldColor);                        
                    }
                }
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (!menu.isSmallMenu()) {
                        menu.setSmallMenu();                   
                    }
                    else {
                        root.setBackground(new Color(81, 141, 215));
                    }
                }
        });
    }
    
}
