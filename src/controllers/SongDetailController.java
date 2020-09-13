/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import models.Song;

/**
 *
 * @author LinhDV
 */
public class SongDetailController {
    
    public static Map getSongDetail (Song s){
        
        String path = System.getProperty("user.dir") + "\\src\\musics\\" + s.getUrl() + ".mp3";
        File file = new File(path);
        AudioFileFormat audioFileFormat = null;
        try {
            audioFileFormat = AudioSystem.getAudioFileFormat(file);
        } catch (IOException | UnsupportedAudioFileException ex) {
            
        }
        Map properties = audioFileFormat.properties();
        
        return properties;
    }
}
