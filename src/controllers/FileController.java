/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author pytha
 */
public class FileController {
    public static boolean saveData(Object data, String path) {
        try {
            FileOutputStream fos = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(data);
            oos.close(); fos.close();
            return true;
        } catch (IOException e) {
        }
        return false;
    }
    
    public static Object readData(String path){
        try {
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object d = ois.readObject();
            ois.close(); fis.close();
            return d;
        } catch (IOException | ClassNotFoundException e) {
        }        
        return null;
    }
}
