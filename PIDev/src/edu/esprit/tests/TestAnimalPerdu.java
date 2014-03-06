/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.esprit.tests;

import edu.esprit.DAO.AnimalPerduDAO;
import edu.esprit.entities.AnimalPerdu;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Melek
 */
public class TestAnimalPerdu {

    public static void main(String[] args) {

         AnimalPerdu animal= new AnimalPerdu();
        AnimalPerduDAO animalDAO = new AnimalPerduDAO();

        //TEST AJOUT

       /* animal.setType("chat");
        animal.setEspece("minouch");
        animal.setCouleur("blanc");
        animal.setSexe("femelle");
        animal.setPhoto("url2");
        animal.setEtat("te3eb");
        animal.setNom("garmouch");
        animal.setDescription("feded");
        animal.setDate("12 janvier 2055");
        animal.setVille("makthar");
        animal.setRegion("de5el makther");
        animalDAO.insertAnimal(animal,0);  */

        // TEST RECHERCHE ET MISE A JOUR

      /*  animal=animalDAO.findDepotById(15);
         System.out.println(animal);
         animal.setNom("9attous");
         animalDAO.updateAnimal(animal); */

        //TEST AFFICHAGE

        List<AnimalPerdu> maliste= new ArrayList<AnimalPerdu>();
        maliste=animalDAO.DisplayAllAnimalsLost();
        Iterator i=maliste.iterator();
        while(i.hasNext())
        {
            System.out.println(i.next());
        }

     }

}
