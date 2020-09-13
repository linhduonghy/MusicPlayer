/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.File;
import views.LoginForm;
import views.MainFrame;

/**
 *
 * @author linhd
 */
public class LogoutController {

    public LogoutController(MainFrame frame) {
        File file = new File("acc.txt");
        if (file.exists()) {
            file.delete();
        }
        frame.setVisible(false);
        
        new LoginForm(null);
    }
    
    
}
