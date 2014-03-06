/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

/**
 *
 * @author Abd
 */
public class Admin {

    private int id_admin;
    private String nom;
    private String login;
    private String mdp;
    private String prenom;
    private String email;

    public int getId_admin() {
        return id_admin;
    }

    public void setId_admin(int id_admin) {
        this.id_admin = id_admin;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public String toString()
            {

    return "ID_Admin: "+this.id_admin+" Nom :"+this.nom+" Prenom: "+this.getPrenom()+" Login:"+this.login+
            " Mdp:"+this.mdp+" Email:"+this.getEmail();

            }

    
}
