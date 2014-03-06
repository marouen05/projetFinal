/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.esprit.dao;
import edu.esprit.entities.Client;
/**
 *
 * @author DarkLord_666
 */
public class test {
  public static void main(String[] args) {

        Client c = new Client();
        ClientDAO dao = new ClientDAO();
      /*  c.setNom("Bacha");
        c.setPrenom("Adly");
        c.setAdresse("Hammamet");
        c.setCin(159888);
        c.setDate_naiss("01/04/91");
        c.setEmail("adly.bacha@esprit.tn");
        c.setLogin("adly");
        c.setMdp(555);*/
        //dao.insertClient(c);
       c=dao.findClientbyid(2);
     //  System.out.println(c.getLogin());
        //dao.deleteClient(7);
    }   
}
