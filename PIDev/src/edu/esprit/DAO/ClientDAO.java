/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.esprit.DAO;


import edu.esprit.util.MyConnection;
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
      String requete = "insert into clients (nom,prenom,cin,adresse,email,login,date_naiss,pwd) values (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            //
            ps.setString(1, c.getNom());
            ps.setString(2, c.getPrenom());
            ps.setInt(3, c.getCin());
            ps.setString(4, c.getAdresse());
            ps.setString(5,c.getEmail() );
            ps.setString(6, c.getLogin());
            ps.setString(8, c.getPwd());
            ps.setString(7, c.getDate_naiss());
            
            ps.executeUpdate();
            System.out.println("Ajout effectuée avec succès");
        } catch (SQLException ex) {
           //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de l'insertion "+ex.getMessage());
        }
      
    }
     public void updateClient(Client c){
      String requete = "update clients set cin=?,adresse=?,email=?,login=?,date_naiss=?,pwd=? where id_Client=?";
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            
           
            ps.setInt(1, c.getCin());
            ps.setString(2, c.getAdresse());
            ps.setString(3,c.getEmail() );
            ps.setString(4, c.getLogin());
            ps.setString(6, c.getPwd());
            ps.setString(5, c.getDate_naiss());
            ps.setInt(7, c.getId_Client());
            ps.executeUpdate();
            System.out.println("Modification effectuée avec succès");
        } catch (SQLException ex) {
           //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la modification "+ex.getMessage());
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
                c.setPwd(resultat.getString(9));
                c.setDate_naiss(resultat.getString(8));
          
            }
            System.out.println("Trouvé");
            return c;

        } catch (SQLException ex) {
             System.out.println("Non Trouvé");
           //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la recherche du depot "+ex.getMessage());
            return null;
        }
    }
     public Client findClientbylogin(String log){
   Client c = new Client();
     String requete = "select * from clients where login=?";
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ps.setString(1, log);
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
                c.setPwd(resultat.getString(9));
                c.setDate_naiss(resultat.getString(8));
          
            }
            System.out.println("Trouvé");
            return c;

        } catch (SQLException ex) {
           //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la recherche du depot "+ex.getMessage());
            return null;
        }
    }
     public Client findClientbyemail(String email){
   Client c = new Client();
     String requete = "select email from clients where email=?";
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ps.setString(1, email);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next())
            {
              
                c.setEmail(resultat.getString(1));
                  
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

        String requete = "select * from clients ";
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
                    c.setPwd(resultat.getString(9));
                    c.setDate_naiss(resultat.getString(8));
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
    
    public boolean Authentification(String login, String mdp) {
        boolean retour = false;
        String requete = "select * from clients where pwd=? and login=? ";
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
