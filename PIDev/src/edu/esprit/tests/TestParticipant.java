/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.tests;
import edu.esprit.DAO.ParticipantDAO;
import edu.esprit.entities.Participant;
/**
 *
 * @author DarkLord_666
 */
public class TestParticipant {
  public static void main(String[] args) {

        Participant c = new Participant();
        ParticipantDAO dao = new ParticipantDAO();
      /*  c.setNom("Bezi");
        c.setPrenom("Sarah");
       
        dao.insertClient(c); */
        
       c=dao.findClientbyid(2);
     //  System.out.println(c.getLogin());
        //dao.deleteClient(7);
    }   
}
