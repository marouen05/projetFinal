/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.esprit.entities;

/**
 *
 * @author Karray
 */
public class Service {

    private int id_Service;
    private String Lieux;
    private String Type;
    private String Infos;
    private int Prix;

    public int getid_Serivce() {
        return id_Service;
    }

    public void setid_Service(int id_Service) {
        this.id_Service = id_Service;
    }

    public String getLieux() {
        return Lieux;
    }

    public void setLieux(String Lieux) {
        this.Lieux = Lieux;
    }
    
    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }
    
    public String getInfos() {
        return Infos;
    }

    public void setInfos(String Infos) {
        this.Infos = Infos;
    }
    
    public int getPrix() {
        return Prix;
    }

    public void setPrix(int Prix) {
        this.Prix = Prix;
    }

}
