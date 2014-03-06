/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import edu.esprit.entities.Evenement;
import edu.esprit.util.MyConnection;
/**
 *
 * @author sarah
 */
public class evenementDAO {

    public void insertEvenement(Evenement e){
           

         String requete = "insert into evenement (Nom,Date,Lieu,Infos)values (?,?,?,?)";
        try { //dep=depdao.findDepotById(st.getDepot().getId_dep());
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            
            ps.setString(1, e.getNom());
            ps.setString(2 ,e.getDate());
            ps.setString(3, e.getLieu());
            ps.setString(4, e.getInfos());
            ps.executeUpdate();
            System.out.println("Ajout effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de l'insertion "+ex.getMessage());
        }
    }

    public void updateEvenement(Evenement e){
        String requete = "update services set  Nom=?, Date=? ,Lieu=?, Infos=?,  where ID=?";
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ps.setString(1, e.getNom());
            ps.setString(2, e.getDate());
            ps.setString(3, e.getLieu());
            ps.setString(4, e.getInfos());
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
           //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la mise à jour "+ex.getMessage());
        }

    }

     public void deleteEvenement(int ID){

          String requete = "delete from evenement where ID=?";
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ps.setInt(1, ID);
            ps.executeUpdate();
            System.out.println("Suppression effectuée avec succès");
        } catch (SQLException ex) {
           //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la suppression "+ex.getMessage());
        }
    }


      public Evenement findEvenementByID(int ID){

        String requete = "select * from stock where ID=?";

        try{
        PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
        ResultSet resultat = ps.executeQuery();
        Evenement Evenement = new Evenement();
        while (resultat.next()){

            Evenement.setNom(resultat.getString(1));
            Evenement.setDate(resultat.getString(2));
            Evenement.setLieu(resultat.getString(3));
            Evenement.setInfos(resultat.getString(4));
            
        }
        return Evenement;
        }
        catch(SQLException ex){
            System.out.println("erreur lors du chargement"+ex.getMessage());
            return null;
        }
    }

       public List<Evenement> DisplayAllEvenement (){


        List<Evenement> listeEvenement= new ArrayList<Evenement>();

        String requete = "select * from Evenement";
        try {
           Statement statement = MyConnection.getInstance()
                   .createStatement();
            ResultSet resultat = statement.executeQuery(requete);
            while(resultat.next()){
                Evenement Evenement = new Evenement();
                Evenement.setID(resultat.getInt(1));
                Evenement.setNom(resultat.getString(2));
                Evenement.setDate(resultat.getString(3));
                Evenement.setLieu(resultat.getString(4));
                Evenement.setInfos(resultat.getString(5));
                listeEvenement.add(Evenement);
            }
            return listeEvenement;
        } catch (SQLException ex) {
           //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des stocks "+ex.getMessage());
            return null;
        }
      
    }

    public void updateEvenement(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}