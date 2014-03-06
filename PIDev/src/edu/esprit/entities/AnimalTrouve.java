/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.esprit.entities;

import edu.esprit.entities.Animal;

/**
 *
 * @author Melek
 */
public class AnimalTrouve extends Animal {

     private String date;
    private String ville;
    private String region;

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the ville
     */
    public String getVille() {
        return ville;
    }

    /**
     * @param vente the ville to set
     */
    public void setVille(String ville) {
        this.ville = ville;
    }

    /**
     * @return the region
     */
    public String getRegion() {
        return region;
    }

    /**
     * @param region the region to set
     */
    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return super.toString()+" "+date+" "+ville+" "+region;
    }


    


}
