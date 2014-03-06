/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

import java.sql.Date;

/**
 *
 * @author sarah
 */
public class Evenement {
     private int ID;
    private String Nom;
    private String  Date;
    private String Lieu;
    private String Infos;
    

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNom() {
        return Nom;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date ) {
        this.Date = Date;
    }
    public void setNom(String Nom) {
        this.Nom = Nom;
    }
    
    public String getLieu() {
        return Lieu;
    }

    public void setLieu(String Lieu) {
        this.Lieu = Lieu;
    }
    
    public String getInfos() {
        return Infos;
    }

    public void setInfos(String Infos) {
        this.Infos= Infos;
    }
}
