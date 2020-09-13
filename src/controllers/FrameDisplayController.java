/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import views.MainFrame;

/**
 *
 * @author linhd
 */
public class FrameDisplayController {
    private MainFrame frame;
    public FrameDisplayController() {
    }

    public FrameDisplayController(MainFrame frame) {
        this.frame = frame;
        initController();        
    }
    
    private void initController() {
        setEventMinimize();
        setEventMaximize();
        setEventExit();
        setLocationFrameByDragging();
    }
    private void setEventMinimize() {
        JPanel minimize_panel = frame.getMinimize_panel();
        minimize_panel.addMouseListener(new MouseAdapter() {            
            @Override
            public void mouseClicked(MouseEvent e) {                
                frame.setState(JFrame.ICONIFIED);                
            }
            @Override
            public void mouseEntered(MouseEvent e) {                
                minimize_panel.setBackground(new Color(100, 95, 95 ));                   
            }
            @Override
            public void mouseExited(MouseEvent e) {                
                minimize_panel.setBackground(Color.black);                
            }            
        });        
    }

    private void setEventMaximize() {
        JPanel maximize_panel = frame.getMaximize_panel();
        JLabel maximize_label = frame.getMaximize_label();
        maximize_panel.addMouseListener(new MouseAdapter() {            
            @Override
            public void mouseClicked(MouseEvent e) {     
                if (frame.getExtendedState() == JFrame.NORMAL) {                    
                    frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
                    maximize_label.setIcon(new ImageIcon(getClass().getResource("/images/icons8_restore_window_20px_1.png")));
                }
                else {                    
                    frame.setExtendedState(JFrame.NORMAL);  
                    maximize_label.setIcon(new ImageIcon(getClass().getResource("/images/icons8_unchecked_checkbox_20px.png")));
                }   
                frame.revalidate();
            }
            @Override
            public void mouseEntered(MouseEvent e) {                
                maximize_panel.setBackground(new Color(100, 95, 95));                   
            }
            @Override
            public void mouseExited(MouseEvent e) {                
                maximize_panel.setBackground(Color.black);                
            }            
        });
    }

    private void setEventExit() {
        JPanel exit_panel = frame.getExit_panel();
        exit_panel.addMouseListener(new MouseAdapter() {            
            @Override
            public void mouseClicked(MouseEvent e) {                
                System.exit(0);                
            }
            @Override
            public void mouseEntered(MouseEvent e) {                
                exit_panel.setBackground(Color.red);   
                
            }
            @Override
            public void mouseExited(MouseEvent e) {                
                exit_panel.setBackground(Color.black);                
            }            
        });
    }
    
    private void setLocationFrameByDragging() {        
        FrameDragListener frameDragListener = new FrameDragListener();
        
        JPanel dragFrame_panel = frame.getDragFrame_panel();
        dragFrame_panel.addMouseListener(frameDragListener);
        dragFrame_panel.addMouseMotionListener(frameDragListener);
    } 
        
    private class FrameDragListener extends MouseAdapter {                
        private Point mouseDownCompCoords = null;                

        @Override
        public void mousePressed(MouseEvent e) {
            mouseDownCompCoords = e.getPoint();   
        }
        
        @Override
        public void mouseDragged(MouseEvent e) {            
            Point currCoords = e.getLocationOnScreen();            
            if (frame.getExtendedState() == JFrame.NORMAL)
                frame.setLocation(currCoords.x - mouseDownCompCoords.x, currCoords.y - mouseDownCompCoords.y);            
        }
    }
}
