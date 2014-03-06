/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.DAO;

import edu.esprit.entities.ExpertAnimalier;
import edu.esprit.util.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Abd
 */
public class ExpertDAO {

    public void insertExpert(ExpertAnimalier ad) {

        String requete = "insert into expertanimalier (name,prenom,domain,adresse,numtel,email,login,mdp,cin) values (?,?,?,?,?,?,?,?,?)";
        try { //dep=depdao.findDepotById(st.getDepot().getId_dep());
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            
            ps.setString(1, ad.getNom());
            ps.setString(2, ad.getPrenom());
            ps.setString(3, ad.getDomain());
            ps.setString(4, ad.getAdresse());
             ps.setInt(5, ad.getNumtel());
            ps.setString(6, ad.getEmail());
            ps.setString(7, ad.getLogin());
            ps.setString(8, ad.getMdp());
            
            ps.setInt(9, ad.getCin());
            ps.executeUpdate();
            System.out.println("Ajout effectuée avec succès");
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de l'insertion " + ex.getMessage());
        }
    }

    public void updateExpert(ExpertAnimalier ad) {
        String requete = "update expertanimalier set name=?,prenom=?,domain=?,adresse=?,numtel=?,email=?,login=?,mdp=?,cin=? where id=?";
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
              ps.setString(1, ad.getNom());
            ps.setString(2, ad.getPrenom());
            ps.setString(3, ad.getDomain());
            ps.setString(4, ad.getAdresse());
             ps.setInt(5, ad.getNumtel());
            ps.setString(6, ad.getEmail());
            ps.setString(7, ad.getLogin());
            ps.setString(8, ad.getMdp());
            ps.setInt(9, ad.getCin());
           ps.setInt(10, ad.getId_Expert());
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }

    public void deleteExpert(int id) {
        String requete = "delete from expertanimalier where id=?";
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Expert supprimée");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }

    public ExpertAnimalier findExpertById(int id) {
        ExpertAnimalier ad = new ExpertAnimalier();
        String requete = "select * from expertanimalier where id=?";
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                ad.setId_Expert(resultat.getInt(1));
                ad.setNom(resultat.getString(2));
                
                ad.setPrenom(resultat.getString(3));
                ad.setDomain(resultat.getString(4));
                ad.setAdresse(resultat.getString(5));
                ad.setLogin(resultat.getString(8));
                  ad.setNumtel(resultat.getInt(6));
                ad.setMdp(resultat.getString(9));
                ad.setEmail(resultat.getString(7));
                ad.setCin(resultat.getInt(10));
            }
            return ad;

        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la recherche de l'admin " + ex.getMessage());
            return null;
        }
    }
      public ExpertAnimalier findExpertBylogin(String login) {
        ExpertAnimalier ad = new ExpertAnimalier();
        String requete = "select * from expertanimalier where login=?";
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ps.setString(1, login);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                ad.setId_Expert(resultat.getInt(1));
                ad.setNom(resultat.getString(2));
                
                ad.setPrenom(resultat.getString(3));
                ad.setDomain(resultat.getString(4));
                ad.setAdresse(resultat.getString(5));
                ad.setLogin(resultat.getString(8));
                  ad.setNumtel(resultat.getInt(6));
                ad.setMdp(resultat.getString(9));
                ad.setEmail(resultat.getString(7));
                ad.setCin(resultat.getInt(10));
            }
            return ad;

        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la recherche de l'admin " + ex.getMessage());
            return null;
        }
    }
 public ExpertAnimalier findExpertBycin(int cin) {
        ExpertAnimalier ad = new ExpertAnimalier();
        String requete = "select * from expertanimalier where cin=?";
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ps.setInt(1, cin);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                ad.setId_Expert(resultat.getInt(1));
                ad.setNom(resultat.getString(2));
               
                ad.setPrenom(resultat.getString(3));
                ad.setDomain(resultat.getString(4));
                ad.setAdresse(resultat.getString(5));
                ad.setLogin(resultat.getString(8));
                  ad.setNumtel(resultat.getInt(6));
                ad.setMdp(resultat.getString(9));
                ad.setEmail(resultat.getString(7));
                ad.setCin(resultat.getInt(10));
            }
            return ad;

        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la recherche de l'admin " + ex.getMessage());
            return null;
        }
    }
            public List<ExpertAnimalier> DisplayAllExpert() {


        List<ExpertAnimalier> listeadmins = new ArrayList<ExpertAnimalier>();

        String requete = "select * from expertanimalier";
        try {
            Statement statement = MyConnection.getInstance().createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                ExpertAnimalier ad = new ExpertAnimalier();
               ad.setId_Expert(resultat.getInt(1));
                ad.setNom(resultat.getString(2));
                ad.setPrenom(resultat.getString(3));
                ad.setDomain(resultat.getString(4));
                ad.setAdresse(resultat.getString(5));
                ad.setLogin(resultat.getString(8));
                  ad.setNumtel(resultat.getInt(6));
                ad.setMdp(resultat.getString(9));
                ad.setEmail(resultat.getString(7));
                ad.setCin(resultat.getInt(10));
                listeadmins.add(ad);
            }
            return listeadmins;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des Admins " + ex.getMessage());
            return null;
        }
    }

    public boolean Authentification(String login, String mdp) {
        boolean retour = false;
        String requete = "select * from expertanimalier where mdp=? and login=? ";
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ps.setString(1, mdp);
            ps.setString(2, login);

            ResultSet resultat = ps.executeQuery();
            if (resultat.next()) {
                retour = true;
            } else {
                retour = false;
            }


        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la recherche de l'admin " + ex.getMessage());
        }
        return retour;
    }
}
