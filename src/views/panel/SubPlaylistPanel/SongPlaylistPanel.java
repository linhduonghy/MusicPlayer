/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.panel.SubPlaylistPanel;

import javax.swing.JLabel;
import javax.swing.JPanel;
import views.MainFrame;

/**
 *
 * @author linhd
 */
public class SongPlaylistPanel extends javax.swing.JPanel {

    /**
     * Creates new form SongPanel
     */
    
    
    public SongPlaylistPanel(MainFrame frame) {
        initComponents();        
    }

    public void setTextName(String name) {
        this.name_song.setText(name);
    }

    public void setSinger(String singer) {
        this.singer.setText(singer);
    }

    public JLabel getName_song() {
        return name_song;
    }

    public JPanel getPlay() {
        return play;
    }

    public JLabel getSinger() {
        return singer;
    }

    public JPanel getRemove() {
        return remove;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        name_song = new javax.swing.JLabel();
        singer = new javax.swing.JLabel();
        remove = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        play = new javax.swing.JPanel();
        play_btn = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 0, 0));

        name_song.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        name_song.setForeground(new java.awt.Color(255, 255, 255));
        name_song.setText("Anh ta bỏ em rồi");

        singer.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        singer.setForeground(new java.awt.Color(255, 255, 255));
        singer.setText("Hương Giang");

        remove.setOpaque(false);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_delete_sign_18px_1.png"))); // NOI18N

        javax.swing.GroupLayout removeLayout = new javax.swing.GroupLayout(remove);
        remove.setLayout(removeLayout);
        removeLayout.setHorizontalGroup(
            removeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, removeLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel1)
                .addGap(0, 0, 0))
        );
        removeLayout.setVerticalGroup(
            removeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, removeLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        play.setBackground(new java.awt.Color(0, 0, 0));
        play.setOpaque(false);

        play_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_play_18px.png"))); // NOI18N

        javax.swing.GroupLayout playLayout = new javax.swing.GroupLayout(play);
        play.setLayout(playLayout);
        playLayout.setHorizontalGroup(
            playLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, playLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(play_btn))
        );
        playLayout.setVerticalGroup(
            playLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, playLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(play_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(play, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(name_song, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75)
                .addComponent(singer, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(remove, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(play, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(remove, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(singer)
                            .addComponent(name_song))))
                .addGap(17, 17, 17))
        );
    }// </editor-fold>//GEN-END:initComponents

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel name_song;
    private javax.swing.JPanel play;
    private javax.swing.JLabel play_btn;
    private javax.swing.JPanel remove;
    private javax.swing.JLabel singer;
    // End of variables declaration//GEN-END:variables
}
