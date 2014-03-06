/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

/**
 *
 * @author sarah
 */
public class Participant {
    private int Idp;	
private String Nomp;	
private String Prenom	;	
 private int  Cin;
 private int Telephone;
 public int Ide;

 public int getIdp() {
        return Idp;
    }

    public int getTelephone() {
        return Telephone;
    }

    public void setTelephone(int Telephone) {
        this.Telephone = Telephone;
    }

   
    public void setIdc(int idp) {
        this.Idp = idp;
    }

   
    public String getNomp() {
        return Nomp;
    }

    
    public void setNomp(String Nomp) {
        this.Nomp = Nomp;
    }
     public String getPrenom() {
        return Prenom;
    }

    
    public void setPrenom(String Prenom) {
        this.Prenom= Prenom;
    }
    
     public int getCin() {
        return Cin;
    }

    
    public void setCin(int Cin) {
        this.Cin = Cin;
    }

public int getIde() {
        return Ide;
    }
public void setIde(int Ide) {
        this.Ide = Ide;
    }
   
}