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
import edu.esprit.entities.Service;
import edu.esprit.util.MyConnection;

/**
 *
 * @author Karray
 */
public class ServiceDAO {


    public void insertService(Service s){
        
           

         String requete = "insert into services (Lieux,Type,Infos,Prix) values (?,?,?,?)";
        try { //dep=depdao.findDepotById(st.getDepot().getId_dep());
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ps.setString(1, s.getLieux());
            ps.setString(2, s.getType());
            ps.setString(3, s.getInfos());
            ps.setInt(4, s.getPrix());
            ps.executeUpdate();
            System.out.println("Ajout effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de l'insertion "+ex.getMessage());
        }
    }

    public void updateService(Service s){
        String requete = "update services set Lieux=?, Type=?, Infos=?, Prix=? where id_Service=?";
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ps.setString(1, s.getLieux());
            ps.setString(2, s.getType());
            ps.setString(3, s.getInfos());
            ps.setInt(4, s.getPrix());
            ps.setInt(5, s.getid_Serivce());
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
           //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la mise à jour "+ex.getMessage());
        }

    }

     public void deleteService(int id_Service){

          String requete = "delete from stock where id_Service=?";
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ps.setInt(1, id_Service);
            ps.executeUpdate();
            System.out.println("Suppression effectuée avec succès");
        } catch (SQLException ex) {
           //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la suppression "+ex.getMessage());
        }
    }


      public Service findServiceByID(int id_Serivce){

        String requete = "select * from stock where id_Service=?";

        try{
        PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
        ResultSet resultat = ps.executeQuery();
        Service service = new Service();
        while (resultat.next()){
            service.setid_Service(resultat.getInt(1));
            service.setLieux(resultat.getString(2));
            service.setType(resultat.getString(3));
            service.setInfos(resultat.getString(4));
            service.setPrix(resultat.getInt(5));
            
        }
        return service;
        }
        catch(SQLException ex){
            System.out.println("erreur lors du chargement"+ex.getMessage());
            return null;
        }
    }

       public List<Service> DisplayAllServices (){


        List<Service> listeservice= new ArrayList<Service>();

        String requete = "select * from services";
        try {
           Statement statement = MyConnection.getInstance()
                   .createStatement();
            ResultSet resultat = statement.executeQuery(requete);
            while(resultat.next()){
                Service service = new Service();
                                service.setid_Service(resultat.getInt(1));

                service.setLieux(resultat.getString(2));
                service.setType(resultat.getString(3));
                service.setInfos(resultat.getString(4));
                service.setPrix(resultat.getInt(5));
                listeservice.add(service);
            }
            return listeservice;
        } catch (SQLException ex) {
           //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des stocks "+ex.getMessage());
            return null;
        }
      
    }
       public List<Service> DisplayAllServicesinscripourpension (){


        List<Service> listeservice= new ArrayList<Service>();

        String requete = "select Type from clients,inscription,services where services.id_Service=inscription.id_Service AND Type='pension' ";
        try {
           Statement statement = MyConnection.getInstance()
                   .createStatement();
            ResultSet resultat = statement.executeQuery(requete);
            while(resultat.next()){
                Service service = new Service();
                                

                
                service.setType(resultat.getString(1));
                
                listeservice.add(service);
            }
            return listeservice;
        } catch (SQLException ex) {
           //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des stocks "+ex.getMessage());
            return null;
        }
      
    }
        public List<Service> DisplayAllServicesinscripourtoilettage (){


        List<Service> listeservice= new ArrayList<Service>();

        String requete = "select Type from clients,inscription,services where services.id_Service=inscription.id_Service AND Type='toilettage' ";
        try {
           Statement statement = MyConnection.getInstance()
                   .createStatement();
            ResultSet resultat = statement.executeQuery(requete);
            while(resultat.next()){
                Service service = new Service();
                                

                
                service.setType(resultat.getString(1));
                
                listeservice.add(service);
            }
            return listeservice;
        } catch (SQLException ex) {
           //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des stocks "+ex.getMessage());
            return null;
        }
      
    }
        public List<Service> DisplayAllServicesinscris (){


        List<Service> listeservice= new ArrayList<Service>();

        String requete = "select distinct Type,Lieux from clients,inscription,services where services.id_Service=inscription.id_Service ";
        try {
           Statement statement = MyConnection.getInstance()
                   .createStatement();
            ResultSet resultat = statement.executeQuery(requete);
            while(resultat.next()){
                Service service = new Service();
                                

                
                service.setType(resultat.getString(1));
                service.setLieux(resultat.getString(2));
                
                listeservice.add(service);
            }
            return listeservice;
        } catch (SQLException ex) {
           //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des stocks "+ex.getMessage());
            return null;
        }
     
      
    }


}
