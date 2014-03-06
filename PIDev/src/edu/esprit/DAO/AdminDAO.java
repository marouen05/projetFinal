/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.DAO;

import edu.esprit.entities.Admin;
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
public class AdminDAO {

    public void insertAdmin(Admin ad) {

        String requete = "insert into admin (nom,prenom,login,mdp,email) values (?,?,?,?,?)";
        try { //dep=depdao.findDepotById(st.getDepot().getId_dep());
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            
            ps.setString(1, ad.getNom());
            ps.setString(2, ad.getPrenom());
            ps.setString(3, ad.getLogin());
            ps.setString(4, ad.getMdp());
            ps.setString(5, ad.getEmail());
            ps.executeUpdate();
            System.out.println("Ajout effectuée avec succès");
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de l'insertion " + ex.getMessage());
        }
    }

    public void updateAdmin(Admin ad) {
        String requete = "update admin set nom=?,prenom=?,login=?,mdp=?,email=? where id_admin=?";
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ps.setString(1, ad.getNom());
            ps.setString(2, ad.getPrenom());
            ps.setString(3, ad.getLogin());
             ps.setString(4, ad.getMdp());
             ps.setString(5, ad.getEmail());
            ps.setInt(6, ad.getId_admin());
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }

    public void deleteAdmin(int id) {
        String requete = "delete from admin where id_admin=?";
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Administrateur supprimée");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }

    public Admin findAdminById(int id) {
        Admin ad = new Admin();
        String requete = "select * from admin where id_admin=?";
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                ad.setId_admin(resultat.getInt(1));
                ad.setNom(resultat.getString(2));
                ad.setPrenom(resultat.getString(3));
                ad.setLogin(resultat.getString(4));
                ad.setMdp(resultat.getString(5));
                ad.setEmail(resultat.getString(6));

            }
            return ad;

        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la recherche de l'admin " + ex.getMessage());
            return null;
        }
    }

            public List<Admin> DisplayAllAdmin() {


        List<Admin> listeadmins = new ArrayList<Admin>();

        String requete = "select * from admin";
        try {
            Statement statement = MyConnection.getInstance().createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                Admin ad = new Admin();
                ad.setId_admin(resultat.getInt(1));
                ad.setNom(resultat.getString(2));
                ad.setPrenom(resultat.getString(3));
                ad.setLogin(resultat.getString(4));
                ad.setMdp(resultat.getString(5));
                ad.setEmail(resultat.getString(6));

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
        String requete = "select * from admin where mdp=? and login=? ";
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
