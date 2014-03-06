/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.DAO;

import edu.esprit.util.MyConnection;
import edu.esprit.entities.Participant;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author sarah
 */
public class ParticipantDAO {
    
     public void insertClient(Participant c){
      String requete = "insert into Participant (Nom,prenom,cin,Telephone,Ide) values (?,?,?,?,?)";
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            
            ps.setString(1, c.getNomp());
            ps.setString(2, c.getPrenom());
            ps.setInt(3, c.getCin());
            ps.setInt(4,c.getTelephone());
            ps.setInt(5,c.getIde());
  
            ps.executeUpdate();
            System.out.println("Ajout effectuée avec succès");
        } catch (SQLException ex) {
           //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de l'insertion "+ex.getMessage());
        }
      
    }
      public void deleteClient(int num){

          String requete = "delete from Participant where idp=?";
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ps.setInt(1, num);
            ps.executeUpdate();
            System.out.println("Suppression effectuée avec succès");
        } catch (SQLException ex) {
           //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la suppression "+ex.getMessage());
        }
    }

    public Participant findClientbyid(int num){
   Participant c = new Participant();
     String requete = "select * from Participant where Idp=?";
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ps.setInt(1, num);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next())
            {
                c.setIdc(resultat.getInt(1));
                c.setNomp(resultat.getString(2));
                c.setPrenom(resultat.getString(3));
                c.setCin(resultat.getInt(4));
                //System.out.println(""+c.getNom());
                //c.getPrenom(resultat.getString(2));
            }
            System.out.println("Trouvé");
            return c;

        } catch (SQLException ex) {
           //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la recherche du depot "+ex.getMessage());
            return null;
        }
    }

    public List<Participant> DisplayAllParticipant(){


        List<Participant> listedepots = new ArrayList<Participant>();

        String requete = "select * from Participant";
        try {
           Statement statement = MyConnection.getInstance().createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while(resultat.next()){
                Participant c =new Participant();
                c.setIdc(resultat.getInt(1));
                c.setNomp(resultat.getString(2));
                c.setPrenom(resultat.getString(3));
                c.setCin(resultat.getInt(4));
                
                listedepots.add(c);
            }
            return listedepots;
        } catch (SQLException ex) {
           //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des depots "+ex.getMessage());
            return null;
        }
    }
    public List<Participant> DisplayClient(){


        List<Participant> listedepots = new ArrayList<Participant>();

        String requete = "select idp,Nom,Prenom from Participant";
        try {
           Statement statement = MyConnection.getInstance().createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while(resultat.next()){
                Participant c =new Participant();
                c.setIdc(resultat.getInt(1));
                c.setNomp(resultat.getString(2));
                c.setPrenom(resultat.getString(3));
                listedepots.add(c);
            }
            return listedepots;
        } catch (SQLException ex) {
           //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des depots "+ex.getMessage());
            return null;
        }
    }

}