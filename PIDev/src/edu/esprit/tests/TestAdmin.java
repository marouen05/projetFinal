/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.tests;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import edu.esprit.DAO.AdminDAO;
import edu.esprit.entities.Admin;


public class TestAdmin {

    public static void main(String[] args) {

        Admin admin = new Admin();
        AdminDAO adminDAO = new AdminDAO();

        /*   //AJOUT
        admin.setNom("testnom");
        admin.setMdp("testmdp");
        admin.setLogin("testlogin");
        adminDAO.insertAdmin(admin);
         */

        /*  //MODIFICATION
        admin.setId_admin(3);
        admin.setNom("testnommodifié");
        admin.setMdp("testmdpmodifié");
        admin.setLogin("testloginmodifié");
        adminDAO.updateAdmin(admin);
         */

        //SUPPRESSION
        /*
        int id=3;
        admin.setId_admin(3);
        adminDAO.deleteAdmin(id);
         */

       //RECHERCHE PAR ID
       /* int id=2;
        Admin test=new Admin();
        test=adminDAO.findAdminById(id);
                System.out.println(""+test.getId_admin()+test.getNom()+test.getLogin()+test.getMdp()); */

        List<Admin> test = new ArrayList<Admin>();
        
        AdminDAO aDAO=new AdminDAO();
        test=aDAO.DisplayAllAdmin();
        System.out.println(""+test.toString());
    }
}
