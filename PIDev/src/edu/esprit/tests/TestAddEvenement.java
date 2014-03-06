/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.tests;
import edu.esprit.entities.Evenement;
import edu.esprit.DAO.evenementDAO;
/**
 *
 * @author sarah
 */ 
public class TestAddEvenement {
    
    public static void main(String[] args) {
        Evenement evenement = new Evenement();
        evenementDAO evenementDAO = new evenementDAO();
        evenement.setNom("Depistage ");
        evenement.setDate("03-13-14");
        evenement.setLieu("Esprit");
        evenement.setInfos("ouvert à partir de 9h jusqu'a 18h");
        
        evenementDAO.insertEvenement(evenement);
    
}
}
