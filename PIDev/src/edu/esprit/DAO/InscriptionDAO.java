/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.esprit.DAO;

import edu.esprit.entities.Inscription;
import edu.esprit.entities.Service;
import edu.esprit.util.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Majdi Chaabene
 */
/*public interface InscriptionDAO {
    public Inscription findInscriptionByIDService(int id_Serivce){

        String requete = "select * from inscription where id_Service=?";
        Inscription inscription = new Inscription();

        try{
        PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
        ResultSet resultat = ps.executeQuery();
        while (resultat.next()){
            inscription.setid_Service(resultat.getInt(1));
            
            
        }
        return inscription;
        }
        catch(SQLException ex){
            System.out.println("erreur lors du chargement"+ex.getMessage());
            return null;
        }
    }
    public Inscription findInscriptionByIDClient(int id_Client){

        String requete = "select * from inscription where id_Client=?";
        Inscription inscription = new Inscription();

        try{
        PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
        ResultSet resultat = ps.executeQuery();
        while (resultat.next()){
            inscription.setid_Client(resultat.getInt(1));
            
            
        }
        return inscription;
        }
        catch(SQLException ex){
            System.out.println("erreur lors du chargement"+ex.getMessage());
            return null;
        }
    }
    
}
*/