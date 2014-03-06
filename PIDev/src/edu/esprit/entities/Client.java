/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.esprit.entities;

/**
 *
 * @author DarkLord_666
 */
public class Client {
    private int id_Client;	
private String Nom;	
private String Prenom	;	
 private int  cin;
private String date_naiss;	
private String adresse	;
private String email;
private String login	;
 private String pwd;

    /**
     * @return the id_Client
     */
    public int getId_Client() {
        return id_Client;
    }

    /**
     * @param id_Client the id_Client to set
     */
    public void setId_Client(int id_Client) {
        this.id_Client = id_Client;
    }

    /**
     * @return the Nom
     */
    public String getNom() {
        return Nom;
    }

    /**
     * @param Nom the Nom to set
     */
    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    /**
     * @return the Prenom
     */
    public String getPrenom() {
        return Prenom;
    }

    /**
     * @param Prenom the Prenom to set
     */
    public void setPrenom(String Prenom) {
        this.Prenom = Prenom;
    }

    /**
     * @return the cin
     */
    public int getCin() {
        return cin;
    }

    /**
     * @param cin the cin to set
     */
    public void setCin(int cin) {
        this.cin = cin;
    }

    /**
     * @return the date_naiss
     */
    public String getDate_naiss() {
        return date_naiss;
    }

    /**
     * @param date_naiss the date_naiss to set
     */
    public void setDate_naiss(String date_naiss) {
        this.date_naiss = date_naiss;
    }

    /**
     * @return the adresse
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     * @param adresse the adresse to set
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the mdp
     */


    /**
     * @return the pwd
     */
    public String getPwd() {
        return pwd;
    }

    /**
     * @param pwd the pwd to set
     */
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
