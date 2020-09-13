/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;

/**
 *
 * @author linhd
 */
public class Singer {
    
    private int id;
    private String name;
    private String company;
    private String country;
    private String dob;
    private ArrayList<Song> listSong;
    
    public Singer() {
    }

    public Singer(int id, String name, String company, String country, String dob) {
        this.id = id;
        this.name = name;
        this.company = company;
        this.country = country;
        this.dob = dob;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public ArrayList<Song> getListSong() {
        return listSong;
    }

    public void setListSong(ArrayList<Song> listSong) {
        this.listSong = listSong;
    }
    
    
    public String toString() {
        return id + " " + name + " " + dob + " " + country + " " + company;        
    }
}
