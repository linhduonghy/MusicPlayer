/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MusicPlayer;

import DatabaseQuery.UserQuery;
import controllers.AudioController;
import controllers.FileController;
import java.io.File;
import java.sql.SQLException;
import models.Account;
import models.User;
import views.LoginForm;
import views.MainFrame;

/**
 *
 * @author linhd
 */
public class MusicPlayer {
    
    
    public static void main(String[] args) throws SQLException {
        
        
        AudioController audio = AudioController.getIntance();
        
        File f = new File("acc.txt");
        if( f.exists() ) {
            Object data = FileController.readData("acc.txt");            
            if( data != null ){
                Account acc = (Account) data;
                UserQuery query = new UserQuery();
                User user = query.getUser(acc.getUsername(), acc.getPassword());
                
                MainFrame frame = new MainFrame(user);
                frame.setVisible(true);
            } 
        } else {
            // don't remember user
            LoginForm loginForm = new LoginForm(null);
        }
        
    }
}
