/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.esprit.dao;

import edu.esprit.entities.Client;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author DarkLord_666
 */
public class ClientDAO {
    
     public void insertClient(Client c){
      String requete = "insert into clients (nom,prenom,cin,adresse,email,login,mdp,date_naiss) values (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            
            ps.setString(1, c.getNom());
            ps.setString(2, c.getPrenom());
            ps.setInt(3, c.getCin());
            ps.setString(4, c.getAdresse());
            ps.setString(5,c.getEmail() );
            ps.setString(6, c.getLogin());
            ps.setInt(7, c.getMdp());
            ps.setString(8, c.getDate_naiss());
            
            ps.executeUpdate();
            System.out.println("Ajout effectuée avec succès");
        } catch (SQLException ex) {
           //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de l'insertion "+ex.getMessage());
        }
      
    }
      public void deleteClient(int num){

          String requete = "delete from clients where id_Client=?";
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

    public Client findClientbyid(int num){
   Client c = new Client();
     String requete = "select * from clients where id_Client=?";
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ps.setInt(1, num);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next())
            {
                c.setId_Client(resultat.getInt(1));
                c.setNom(resultat.getString(2));
                c.setPrenom(resultat.getString(3));
                c.setCin(resultat.getInt(4));
                c.setAdresse(resultat.getString(5));
                c.setEmail(resultat.getString(6));
                  c.setLogin(resultat.getString(7));
                c.setMdp(resultat.getInt(8));
                c.setDate_naiss(resultat.getString(9));
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

    public List<Client> DisplayAllClient(){


        List<Client> listedepots = new ArrayList<Client>();

        String requete = "select * from clients";
        try {
           Statement statement = MyConnection.getInstance().createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while(resultat.next()){
                Client c =new Client();
                c.setId_Client(resultat.getInt(1));
                c.setNom(resultat.getString(2));
                c.setPrenom(resultat.getString(3));
                c.setCin(resultat.getInt(4));
                 c.setAdresse(resultat.getString(5));
                c.setEmail(resultat.getString(6));
                    c.setLogin(resultat.getString(7));
                    c.setMdp(resultat.getInt(8));
                    c.setDate_naiss(resultat.getString(9));
                listedepots.add(c);
            }
            return listedepots;
        } catch (SQLException ex) {
           //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des depots "+ex.getMessage());
            return null;
        }
    }
    public List<Client> DisplayClient(){


        List<Client> listedepots = new ArrayList<Client>();

        String requete = "select id_Client,nom,prenom from clients";
        try {
           Statement statement = MyConnection.getInstance().createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while(resultat.next()){
                Client c =new Client();
                c.setId_Client(resultat.getInt(1));
                c.setNom(resultat.getString(2));
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
