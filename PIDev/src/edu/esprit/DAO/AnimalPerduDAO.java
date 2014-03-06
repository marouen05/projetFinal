/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.esprit.DAO;

import edu.esprit.entities.AnimalPerdu;
import edu.esprit.util.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Melek
 */
public class AnimalPerduDAO {

     public void insertAnimal(AnimalPerdu a,int id_client){

        String requete = "insert into animal (espece,type,couleur,sexe,photo,description,etat,id_client) values (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ps.setString(1, a.getEspece());
            ps.setString(2, a.getType());
            ps.setString(3, a.getCouleur());
            ps.setString(4, a.getSexe());
            ps.setBytes(5, a.getPhoto());
            ps.setString(6, a.getDescription());
            ps.setString(7, a.getEtat());
            ps.setInt(8, id_client);
            ps.executeUpdate();
            requete= "select id from animal where description=? ";
            ps = MyConnection.getInstance().prepareStatement(requete);
            ps.setString(1, a.getDescription());
            ResultSet resultat = ps.executeQuery();
            while(resultat.next())
            a.setId(resultat.getInt(1));
            requete="insert into animal_perdu (id,nom,date,ville,region) values(?,?,?,?,?)";

            ps= MyConnection.getInstance().prepareStatement(requete);
            ps.setInt(1,a.getId());
            ps.setString(2, a.getNom());
            ps.setString(3, a.getDate());
            ps.setString(4, a.getVille());
            ps.setString(5, a.getRegion());

            ps.executeUpdate();


            System.out.println("Ajout effectué avec succès");
        } catch (SQLException ex) {
           //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de l'insertion "+ex.getMessage());
        }
    }


    public void updateAnimal(AnimalPerdu a){
        String requete = "update animal set espece=?,type=?,couleur=?,sexe=?,description=?,etat=? where id=?";
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ps.setString(1, a.getEspece());
            ps.setString(2, a.getType());
            ps.setString(3, a.getCouleur());
            ps.setString(4, a.getSexe());
            ps.setString(5, a.getDescription());
            ps.setString(6, a.getEtat());
            ps.setInt(7, a.getId());
            ps.executeUpdate();
            requete="update animal_perdu set nom=?,date=?,ville=?,region=? where id=?";
            ps = MyConnection.getInstance().prepareStatement(requete);
            ps.setString(1,a.getNom());
            ps.setString(2, a.getDate());
            ps.setString(3, a.getVille());
            ps.setString(4, a.getRegion());
            ps.setInt(5,a.getId());
            ps.executeUpdate();
            System.out.println("Mise à jour effectué avec succès");
        } catch (SQLException ex) {
           //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la mise à jour "+ex.getMessage());
        }
    }

    public void deleteAnimal(int id){
        String requete = "delete from animal_perdu where id=?";
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ps.setInt(1, id);
            ps.executeUpdate();
            requete="delete from animal where id=?";
            ps = MyConnection.getInstance().prepareStatement(requete);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Animal supprimé");
        } catch (SQLException ex) {
           //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la suppression "+ex.getMessage());
        }
    }


    public AnimalPerdu findDepotById(int id){
    AnimalPerdu an = new AnimalPerdu();
     String requete = "select * from animal where id=?";
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next())
            {
                an.setId(resultat.getInt(1));
                an.setEspece(resultat.getString(2));
                an.setType(resultat.getString(3));
                an.setCouleur(resultat.getString(4));
                an.setSexe(resultat.getString(5));
                an.setPhoto(resultat.getBytes(6));
                an.setDescription(resultat.getString(7));
                an.setEtat(resultat.getString(8));
                an.setId_client(resultat.getInt(9));
            }
            requete="select * from animal_perdu where id=?";
            ps = MyConnection.getInstance().prepareStatement(requete);
            ps.setInt(1, id);
           resultat = ps.executeQuery();
            while (resultat.next()) {
                an.setNom(resultat.getString(2));
                an.setDate(resultat.getString(3));
                an.setVille(resultat.getString(4));
                an.setRegion(resultat.getString(5));
            }
            return an;

        } catch (SQLException ex) {
           //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la recherche du depot "+ex.getMessage());
            return null;
        }
    }

   public List<AnimalPerdu> DisplayAllAnimalsLost (){


        List<AnimalPerdu> listeAnimaux = new ArrayList<AnimalPerdu>();

        String requete = "select * from animal,animal_perdu where animal.id=animal_perdu.id";
        try {
           Statement statement = MyConnection.getInstance()
                   .createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while(resultat.next()){
                AnimalPerdu animal =new AnimalPerdu();
                animal.setId(resultat.getInt(1));
                animal.setEspece(resultat.getString(2));
                animal.setType(resultat.getString(3));
                animal.setCouleur(resultat.getString(4));
                animal.setSexe(resultat.getString(5));
                animal.setPhoto(resultat.getBytes(6));
                animal.setDescription(resultat.getString(7));
                animal.setEtat(resultat.getString(8));
                animal.setId_client(resultat.getInt(9));
                animal.setNom(resultat.getString(11));
                animal.setDate(resultat.getString(12));
                animal.setVille(resultat.getString(13));
                animal.setRegion(resultat.getString(14));

                listeAnimaux.add(animal);
            }
            return listeAnimaux;
        } catch (SQLException ex) {
           //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des depots "+ex.getMessage());
            return null;
        }
    }

}
