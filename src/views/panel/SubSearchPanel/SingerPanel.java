/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.panel.SubSearchPanel;

/**
 *
 * @author linhd
 */
public class SingerPanel extends javax.swing.JPanel {

    /**
     * Creates new form SingerPanel
     */
    public SingerPanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        name_singer = new javax.swing.JLabel();

        setBackground(new java.awt.Color(102, 102, 102));

        name_singer.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        name_singer.setForeground(new java.awt.Color(255, 255, 255));
        name_singer.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(name_singer)
                .addContainerGap(268, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(name_singer)
                .addGap(15, 15, 15))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel name_singer;
    // End of variables declaration//GEN-END:variables
    public void setName_singer(String name) {
        this.name_singer.setText(name);
    }
    
}