/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

/**
 *
 * @author LinhDV
 */
public class Utils {
    public static String getMinutesRapp(long microseconds) {
        int sec = (int) (microseconds / 1000000);
        int min = sec / 60;
        sec = sec % 60;
        String ms = min + "";
        String ss = sec + "";
        if (ms.length() < 2) {
            ms = "0" + ms;
        }
        if (ss.length() < 2) {
            ss = "0" + ss;
        }
        return ms + ":" + ss;
    }
}
